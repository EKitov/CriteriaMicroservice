package com.example.criteriamicroservice.service;

import com.example.criteriamicroservice.entity.CriteriaType;
import com.example.criteriamicroservice.repository.CriteriaTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CriteriaTypeServiceImpl implements CriteriaTypeService{
    @Autowired
    private CriteriaTypeRepository criteriaTypeRepository;
    @Override
    public CriteriaType saveCriteriaType(CriteriaType criteriaType) {
        return criteriaTypeRepository.save(criteriaType);
    }

    @Override
    public List<CriteriaType> fetchCriteriaTypeList() {
        return (List<CriteriaType>) criteriaTypeRepository.findAll();
    }

    @Override
    public CriteriaType findCriteriaTypeById(Long id) {
        CriteriaType criteriaTypeDB = criteriaTypeRepository.findById(id).orElse(null);
        return criteriaTypeDB;
    }

    @Override
    public CriteriaType updateCriteriaType(CriteriaType criteriaType, Long id) {
        CriteriaType criteriaTypeDB = criteriaTypeRepository.findById(id).orElse(null);
        if (Objects.nonNull(criteriaTypeDB)) {
            criteriaTypeDB.setName(criteriaType.getName());
            criteriaTypeDB.setCriteriaDirectories(criteriaType.getCriteriaDirectories());
            return criteriaTypeRepository.save(criteriaTypeDB);
        } else {
            return null;
        }
    }

    @Override
    public void deleteCriteriaType(CriteriaType criteriaType) {
        criteriaTypeRepository.delete(criteriaType);
    }
}
