package com.example.criteriamicroservice.service;


import com.example.criteriamicroservice.entity.CriteriaDirectory;
import com.example.criteriamicroservice.entity.CriteriaType;
import com.example.criteriamicroservice.entity.EvaluationScale;
import com.example.criteriamicroservice.entity.LifecycleStage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CriteriaDirectoryService {
    CriteriaDirectory saveCriteriaDirectory(CriteriaDirectory criteriaDirectory);
    List<CriteriaDirectory> fetchCriteriaDirectoryList();
    List<CriteriaDirectory> findByEvaluationScale(EvaluationScale scaleParam);
    List<CriteriaDirectory> findByCriteriaType(CriteriaType criteriaType);
    List<CriteriaDirectory> findByLifecycleStage(LifecycleStage lifecycleStage);
    List<CriteriaDirectory> findByScaleAndType(EvaluationScale scaleParam, CriteriaType criteriaType);
    List<CriteriaDirectory> findByScaleAndStage(EvaluationScale scale, LifecycleStage lifecycleStage);
    List<CriteriaDirectory> findByTypeAndStage(CriteriaType criteriaType, LifecycleStage lifecycleStage);
    List<CriteriaDirectory> findByCombined(EvaluationScale evaluationScale, LifecycleStage lifecycleStage, CriteriaType criteriaType);
    CriteriaDirectory findCriteriaDirectoryById(Long id);
    CriteriaDirectory updateCriteriaDirectory(CriteriaDirectory criteriaDirectory, Long id);
    void deleteCriteriaDirectory(CriteriaDirectory criteriaDirectory);
    void loadFromCsv(MultipartFile file) throws Exception;

    byte[] exportToCsv() throws Exception;
}
