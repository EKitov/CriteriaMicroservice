package com.example.criteriamicroservice.repository;

import com.example.criteriamicroservice.entity.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CriteriaRepository extends JpaRepository<Criteria, Long> {
}
