package com.example.criteriamicroservice.service;

import com.example.criteriamicroservice.entity.Respondent;
import com.example.criteriamicroservice.entity.Survey;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface SurveyService {
    Survey saveSurvey(Survey survey);
    List<Survey> fetchSurveyList();
    Survey findSurveyById(Long id);
    Survey updateSurvey(Survey survey, Long id);
    void deleteSurvey(Long id);
    void uploadSurveysFromCsv(InputStream csvInputStream);
}
