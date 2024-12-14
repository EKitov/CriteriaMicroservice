package com.example.criteriamicroservice.service;

import com.example.criteriamicroservice.entity.EvaluationParameter;

import java.util.List;

public interface EvaluationParameterService {
    EvaluationParameter saveEvaluationParameter(EvaluationParameter evaluationParameter);
    List<EvaluationParameter> fetchEvaluationParameterList();
    EvaluationParameter findEvaluationParameterById(Long id);
    EvaluationParameter updateEvaluationParameter(EvaluationParameter evaluationParameter, Long id);
    void deleteEvaluationParameter(Long id);
}
