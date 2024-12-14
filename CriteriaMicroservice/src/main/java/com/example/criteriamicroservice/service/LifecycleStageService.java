package com.example.criteriamicroservice.service;

import com.example.criteriamicroservice.entity.LifecycleStage;

import java.util.List;

public interface LifecycleStageService {
    LifecycleStage saveLifecycleStage(LifecycleStage lifecycleStage);
    List<LifecycleStage> fetchLifecycleStageList();
    LifecycleStage findLifecycleStageById(Long id);
    LifecycleStage updateLifecycleStage(LifecycleStage lifecycleStage, Long id);
    void deleteLifecycleStage(Long id);
}
