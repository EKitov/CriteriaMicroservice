package com.example.criteriamicroservice.service;

import com.example.criteriamicroservice.entity.*;

import java.io.Writer;
import java.util.List;

public interface SurveyResultService {
    SurveyResult saveSurveyResult(SurveyResult surveyResult);
    List<SurveyResult> fetchSurveyResultList();
    List<SurveyResult> findBySurvey(Survey survey);
    List<SurveyResult> findByQuestion(SurveyQuestion surveyQuestion);
    List<SurveyResult> findByRespondent(Respondent respondent);
    List<SurveyResult> findByScaleParameter(ScaleParameter scaleParameter);
    List<SurveyResult> findBySurveyAndQuestion(Survey survey, SurveyQuestion surveyQuestion);
    List<SurveyResult> findBySurveyAndRespondent(Survey survey, Respondent respondent);
    List<SurveyResult> findBySurveyAndScaleParameter(Survey survey, ScaleParameter scaleParameter);
    List<SurveyResult> findByQuestionAndRespondent(SurveyQuestion surveyQuestion, Respondent respondent);
    List<SurveyResult> findByQuestionAndScaleParameter(SurveyQuestion surveyQuestion, ScaleParameter scaleParameter);
    List<SurveyResult> findByRespondentAndScaleParameter(Respondent respondent, ScaleParameter scaleParameter);
    List<SurveyResult> findBySurveyAndQuestionAndRespondent(Survey survey, SurveyQuestion surveyQuestion, Respondent respondent);
    List<SurveyResult> findBySurveyAndQuestionAndScaleParameter(Survey survey, SurveyQuestion surveyQuestion, ScaleParameter scaleParameter);
    List<SurveyResult> findBySurveyAndRespondentAndScaleParameter(Survey survey, Respondent respondent, ScaleParameter scaleParameter);
    List<SurveyResult> findByQuestionAndRespondentAndScaleParameter(SurveyQuestion surveyQuestion, Respondent respondent, ScaleParameter scaleParameter);
    List<SurveyResult> findBySurveyAndQuestionAndRespondentAndScaleParameter(Survey survey, SurveyQuestion surveyQuestion, Respondent respondent, ScaleParameter scaleParameter);
    SurveyResult findSurveyResultById(Long id);
    SurveyResult updateSurveyResult(SurveyResult surveyResult, Long id);
    void deleteSurveyResult(Long id);
    void downloadSurveyResultsToCsv(Writer writer);
}
