package com.example.criteriamicroservice.service;

import com.example.criteriamicroservice.entity.CriteriaType;

import java.util.List;

public interface CriteriaTypeService {
    CriteriaType saveCriteriaType(CriteriaType criteriaType);
    List<CriteriaType> fetchCriteriaTypeList();
    CriteriaType findCriteriaTypeById(Long id);
    CriteriaType updateCriteriaType(CriteriaType criteriaType, Long id);
    void deleteCriteriaType(Long id);
}
