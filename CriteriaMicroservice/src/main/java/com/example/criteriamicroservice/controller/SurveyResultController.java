package com.example.criteriamicroservice.controller;

import com.example.criteriamicroservice.entity.*;
import com.example.criteriamicroservice.service.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class SurveyResultController {
    @Autowired
    private SurveyResultService surveyResultService;
    @Autowired
    private RespondentService respondentService;
    @Autowired
    private SurveyService surveyService;
    @Autowired
    private SurveyQuestionService surveyQuestionService;
    @Autowired
    private ScaleParameterService scaleParameterService;
    @PostMapping("/surveyResults")
    public SurveyResult saveSurveyResult(@RequestBody SurveyResult surveyResult)
    {
        return surveyResultService.saveSurveyResult(surveyResult);
    }
    @GetMapping("/surveyResults")
    public List<SurveyResult> fetchSurveyResultList()
    {
        return surveyResultService.fetchSurveyResultList();
    }
    @GetMapping("/surveyResults/{id}")
    public SurveyResult findSurveyResultById(@PathVariable("id") Long id) {
        return surveyResultService.findSurveyResultById(id);
    }
    @GetMapping("/surveyResultsBySurvey/{surveyId}")
    public List<SurveyResult> findBySurvey(@PathVariable Long surveyId) {
        Survey survey = surveyService.findSurveyById(surveyId);
        return surveyResultService.findBySurvey(survey);
    }
    @GetMapping("/surveyResultsByQuestion/{questionId}")
    public List<SurveyResult> getByQuestion(@PathVariable Long questionId) {
        SurveyQuestion surveyQuestion = surveyQuestionService.findSurveyQuestionById(questionId);
        return surveyResultService.findByQuestion(surveyQuestion);
    }
    @GetMapping("/surveyResultsByRespondent/{respondentId}")
    public List<SurveyResult> getByRespondent(@PathVariable Long respondentId) {
        Respondent respondent = respondentService.findRespondentById(respondentId);
        return surveyResultService.findByRespondent(respondent);
    }
    @GetMapping("/surveyResultsByScaleParameter/{parameterId}")
    public List<SurveyResult> getByScaleParameter(@PathVariable Long parameterId) {
        ScaleParameter scaleParameter = scaleParameterService.findScaleParameterById(parameterId);
        return surveyResultService.findByScaleParameter(scaleParameter);
    }
    @GetMapping("/surveyResultsBySurveyAndQuestion")
    public List<SurveyResult> getBySurveyAndQuestion(
            @PathVariable Long surveyId,
            @PathVariable Long questionId) {
        Survey survey = surveyService.findSurveyById(surveyId);
        SurveyQuestion surveyQuestion = surveyQuestionService.findSurveyQuestionById(questionId);
        return surveyResultService.findBySurveyAndQuestion(survey, surveyQuestion);
    }
    @GetMapping("/surveyResultsBySurveyAndRespondent")
    public List<SurveyResult> getBySurveyAndRespondent(
            @PathVariable Long surveyId,
            @PathVariable Long respondentId) {
        Survey survey = surveyService.findSurveyById(surveyId);
        Respondent respondent = respondentService.findRespondentById(respondentId);
        return surveyResultService.findBySurveyAndRespondent(survey, respondent);
    }
    @GetMapping("/surveyResultsBySurveyAndScaleParameter")
    public List<SurveyResult> getBySurveyAndScaleParameter(
            @PathVariable Long surveyId,
            @PathVariable Long parameterId) {
        Survey survey = surveyService.findSurveyById(surveyId);
        ScaleParameter scaleParameter = scaleParameterService.findScaleParameterById(parameterId);
        return surveyResultService.findBySurveyAndScaleParameter(survey, scaleParameter);
    }
    @GetMapping("/surveyResultsByQuestionAndRespondent")
    public List<SurveyResult> getByQuestionAndRespondent(
            @PathVariable Long questionId,
            @PathVariable Long respondentId) {
        SurveyQuestion surveyQuestion = surveyQuestionService.findSurveyQuestionById(questionId);
        Respondent respondent = respondentService.findRespondentById(respondentId);
        return surveyResultService.findByQuestionAndRespondent(surveyQuestion, respondent);
    }
    @GetMapping("/surveyResultsByQuestionAndScaleParameter")
    public List<SurveyResult> getByQuestionAndScaleParameter(
            @PathVariable Long questionId,
            @PathVariable Long parameterId) {
        SurveyQuestion surveyQuestion = surveyQuestionService.findSurveyQuestionById(questionId);
        ScaleParameter scaleParameter = scaleParameterService.findScaleParameterById(parameterId);
        return surveyResultService.findByQuestionAndScaleParameter(surveyQuestion, scaleParameter);
    }
    @GetMapping("/surveyResultsByRespondentAndScaleParameter")
    public List<SurveyResult> getByRespondentAndScaleParameter(
            @PathVariable Long respondentId,
            @PathVariable Long parameterId) {
        Respondent respondent = respondentService.findRespondentById(respondentId);
        ScaleParameter scaleParameter = scaleParameterService.findScaleParameterById(parameterId);
        return surveyResultService.findByRespondentAndScaleParameter(respondent, scaleParameter);
    }
    @GetMapping("/surveyResultsBySurveyAndQuestionAndRespondent")
    public List<SurveyResult> getBySurveyAndQuestionAndRespondent(
            @PathVariable Long surveyId,
            @PathVariable Long questionId,
            @PathVariable Long respondentId) {
        Survey survey = surveyService.findSurveyById(surveyId);
        SurveyQuestion surveyQuestion = surveyQuestionService.findSurveyQuestionById(questionId);
        Respondent respondent = respondentService.findRespondentById(respondentId);
        return surveyResultService.findBySurveyAndQuestionAndRespondent(survey, surveyQuestion, respondent);
    }
    @GetMapping("/surveyResultsBySurveyAndQuestionAndScaleParameter")
    public List<SurveyResult> getBySurveyAndQuestionAndScaleParameter(
            @PathVariable Long surveyId,
            @PathVariable Long questionId,
            @PathVariable Long parameterId) {
        Survey survey = surveyService.findSurveyById(surveyId);
        SurveyQuestion surveyQuestion = surveyQuestionService.findSurveyQuestionById(questionId);
        ScaleParameter scaleParameter = scaleParameterService.findScaleParameterById(parameterId);
        return surveyResultService.findBySurveyAndQuestionAndScaleParameter(survey, surveyQuestion, scaleParameter);
    }
    @GetMapping("/surveyResultsBySurveyAndRespondentAndScaleParameter")
    public List<SurveyResult> getBySurveyAndRespondentAndScaleParameter(
            @PathVariable Long surveyId,
            @PathVariable Long respondentId,
            @PathVariable Long parameterId) {
        Survey survey = surveyService.findSurveyById(surveyId);
        Respondent respondent = respondentService.findRespondentById(respondentId);
        ScaleParameter scaleParameter = scaleParameterService.findScaleParameterById(parameterId);
        return surveyResultService.findBySurveyAndRespondentAndScaleParameter(survey, respondent, scaleParameter);
    }
    @GetMapping("/surveyResultsByQuestionAndRespondentAndScaleParameter")
    public List<SurveyResult> getByQuestionAndRespondentAndScaleParameter(
            @PathVariable Long questionId,
            @PathVariable Long respondentId,
            @PathVariable Long parameterId) {
        SurveyQuestion surveyQuestion = surveyQuestionService.findSurveyQuestionById(questionId);
        Respondent respondent = respondentService.findRespondentById(respondentId);
        ScaleParameter scaleParameter = scaleParameterService.findScaleParameterById(parameterId);
        return surveyResultService.findByQuestionAndRespondentAndScaleParameter(surveyQuestion, respondent, scaleParameter);
    }
    @GetMapping("/surveyResultsBySurveyAndQuestionAndRespondentAndScaleParameter")
    public List<SurveyResult> getBySurveyAndQuestionAndRespondentAndScaleParameter(
            @PathVariable Long surveyId,
            @PathVariable Long questionId,
            @PathVariable Long respondentId,
            @PathVariable Long parameterId) {
        Survey survey = surveyService.findSurveyById(surveyId);
        SurveyQuestion surveyQuestion = surveyQuestionService.findSurveyQuestionById(questionId);
        Respondent respondent = respondentService.findRespondentById(respondentId);
        ScaleParameter scaleParameter = scaleParameterService.findScaleParameterById(parameterId);
        return surveyResultService.findBySurveyAndQuestionAndRespondentAndScaleParameter(survey, surveyQuestion, respondent, scaleParameter);
    }
    // Update operation
    @PutMapping("/surveyResults/{id}")
    public SurveyResult updateSurveyQuestion(@RequestBody SurveyResult surveyResult, @PathVariable("id") Long ID)
    {
        return surveyResultService.updateSurveyResult(surveyResult, ID);
    }
    // Delete operation
    @DeleteMapping("/surveyResults/{id}")
    public String deleteSurveyResult(@PathVariable("id") Long ID)
    {
        surveyResultService.deleteSurveyResult(ID);
        return "Deleted Successfully";
    }
    @GetMapping("/surveyResults/download")
    public void downloadSurveyResults(HttpServletResponse response) {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=survey_results.csv");
        try {
            surveyResultService.downloadSurveyResultsToCsv(response.getWriter());
        } catch (IOException e) {
            throw new RuntimeException("Error writing CSV file: " + e.getMessage());
        }
    }
}
