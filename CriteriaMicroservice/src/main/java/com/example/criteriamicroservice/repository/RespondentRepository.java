package com.example.criteriamicroservice.repository;

import com.example.criteriamicroservice.entity.Respondent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespondentRepository extends JpaRepository<Respondent, Long> {
}
