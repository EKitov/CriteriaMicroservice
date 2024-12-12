package com.example.criteriamicroservice.repository;

import com.example.criteriamicroservice.entity.EvaluationParameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationParameterRepository extends JpaRepository<EvaluationParameter, Long> {
}
