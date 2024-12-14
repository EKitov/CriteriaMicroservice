package com.example.criteriamicroservice.service;

import com.example.criteriamicroservice.entity.*;

import java.io.InputStream;
import java.util.List;

public interface SurveyQuestionService {
    SurveyQuestion saveSurveyQuestion(SurveyQuestion surveyQuestion);
    List<SurveyQuestion> fetchSurveyQuestionList();
    List<SurveyQuestion> findBySurvey(Survey survey);
    List<SurveyQuestion> findByCriteria(Criteria criteria);
    List<SurveyQuestion> findBySurveyAndCriteria(Survey survey, Criteria criteria);
    SurveyQuestion findSurveyQuestionById(Long id);
    SurveyQuestion updateSurveyQuestion(SurveyQuestion surveyQuestion, Long id);
    void deleteSurveyQuestion(Long id);
    void uploadSurveyQuestionsFromCsv(InputStream csvInputStream);
}
