package com.example.criteriamicroservice.controller;

import com.example.criteriamicroservice.entity.*;
import com.example.criteriamicroservice.service.CriteriaService;
import com.example.criteriamicroservice.service.SurveyQuestionService;
import com.example.criteriamicroservice.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class SurveyQuestionController {
    @Autowired
    private SurveyQuestionService surveyQuestionService;
    @Autowired
    private SurveyService surveyService;
    @Autowired
    private CriteriaService criteriaService;
    @PostMapping("/surveyQuestions")
    public SurveyQuestion saveSurveyQuestion(@RequestBody SurveyQuestion surveyQuestion)
    {
        return surveyQuestionService.saveSurveyQuestion(surveyQuestion);
    }
    @GetMapping("/surveyQuestions")
    public List<SurveyQuestion> fetchSurveyQuestionList()
    {
        return surveyQuestionService.fetchSurveyQuestionList();
    }
    @GetMapping("/surveyQuestions/{id}")
    public SurveyQuestion findSurveyQuestionById(@PathVariable("id") Long id) {
        return surveyQuestionService.findSurveyQuestionById(id);
    }
    @GetMapping("/surveyQuestionsBySurvey/{surveyId}")
    public List<SurveyQuestion> findBySurvey(@PathVariable Long surveyId) {
        Survey survey = surveyService.findSurveyById(surveyId);
        return surveyQuestionService.findBySurvey(survey);
    }
    @GetMapping("/surveyQuestionsByCriteria/{criteriaId}")
    public List<SurveyQuestion> getByCriteria(@PathVariable Long criteriaId) {
        Criteria criteria = criteriaService.findCriteriaById(criteriaId);
        return surveyQuestionService.findByCriteria(criteria);
    }
    @GetMapping("/surveyQuestionsBySurveyAndCriteria")
    public List<SurveyQuestion> getBySurveyAndCriteria(
            @PathVariable Long surveyId,
            @PathVariable Long criteriaId) {
        Survey survey = surveyService.findSurveyById(surveyId);
        Criteria criteria = criteriaService.findCriteriaById(criteriaId);
        return surveyQuestionService.findBySurveyAndCriteria(survey, criteria);
    }
    // Update operation
    @PutMapping("/surveyQuestions/{id}")
    public SurveyQuestion updateSurveyQuestion(@RequestBody SurveyQuestion surveyQuestion, @PathVariable("id") Long ID)
    {
        return surveyQuestionService.updateSurveyQuestion(surveyQuestion, ID);
    }
    // Delete operation
    @DeleteMapping("/surveyQuestions/{id}")
    public String deleteSurveyQuestion(@PathVariable("id") Long ID)
    {
        surveyQuestionService.deleteSurveyQuestion(ID);
        return "Deleted Successfully";
    }
    // Загрузка SurveyQuestion из CSV
    @PostMapping("/surveyQuestions/upload")
    public ResponseEntity<String> uploadSurveyQuestions(@RequestParam("file") MultipartFile file) {
        try {
            surveyQuestionService.uploadSurveyQuestionsFromCsv(file.getInputStream());
            return ResponseEntity.ok("Survey questions uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error reading CSV file: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
