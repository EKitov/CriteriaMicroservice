package com.example.criteriamicroservice.repository;

import com.example.criteriamicroservice.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
