package com.example.criteriamicroservice.controller;

import com.example.criteriamicroservice.entity.Respondent;
import com.example.criteriamicroservice.entity.Survey;
import com.example.criteriamicroservice.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class SurveyController {
    @Autowired
    private SurveyService surveyService;
    @PostMapping("/surveys")
    public Survey saveSurvey(@RequestBody Survey survey)
    {
        return surveyService.saveSurvey(survey);
    }
    @GetMapping("/surveys")
    public List<Survey> fetchSurvey()
    {
        return surveyService.fetchSurveyList();
    }
    @GetMapping("/surveys/{id}")
    public Survey findSurveyById(@PathVariable("id") Long id) {
        return surveyService.findSurveyById(id);
    }
    // Update operation
    @PutMapping("/surveys/{id}")
    public Survey updateSurvey(@RequestBody Survey survey, @PathVariable("id") Long ID)
    {
        return surveyService.updateSurvey(survey, ID);
    }
    // Delete operation
    @DeleteMapping("/surveys/{id}")
    public String deleteSurvey(@PathVariable("id") Long ID)
    {
        surveyService.deleteSurvey(ID);
        return "Deleted Successfully";
    }
    @PostMapping("/surveys/upload")
    public ResponseEntity<String> uploadSurveys(@RequestParam("file") MultipartFile file) {
        try {
            surveyService.uploadSurveysFromCsv(file.getInputStream());
            return ResponseEntity.ok("Surveys uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error reading CSV file: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
