package com.example.criteriamicroservice.controller;

import com.example.criteriamicroservice.entity.*;
import com.example.criteriamicroservice.service.CriteriaDirectoryService;
import com.example.criteriamicroservice.service.CriteriaTypeService;
import com.example.criteriamicroservice.service.EvaluationScaleService;
import com.example.criteriamicroservice.service.LifecycleStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class CriteriaDirectoryController {
    @Autowired
    private CriteriaDirectoryService criteriaDirectoryService;
    @Autowired
    private EvaluationScaleService evaluationScaleService;
    @Autowired
    private CriteriaTypeService criteriaTypeService;
    @Autowired
    private LifecycleStageService lifecycleStageService;
    @PostMapping("/criteriaDirectories")
    public CriteriaDirectory saveCriteriaDirectory(@RequestBody CriteriaDirectory criteriaDirectory)
    {
        return criteriaDirectoryService.saveCriteriaDirectory(criteriaDirectory);
    }
    @GetMapping("/criteriaDirectories")
    public List<CriteriaDirectory> fetchCriteriaDirectoryList()
    {
        return criteriaDirectoryService.fetchCriteriaDirectoryList();
    }
    @GetMapping("/criteriaDirectories/{id}")
    public CriteriaDirectory findCriteriaDirectoryById(@PathVariable("id") Long id) {
        return criteriaDirectoryService.findCriteriaDirectoryById(id);
    }
    @GetMapping("/criteriaDirectoriesByScale/{scaleId}")
    public List<CriteriaDirectory> findByEvaluationScale(@PathVariable Long scaleId) {
        EvaluationScale scale = evaluationScaleService.findEvaluationScaleById(scaleId);
        return criteriaDirectoryService.findByEvaluationScale(scale);
    }
    @GetMapping("/criteriaDirectoriesByCriteriaType/{typeID}")
    public List<CriteriaDirectory> findByCriteriaType(@PathVariable Long typeID) {
        CriteriaType criteriaType = criteriaTypeService.findCriteriaTypeById(typeID);
        return criteriaDirectoryService.findByCriteriaType(criteriaType);
    }
    @GetMapping("/criteriaDirectoriesByLifecycleStage/{stageID}")
    public List<CriteriaDirectory> findByLifecycleStage(@PathVariable Long stageID) {
       LifecycleStage lifecycleStage = lifecycleStageService.findLifecycleStageById(stageID);
        return criteriaDirectoryService.findByLifecycleStage(lifecycleStage);
    }
    @GetMapping("/criteriaDirectoriesByScaleAndType")
    public List<CriteriaDirectory> findByScaleAndType(
            @RequestParam Long scaleId,
            @RequestParam Long typeID) {
        EvaluationScale scale = evaluationScaleService.findEvaluationScaleById(scaleId);
        CriteriaType criteriaType = criteriaTypeService.findCriteriaTypeById(typeID);
        return criteriaDirectoryService.findByScaleAndType(scale, criteriaType);
    }
    @GetMapping("/criteriaDirectoriesByScaleAndStage")
    public List<CriteriaDirectory> findByScaleAndStage(
            @RequestParam Long scaleId,
            @RequestParam Long stageID) {
        EvaluationScale scale = evaluationScaleService.findEvaluationScaleById(scaleId);
        LifecycleStage lifecycleStage = lifecycleStageService.findLifecycleStageById(stageID);
        return criteriaDirectoryService.findByScaleAndStage(scale, lifecycleStage);
    }
    @GetMapping("/criteriaDirectoriesByTypeAndStage")
    public List<CriteriaDirectory> findByTypeAndStage(
            @RequestParam Long typeID,
            @RequestParam Long stageID) {
        CriteriaType criteriaType = criteriaTypeService.findCriteriaTypeById(typeID);
        LifecycleStage lifecycleStage = lifecycleStageService.findLifecycleStageById(stageID);
        return criteriaDirectoryService.findByTypeAndStage(criteriaType, lifecycleStage);
    }
    @GetMapping("/criteriaDirectoriesByCombined")
    public List<CriteriaDirectory> findByCombined(
            @RequestParam Long scaleId,
            @RequestParam Long typeID,
            @RequestParam Long stageID) {
        EvaluationScale scale = evaluationScaleService.findEvaluationScaleById(scaleId);
        CriteriaType criteriaType = criteriaTypeService.findCriteriaTypeById(typeID);
        LifecycleStage lifecycleStage = lifecycleStageService.findLifecycleStageById(stageID);
        return criteriaDirectoryService.findByCombined(scale, lifecycleStage, criteriaType);
    }
    // Update operation
    @PutMapping("/criteriaDirectories/{id}")
    public CriteriaDirectory updateCriteriaDirectory(@RequestBody CriteriaDirectory criteriaDirectory, @PathVariable("id") Long ID)
    {
        return criteriaDirectoryService.updateCriteriaDirectory(criteriaDirectory, ID);
    }
    // Delete operation
    @DeleteMapping("/criteriaDirectories/{id}")
    public String deleteScaleParameter(@PathVariable("id") Long ID)
    {
        criteriaDirectoryService.deleteCriteriaDirectory(ID);
        return "Deleted Successfully";
    }
    @PostMapping("/criteriaDirectories/uploadCsv")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
        try {
            criteriaDirectoryService.loadFromCsv(file);
            return ResponseEntity.status(HttpStatus.OK).body("Файл успешно загружен");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка загрузки файла: " + e.getMessage());
        }
    }

    @GetMapping("/criteriaDirectories/downloadCsv")
    public ResponseEntity<byte[]> downloadCsv() {
        try {
            byte[] csvData = criteriaDirectoryService.exportToCsv();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=criteria_directories.csv");
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(csvData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
