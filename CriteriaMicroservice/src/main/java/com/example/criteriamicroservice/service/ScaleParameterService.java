package com.example.criteriamicroservice.service;

import com.example.criteriamicroservice.entity.EvaluationParameter;
import com.example.criteriamicroservice.entity.EvaluationScale;
import com.example.criteriamicroservice.entity.ScaleParameter;

import java.util.List;

public interface ScaleParameterService {
    ScaleParameter saveScaleParameter(ScaleParameter scaleParameter);
    List<ScaleParameter> fetchScaleParameterList();
    List<ScaleParameter> findByEvaluationScale(EvaluationScale scaleParam);
    List<ScaleParameter> findByEvaluationParameter(EvaluationParameter parameter);
    List<ScaleParameter> findByScaleAndParameter(EvaluationScale scaleParam, EvaluationParameter parameter);
    ScaleParameter findScaleParameterById(Long id);
    ScaleParameter updateScaleParameter(ScaleParameter scaleParameter, Long id);
    void deleteScaleParameter(ScaleParameter scaleParameter);
}
