package com.example.criteriamicroservice.service;

import com.example.criteriamicroservice.entity.LifecycleStage;
import com.example.criteriamicroservice.repository.LifecycleStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LifecycleStageServiceImpl implements LifecycleStageService {
    @Autowired
    private LifecycleStageRepository lifecycleStageRepository;

    @Override
    public LifecycleStage saveLifecycleStage(LifecycleStage lifecycleStage) {
        return lifecycleStageRepository.save(lifecycleStage);
    }

    @Override
    public List<LifecycleStage> fetchLifecycleStageList() {
        return (List<LifecycleStage>) lifecycleStageRepository.findAll();
    }

    @Override
    public LifecycleStage findLifecycleStageById(Long id) {
        LifecycleStage lifecycleStageDB = lifecycleStageRepository.findById(id).orElse(null);
        return lifecycleStageDB;
    }

    @Override
    public LifecycleStage updateLifecycleStage(LifecycleStage lifecycleStage, Long id) {
        LifecycleStage lifecycleStageDB = lifecycleStageRepository.findById(id).orElse(null);
        if (Objects.nonNull(lifecycleStageDB)) {
            lifecycleStageDB.setName(lifecycleStage.getName());
            lifecycleStageDB.setCriteriaDirectories(lifecycleStage.getCriteriaDirectories());
            return lifecycleStageRepository.save(lifecycleStageDB);
        } else {
            return null;
        }
    }

    @Override
    public void deleteLifecycleStage(LifecycleStage lifecycleStage) {
        lifecycleStageRepository.delete(lifecycleStage);
    }
}
