package com.example.criteriamicroservice.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "criteria_directory", uniqueConstraints = {@UniqueConstraint(columnNames = {"formulation", "shortname", "type_id", "lifecycle_stage_id", "scale_id" })})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriteriaDirectory {
    @Id
    @Column(unique=true, nullable = false)
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String formulation;
    @Column(nullable = false)
    private Double weight;
    @Column(nullable = false)
    private String shortname;
    @JsonIgnoreProperties(value = "type-reference")
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private CriteriaType criterionType;
    @JsonIgnoreProperties(value = "stage-reference")
    @ManyToOne
    @JoinColumn(name = "lifecycle_stage_id", nullable = false)
    private LifecycleStage criterionStage;
    @JsonIgnoreProperties(value = "scale-reference")
    @ManyToOne
    @JoinColumn(name = "scale_id", nullable = false)
    private EvaluationScale criterionScale;
    @JsonIgnore
    @JsonManagedReference("criteria-reference")
    @OneToMany(mappedBy = "criterionDirectory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Criteria> criteria;

    public List<Criteria> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<Criteria> criteria) {
        this.criteria = criteria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CriteriaType getCriterionType() {
        return criterionType;
    }

    public Double getWeight() {
        return weight;
    }

    public EvaluationScale getCriterionScale() {
        return criterionScale;
    }

    public LifecycleStage getCriterionStage() {
        return criterionStage;
    }

    public String getFormulation() {
        return formulation;
    }

    public String getShortname() {
        return shortname;
    }

    public void setCriterionScale(EvaluationScale criterionScale) {
        this.criterionScale = criterionScale;
    }

    public void setCriterionStage(LifecycleStage criterionStage) {
        this.criterionStage = criterionStage;
    }

    public void setCriterionType(CriteriaType criterionType) {
        this.criterionType = criterionType;
    }

    public void setFormulation(String formulation) {
        this.formulation = formulation;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
