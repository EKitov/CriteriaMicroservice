package com.example.criteriamicroservice.service;

import com.example.criteriamicroservice.entity.EvaluationParameter;
import com.example.criteriamicroservice.repository.EvaluationParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EvaluationParameterServiceImpl implements EvaluationParameterService {
    @Autowired
    private EvaluationParameterRepository evaluationParameterRepository;

    @Override
    public EvaluationParameter saveEvaluationParameter(EvaluationParameter evaluationParameter) {
        return evaluationParameterRepository.save(evaluationParameter);
    }

    @Override
    public List<EvaluationParameter> fetchEvaluationParameterList() {
        return (List<EvaluationParameter>) evaluationParameterRepository.findAll();
    }

    @Override
    public EvaluationParameter findEvaluationParameterById(Long id) {
        EvaluationParameter evaluationParameterDB = evaluationParameterRepository.findById(id).orElse(null);
        return evaluationParameterDB;
    }

    @Override
    public EvaluationParameter updateEvaluationParameter(EvaluationParameter evaluationParameter, Long id) {
        EvaluationParameter evaluationParameterDB = evaluationParameterRepository.findById(id).orElse(null);
        if (Objects.nonNull(evaluationParameterDB)) {
            evaluationParameterDB.setValue(evaluationParameter.getValue());
            evaluationParameterDB.setDescription(evaluationParameter.getDescription());
            evaluationParameterDB.setScaleParameters(evaluationParameter.getScaleParameters());
            return evaluationParameterRepository.save(evaluationParameterDB);
        } else {
            return null;
        }
    }

    @Override
    public void deleteEvaluationParameter(EvaluationParameter evaluationParameter) {
        evaluationParameterRepository.delete(evaluationParameter);
    }
}
