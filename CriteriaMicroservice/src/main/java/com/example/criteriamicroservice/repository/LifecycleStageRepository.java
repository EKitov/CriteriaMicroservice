package com.example.criteriamicroservice.repository;

import com.example.criteriamicroservice.entity.LifecycleStage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LifecycleStageRepository extends JpaRepository<LifecycleStage, Long> {
}
