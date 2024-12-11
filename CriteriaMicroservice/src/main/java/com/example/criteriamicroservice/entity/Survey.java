package com.example.criteriamicroservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Survey {
    @Id
    @Column(unique=true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true, nullable = false)
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private SurveyStatus status; // Статус опросника: ACTIVE, IN_PROGRESS, COMPLETED

    @JsonManagedReference
    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SurveyQuestion> questions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public List<SurveyQuestion> getQuestions() {
        return questions;
    }

    public SurveyStatus getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public void setQuestions(List<SurveyQuestion> questions) {
        this.questions = questions;
    }

    public void setStatus(SurveyStatus status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
