package com.example.criteriamicroservice.service;

import com.example.criteriamicroservice.entity.*;
import com.example.criteriamicroservice.repository.SurveyResultRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SurveyResultServiceImpl implements SurveyResultService {
    @Autowired
    private SurveyResultRepository surveyResultRepository;

    @Override
    public SurveyResult saveSurveyResult(SurveyResult surveyResult) {
        return surveyResultRepository.save(surveyResult);
    }

    @Override
    public List<SurveyResult> fetchSurveyResultList() {
        return (List<SurveyResult>) surveyResultRepository.findAll();
    }

    @Override
    public List<SurveyResult> findBySurvey(Survey survey) {
        return surveyResultRepository.findAll()
                .stream()
                .filter(surveyResult -> surveyResult.getSurvey().equals(survey))
                .collect(Collectors.toList());
    }

    @Override
    public List<SurveyResult> findByQuestion(SurveyQuestion surveyQuestion) {
        return surveyResultRepository.findAll()
                .stream()
                .filter(surveyResult -> surveyResult.getQuestion().equals(surveyQuestion))
                .collect(Collectors.toList());
    }

    @Override
    public List<SurveyResult> findByRespondent(Respondent respondent) {
        return surveyResultRepository.findAll()
                .stream()
                .filter(surveyResult -> surveyResult.getRespondent().equals(respondent))
                .collect(Collectors.toList());
    }

    @Override
    public List<SurveyResult> findByScaleParameter(ScaleParameter scaleParameter) {
        return surveyResultRepository.findAll()
                .stream()
                .filter(surveyResult -> surveyResult.getSelectedParameter().equals(scaleParameter))
                .collect(Collectors.toList());
    }

    @Override
    public List<SurveyResult> findBySurveyAndQuestion(Survey survey, SurveyQuestion surveyQuestion) {
        return surveyResultRepository.findAll()
                .stream()
                .filter(surveyResult -> surveyResult.getSurvey().equals(survey) &&
                        surveyResult.getQuestion().equals(surveyQuestion))
                .collect(Collectors.toList());
    }

    @Override
    public List<SurveyResult> findBySurveyAndRespondent(Survey survey, Respondent respondent) {
        return surveyResultRepository.findAll()
                .stream()
                .filter(surveyResult -> surveyResult.getSurvey().equals(survey) &&
                        surveyResult.getRespondent().equals(respondent))
                .collect(Collectors.toList());
    }

    @Override
    public List<SurveyResult> findBySurveyAndScaleParameter(Survey survey, ScaleParameter scaleParameter) {
        return surveyResultRepository.findAll()
                .stream()
                .filter(surveyResult -> surveyResult.getSurvey().equals(survey) &&
                        surveyResult.getSelectedParameter().equals(scaleParameter))
                .collect(Collectors.toList());
    }

    @Override
    public List<SurveyResult> findByQuestionAndRespondent(SurveyQuestion surveyQuestion, Respondent respondent) {
        return surveyResultRepository.findAll()
                .stream()
                .filter(surveyResult -> surveyResult.getQuestion().equals(surveyQuestion) &&
                        surveyResult.getRespondent().equals(respondent))
                .collect(Collectors.toList());
    }

    @Override
    public List<SurveyResult> findByQuestionAndScaleParameter(SurveyQuestion surveyQuestion, ScaleParameter scaleParameter) {
        return surveyResultRepository.findAll()
                .stream()
                .filter(surveyResult -> surveyResult.getQuestion().equals(surveyQuestion) &&
                        surveyResult.getSelectedParameter().equals(scaleParameter))
                .collect(Collectors.toList());
    }

    @Override
    public List<SurveyResult> findByRespondentAndScaleParameter(Respondent respondent, ScaleParameter scaleParameter) {
        return surveyResultRepository.findAll()
                .stream()
                .filter(surveyResult -> surveyResult.getRespondent().equals(respondent) &&
                        surveyResult.getSelectedParameter().equals(scaleParameter))
                .collect(Collectors.toList());
    }

    @Override
    public List<SurveyResult> findBySurveyAndQuestionAndRespondent(Survey survey, SurveyQuestion surveyQuestion, Respondent respondent) {
        return surveyResultRepository.findAll()
                .stream()
                .filter(surveyResult -> surveyResult.getSurvey().equals(survey) &&
                        surveyResult.getQuestion().equals(surveyQuestion) &&
                        surveyResult.getRespondent().equals(respondent))
                .collect(Collectors.toList());
    }

    @Override
    public List<SurveyResult> findBySurveyAndQuestionAndScaleParameter(Survey survey, SurveyQuestion surveyQuestion, ScaleParameter scaleParameter) {
        return surveyResultRepository.findAll()
                .stream()
                .filter(surveyResult -> surveyResult.getSurvey().equals(survey) &&
                        surveyResult.getQuestion().equals(surveyQuestion) &&
                        surveyResult.getSelectedParameter().equals(scaleParameter))
                .collect(Collectors.toList());
    }

    @Override
    public List<SurveyResult> findBySurveyAndRespondentAndScaleParameter(Survey survey, Respondent respondent, ScaleParameter scaleParameter) {
        return surveyResultRepository.findAll()
                .stream()
                .filter(surveyResult -> surveyResult.getSurvey().equals(survey) &&
                        surveyResult.getRespondent().equals(respondent) &&
                        surveyResult.getSelectedParameter().equals(scaleParameter))
                .collect(Collectors.toList());
    }

    @Override
    public List<SurveyResult> findByQuestionAndRespondentAndScaleParameter(SurveyQuestion surveyQuestion, Respondent respondent, ScaleParameter scaleParameter) {
        return surveyResultRepository.findAll()
                .stream()
                .filter(surveyResult -> surveyResult.getQuestion().equals(surveyQuestion) &&
                        surveyResult.getRespondent().equals(respondent) &&
                        surveyResult.getSelectedParameter().equals(scaleParameter))
                .collect(Collectors.toList());
    }

    @Override
    public List<SurveyResult> findBySurveyAndQuestionAndRespondentAndScaleParameter(Survey survey, SurveyQuestion surveyQuestion, Respondent respondent, ScaleParameter scaleParameter) {
        return surveyResultRepository.findAll()
                .stream()
                .filter(surveyResult -> surveyResult.getSurvey().equals(survey) &&
                        surveyResult.getQuestion().equals(surveyQuestion) &&
                        surveyResult.getRespondent().equals(respondent) &&
                        surveyResult.getSelectedParameter().equals(scaleParameter))
                .collect(Collectors.toList());
    }

    @Override
    public SurveyResult findSurveyResultById(Long id) {
        SurveyResult surveyResultDB = surveyResultRepository.findById(id).orElse(null);
        return surveyResultDB;
    }

    @Override
    public SurveyResult updateSurveyResult(SurveyResult surveyResult, Long id) {
        SurveyResult surveyResultDB = surveyResultRepository.findById(id).orElse(null);
        if (Objects.nonNull(surveyResultDB)) {
            surveyResultDB.setSurvey(surveyResult.getSurvey());
            surveyResultDB.setQuestion(surveyResult.getQuestion());
            surveyResultDB.setRespondent(surveyResult.getRespondent());
            surveyResultDB.setVerdict(surveyResult.getVerdict());
            surveyResultDB.setSelectedParameter(surveyResult.getSelectedParameter());
            surveyResultDB.setInProgress(surveyResult.getInProgress());
            return surveyResultRepository.save(surveyResultDB);
        } else {
            return null;
        }
    }

    @Override
    public void deleteSurveyResult(Long id) {
        surveyResultRepository.deleteById(id);
    }
    @Override
    public void downloadSurveyResultsToCsv(Writer writer) {
        try {
            List<SurveyResult> surveyResults = surveyResultRepository.findAll();

            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader("id", "surveyId", "respondentId", "criteriaID", "evaluationParameterID", "verdict"));

            for (SurveyResult result : surveyResults) {
                csvPrinter.printRecord(
                        result.getId(),
                        result.getSurvey().getId(),
                        result.getRespondent().getId(),
                        result.getQuestion().getCriteria().getId(),
                        result.getSelectedParameter().getScaleParam().getId(),
                        result.getVerdict()
                );
            }

            csvPrinter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Error writing Survey Results to CSV: " + e.getMessage(), e);
        }
    }
}
