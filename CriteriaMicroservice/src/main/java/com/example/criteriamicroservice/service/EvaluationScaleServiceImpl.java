package com.example.criteriamicroservice.service;

import com.example.criteriamicroservice.entity.EvaluationScale;
import com.example.criteriamicroservice.entity.LifecycleStage;
import com.example.criteriamicroservice.repository.EvaluationScaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EvaluationScaleServiceImpl implements EvaluationScaleService {
    @Autowired
    private EvaluationScaleRepository evaluationScaleRepository;

    @Override
    public EvaluationScale saveEvaluationScale(EvaluationScale evaluationScale) {
        return evaluationScaleRepository.save(evaluationScale);
    }

    @Override
    public List<EvaluationScale> fetchEvaluationScaleList() {
        return (List<EvaluationScale>) evaluationScaleRepository.findAll();
    }

    @Override
    public EvaluationScale findEvaluationScaleById(Long id) {
        EvaluationScale evaluationScaleDB = evaluationScaleRepository.findById(id).orElse(null);
        return evaluationScaleDB;
    }

    @Override
    public EvaluationScale updateEvaluationScale(EvaluationScale evaluationScale, Long id) {
        EvaluationScale evaluationScaleDB = evaluationScaleRepository.findById(id).orElse(null);
        if (Objects.nonNull(evaluationScaleDB)) {
            evaluationScaleDB.setName(evaluationScale.getName());
            evaluationScaleDB.setScaleParameters(evaluationScale.getScaleParameters());
            evaluationScaleDB.setCriteriaDirectories(evaluationScale.getCriteriaDirectories());
            return evaluationScaleRepository.save(evaluationScaleDB);
        } else {
            return null;
        }
    }

    @Override
    public void deleteEvaluationScale(Long id) {
        evaluationScaleRepository.deleteById(id);
    }
}
