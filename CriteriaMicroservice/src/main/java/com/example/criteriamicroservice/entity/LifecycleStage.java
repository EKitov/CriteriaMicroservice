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
public class LifecycleStage {
    @Id
    @Column(unique=true, nullable = false)
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true, nullable = false)
    private String name;
    @JsonManagedReference
    @OneToMany(mappedBy = "CriterionStage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CriteriaDirectory> criteriaDirectories;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CriteriaDirectory> getCriteriaDirectories() {
        return criteriaDirectories;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setCriteriaDirectories(List<CriteriaDirectory> criteriaDirectories) {
        this.criteriaDirectories = criteriaDirectories;
    }
}
