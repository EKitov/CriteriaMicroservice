package com.example.criteriamicroservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "survey_result", uniqueConstraints = {@UniqueConstraint(columnNames = {"respondent_id", "scale_parameter_id", "question_id", "inProgress" })})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SurveyResult {
    @Id
    @Column(unique=true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "respondent_id", nullable = false)
    private Respondent respondent;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private SurveyQuestion question;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "scale_parameter_id", nullable = false)
    private ScaleParameter selectedParameter; // Ответ — это параметр из шкалы

    private Boolean inProgress; // Флаг для отслеживания завершения опроса
}
