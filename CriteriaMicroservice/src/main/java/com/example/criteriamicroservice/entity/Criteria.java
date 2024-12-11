package com.example.criteriamicroservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "criteria", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "value", "directory_id"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Criteria {
    @Id
    @NonNull
    @Column(unique=true, nullable = false)
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(unique=true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String value;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "directory_id", nullable = false)
    private CriteriaDirectory CriterionDirectory;
    @JsonManagedReference
    @OneToMany(mappedBy = "criteria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SurveyQuestion> questions;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public CriteriaDirectory getCriterionDirectory() {
        return CriterionDirectory;
    }

    public void setCriterionDirectory(CriteriaDirectory criterionDirectory) {
        CriterionDirectory = criterionDirectory;
    }

    public void setQuestions(List<SurveyQuestion> questions) {
        this.questions = questions;
    }

    public List<SurveyQuestion> getQuestions() {
        return questions;
    }
}
