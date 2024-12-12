package com.example.criteriamicroservice.service;

import com.example.criteriamicroservice.entity.EvaluationScale;

import java.util.List;
import java.util.Optional;

public interface EvaluationScaleService {
    EvaluationScale saveEvaluationScale(EvaluationScale evaluationScale);
    List<EvaluationScale> fetchEvaluationScaleList();
    EvaluationScale findEvaluationScaleById(Long id);
    EvaluationScale updateEvaluationScale(EvaluationScale evaluationScale, Long id);
    void deleteEvaluationScale(EvaluationScale evaluationScale);
}
