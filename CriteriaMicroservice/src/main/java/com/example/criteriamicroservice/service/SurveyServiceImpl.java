package com.example.criteriamicroservice.service;

import com.example.criteriamicroservice.entity.Respondent;
import com.example.criteriamicroservice.entity.Survey;
import com.example.criteriamicroservice.entity.SurveyStatus;
import com.example.criteriamicroservice.repository.SurveyRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SurveyServiceImpl implements SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;

    @Override
    public Survey saveSurvey(Survey survey) {
        return  surveyRepository.save(survey);
    }

    @Override
    public List<Survey> fetchSurveyList() {
        return (List<Survey>) surveyRepository.findAll();
    }

    @Override
    public Survey findSurveyById(Long id) {
        Survey surveyDB = surveyRepository.findById(id).orElse(null);
        return surveyDB;
    }

    @Override
    public Survey updateSurvey(Survey survey, Long id) {
        Survey surveyDB = surveyRepository.findById(id).orElse(null);
        if (Objects.nonNull(surveyDB)) {
            surveyDB.setDescription(survey.getDescription());
            surveyDB.setQuestions(survey.getQuestions());
            surveyDB.setResults(survey.getResults());
            surveyDB.setTitle(survey.getTitle());
            return surveyRepository.save(surveyDB);
        } else {
            return null;
        }
    }

    @Override
    public void deleteSurvey(Long id) {
        surveyRepository.deleteById(id);
    }
    @Override
    public void uploadSurveysFromCsv(InputStream csvInputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(csvInputStream))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader("title", "description", "status")
                    .withSkipHeaderRecord()
                    .parse(reader);

            List<Survey> surveyList = new ArrayList<>();
            for (CSVRecord record : records) {
                String title = record.get("title");
                String description = record.get("description");
                String status = record.get("status");

                // Проверяем валидность статуса
                SurveyStatus surveyStatus = SurveyStatus.valueOf(status.toUpperCase());

                // Создаем и заполняем объект Survey
                Survey survey = new Survey();
                survey.setTitle(title);
                survey.setDescription(description);
                survey.setStatus(surveyStatus);

                surveyList.add(survey);
            }

            surveyRepository.saveAll(surveyList);
        } catch (IOException | IllegalArgumentException e) {
            throw new RuntimeException("Error uploading surveys from CSV: " + e.getMessage(), e);
        }
    }
}
