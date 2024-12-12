package com.example.criteriamicroservice.controller;

import com.example.criteriamicroservice.entity.*;
import com.example.criteriamicroservice.service.CriteriaService;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

@RestController
public class CriteriaController {
    @Autowired
    private CriteriaService criteriaService;
    @PostMapping("/criterion")
    public Criteria saveCriteria(@RequestBody Criteria criteria)
    {
        return criteriaService.saveCriteria(criteria);
    }
    @GetMapping("/criterion")
    public List<Criteria> fetchCriteriaList()
    {
        return criteriaService.fetchCriteriaList();
    }
    @GetMapping("/criterion/{id}")
    public Criteria findCriteriaById(@PathVariable("id") Long id) {
        return criteriaService.findCriteriaById(id);
    }
    @GetMapping("/criterion/{directoryId}")
    public List<Criteria> findByDirectory(@PathVariable Long directoryId) {
        CriteriaDirectory criteriaDirectory = new CriteriaDirectory();
        criteriaDirectory.setId(directoryId);
        return criteriaService.findByCriteriaDirectory(criteriaDirectory);
    }
    // Update operation
    @PutMapping("/criterion/{id}")
    public Criteria updateCriteriaDirectory(@RequestBody Criteria criteria, @PathVariable("id") Long ID)
    {
        return criteriaService.updateCriteria(criteria, ID);
    }
    // Delete operation
    @DeleteMapping("/criterion")
    public String deleteScaleParameter(@PathVariable("id") Long ID)
    {
        Criteria criteria = criteriaService.findCriteriaById(ID);
        criteriaService.deleteCriteria(criteria);
        return "Deleted Successfully";
    }
    @PostMapping("/criterion/upload")
    public ResponseEntity<String> uploadCriteria(@RequestParam("file") MultipartFile file) {
        try {
            criteriaService.uploadCriteriaFromCsv(file.getInputStream());
            return ResponseEntity.ok("Criteria uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error reading CSV file: " + e.getMessage());
        }
    }

    @GetMapping("/criterion/download")
    public void downloadCriteria(HttpServletResponse response) {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=criteria.csv");
        try {
            criteriaService.downloadCriteriaToCsv(response.getWriter());
        } catch (IOException e) {
            throw new RuntimeException("Error writing CSV file: " + e.getMessage());
        }
    }
}
