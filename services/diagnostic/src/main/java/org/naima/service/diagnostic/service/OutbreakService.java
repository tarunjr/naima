package org.naima.service.diagnostic.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.naima.service.diagnostic.domain.Outbreak;
import org.naima.service.diagnostic.domain.Speciality;
import org.naima.service.diagnostic.domain.Location;
import org.naima.service.diagnostic.domain.Condition;
import org.naima.service.diagnostic.repository.OutbreakRepository;

import java.util.List;

@Service
public class OutbreakService {
    @Autowired
    private OutbreakRepository outbreakRespository;

    public List<Outbreak> findAll() {
        return outbreakRespository.findAll();
    }
    public List<Outbreak> findByLocation(Location location ) {
        return outbreakRespository.findByLocation(location);
    }
    public List<Outbreak> findByConditions(List<Condition> conditions) {
        return outbreakRespository.findByConditions(conditions);
    }
}
