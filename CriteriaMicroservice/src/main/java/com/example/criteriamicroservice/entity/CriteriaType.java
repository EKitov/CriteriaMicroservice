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
public class CriteriaType {
    @Id
    @Column(unique=true, nullable = false)
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true, nullable = false)
    private String name;
    @JsonManagedReference
    @OneToMany(mappedBy = "CriterionType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CriteriaDirectory> criteriaDirectories;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CriteriaDirectory> getCriteriaDirectories() {
        return criteriaDirectories;
    }

    public void setCriteriaDirectories(List<CriteriaDirectory> criteriaDirectories) {
        this.criteriaDirectories = criteriaDirectories;
    }
}
