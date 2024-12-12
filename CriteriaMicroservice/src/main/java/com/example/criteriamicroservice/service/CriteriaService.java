package com.example.criteriamicroservice.service;

import com.example.criteriamicroservice.entity.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.List;

public interface CriteriaService {
    Criteria saveCriteria(Criteria criteria);
    List<Criteria> fetchCriteriaList();
    List<Criteria> findByCriteriaDirectory(CriteriaDirectory criteriaDirectory);
    Criteria findCriteriaById(Long id);
    Criteria updateCriteria(Criteria criteria, Long id);
    void deleteCriteria(Criteria criteria);
    void uploadCriteriaFromCsv(InputStream csvInputStream);

    void downloadCriteriaToCsv(Writer writer);
}
