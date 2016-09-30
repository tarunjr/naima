package org.naima.service.diagnostic.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.naima.service.diagnostic.domain.Symptom;
import org.naima.service.diagnostic.repository.SymptomRepository;

import java.util.List;

@Service
public class SymptomService {
    @Autowired
    private SymptomRepository symptomRespository;

    public List<Symptom> findAll() {
        return symptomRespository.findAll();
    }
    public Symptom findById(String id) {
        return symptomRespository.findById(id);
    }
}
