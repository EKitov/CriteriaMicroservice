package com.example.criteriamicroservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "survey_question", uniqueConstraints = {@UniqueConstraint(columnNames = {"survey_id", "criteria_id", "questionOrder" })})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SurveyQuestion {
    @Id
    @Column(unique=true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "survey_id", nullable = false)
    private Survey survey;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "criteria_id", nullable = false)
    private Criteria criteria;
    @Column(nullable = false)
    private Integer questionOrder; // Порядок вопроса в опроснике
    private String additionalDetails; // Дополнительная информация о вопросе (опционально)

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public String getAdditionalDetails() {
        return additionalDetails;
    }

    public void setAdditionalDetails(String additionalDetails) {
        this.additionalDetails = additionalDetails;
    }

    public Integer getQuestionOrder() {
        return questionOrder;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setQuestionOrder(Integer questionOrder) {
        this.questionOrder = questionOrder;
    }
}
