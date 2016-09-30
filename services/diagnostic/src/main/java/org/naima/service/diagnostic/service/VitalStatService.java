package org.naima.service.diagnostic.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.naima.service.diagnostic.domain.VitalStat;
import org.naima.service.diagnostic.repository.VitalStatRepository;

import java.util.List;

@Service
public class VitalStatService {
    @Autowired
    private VitalStatRepository vitalStatRespository;

    public List<VitalStat> findAll() {
        return vitalStatRespository.findAll();
    }
    public VitalStat findById(String id) {
        return vitalStatRespository.findById(id);
    }
}
