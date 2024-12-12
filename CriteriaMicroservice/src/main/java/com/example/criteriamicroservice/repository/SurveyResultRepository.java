package com.example.criteriamicroservice.repository;

import com.example.criteriamicroservice.entity.SurveyResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyResultRepository extends JpaRepository<SurveyResult, Long> {
}
