package com.example.criteriamicroservice.service;

import com.example.criteriamicroservice.entity.EvaluationParameter;
import com.example.criteriamicroservice.entity.EvaluationScale;
import com.example.criteriamicroservice.entity.ScaleParameter;
import com.example.criteriamicroservice.repository.ScaleParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ScaleParameterServiceImpl implements ScaleParameterService {
    @Autowired
    private ScaleParameterRepository scaleParameterRepository;

    @Override
    public ScaleParameter saveScaleParameter(ScaleParameter scaleParameter) {
        return scaleParameterRepository.save(scaleParameter);
    }

    @Override
    public List<ScaleParameter> fetchScaleParameterList() {
        return (List<ScaleParameter>) scaleParameterRepository.findAll();
    }

    @Override
    public List<ScaleParameter> findByEvaluationScale(EvaluationScale scaleParam) {
        return scaleParameterRepository.findAll()
                .stream()
                .filter(scaleParameter -> scaleParameter.getScaleParam().equals(scaleParam))
                .collect(Collectors.toList());
    }

    @Override
    public List<ScaleParameter> findByEvaluationParameter(EvaluationParameter parameter) {
        return scaleParameterRepository.findAll()
                .stream()
                .filter(scaleParameter -> scaleParameter.getScaleEvParam().equals(parameter))
                .collect(Collectors.toList());
    }

    @Override
    public List<ScaleParameter> findByScaleAndParameter(EvaluationScale scaleParam, EvaluationParameter parameter) {
        return scaleParameterRepository.findAll()
                .stream()
                .filter(scaleParameter -> scaleParameter.getScaleParam().equals(scaleParam) &&
                        scaleParameter.getScaleEvParam().equals(parameter))
                .collect(Collectors.toList());
    }

    @Override
    public ScaleParameter findScaleParameterById(Long id) {
        ScaleParameter scaleParameterDB = scaleParameterRepository.findById(id).orElse(null);
        return scaleParameterDB;
    }

    @Override
    public ScaleParameter updateScaleParameter(ScaleParameter scaleParameter, Long id) {
        ScaleParameter scaleParameterDB = scaleParameterRepository.findById(id).orElse(null);
        if (Objects.nonNull(scaleParameterDB)) {
            scaleParameterDB.setScaleEvParam(scaleParameter.getScaleEvParam());
            scaleParameterDB.setScaleParam(scaleParameter.getScaleParam());
            scaleParameterDB.setParam_order(scaleParameter.getParam_order());;
            scaleParameterDB.setSurveyResults(scaleParameter.getSurveyResults());
            return scaleParameterRepository.save(scaleParameterDB);
        } else {
            return null;
        }
    }

    @Override
    public void deleteScaleParameter(ScaleParameter scaleParameter) {
        scaleParameterRepository.delete(scaleParameter);
    }
}
