package com.example.criteriamicroservice.service;

import com.example.criteriamicroservice.entity.Criteria;
import com.example.criteriamicroservice.entity.Survey;
import com.example.criteriamicroservice.entity.SurveyQuestion;
import com.example.criteriamicroservice.repository.CriteriaRepository;
import com.example.criteriamicroservice.repository.SurveyQuestionRepository;
import com.example.criteriamicroservice.repository.SurveyRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SurveyQuestionServiceImpl implements SurveyQuestionService {
    @Autowired
    private SurveyQuestionRepository surveyQuestionRepository;
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private CriteriaRepository criteriaRepository;

    @Override
    public SurveyQuestion saveSurveyQuestion(SurveyQuestion surveyQuestion) {
        return surveyQuestionRepository.save(surveyQuestion);
    }

    @Override
    public List<SurveyQuestion> fetchSurveyQuestionList() {
        return (List<SurveyQuestion>) surveyQuestionRepository.findAll();
    }

    @Override
    public List<SurveyQuestion> findBySurvey(Survey survey) {
        return surveyQuestionRepository.findAll()
                .stream()
                .filter(surveyQuestion -> surveyQuestion.getSurvey().equals(survey))
                .collect(Collectors.toList());
    }

    @Override
    public List<SurveyQuestion> findByCriteria(Criteria criteria) {
        return surveyQuestionRepository.findAll()
                .stream()
                .filter(surveyQuestion -> surveyQuestion.getCriteria().equals(criteria))
                .collect(Collectors.toList());
    }

    @Override
    public List<SurveyQuestion> findBySurveyAndCriteria(Survey survey, Criteria criteria) {
        return surveyQuestionRepository.findAll()
                .stream()
                .filter(surveyQuestion -> surveyQuestion.getCriteria().equals(criteria) &&
                        surveyQuestion.getSurvey().equals(survey))
                .collect(Collectors.toList());
    }

    @Override
    public SurveyQuestion findSurveyQuestionById(Long id) {
        SurveyQuestion surveyQuestionDB = surveyQuestionRepository.findById(id).orElse(null);
        return surveyQuestionDB;
    }

    @Override
    public SurveyQuestion updateSurveyQuestion(SurveyQuestion surveyQuestion, Long id) {
        SurveyQuestion surveyQuestionDB = surveyQuestionRepository.findById(id).orElse(null);
        if (Objects.nonNull(surveyQuestionDB)) {
            surveyQuestionDB.setResults(surveyQuestion.getResults());
            surveyQuestionDB.setSurvey(surveyQuestion.getSurvey());
            surveyQuestionDB.setQuestionOrder(surveyQuestion.getQuestionOrder());
            surveyQuestionDB.setCriteria(surveyQuestion.getCriteria());
            surveyQuestionDB.setAdditionalDetails(surveyQuestion.getAdditionalDetails());
            return surveyQuestionRepository.save(surveyQuestionDB);
        } else {
            return null;
        }
    }

    @Override
    public void deleteSurveyQuestion(Long id) {
        surveyQuestionRepository.deleteById(id);
    }
    @Override
    public void uploadSurveyQuestionsFromCsv(InputStream csvInputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(csvInputStream))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader("surveyId", "criteriaId", "questionOrder", "additionalDetails")
                    .withSkipHeaderRecord(true)
                    .parse(reader);

            List<SurveyQuestion> surveyQuestions = new ArrayList<>();
            for (CSVRecord record : records) {
                Long surveyId = Long.parseLong(record.get("surveyId"));
                Long criteriaId = Long.parseLong(record.get("criteriaId"));

                Survey survey = surveyRepository.findById(surveyId)
                        .orElseThrow(() -> new IllegalArgumentException("Survey с ID " + surveyId + " не найден!"));
                Criteria criteria = criteriaRepository.findById(criteriaId)
                        .orElseThrow(() -> new IllegalArgumentException("Criteria с ID " + criteriaId + " не найден!"));
                // Создаем и заполняем объект SurveyQuestion
                SurveyQuestion question = new SurveyQuestion();
                question.setSurvey(survey);
                question.setCriteria(criteria);
                question.setQuestionOrder(Integer.parseInt(record.get("questionOrder")));
                question.setAdditionalDetails(record.get("additionalDetails"));


                surveyQuestions.add(question);
            }

            surveyQuestionRepository.saveAll(surveyQuestions);
        } catch (IOException | IllegalArgumentException e) {
            throw new RuntimeException("Error uploading survey questions from CSV: " + e.getMessage(), e);
        }
    }
}
