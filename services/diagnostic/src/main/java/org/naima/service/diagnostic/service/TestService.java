package org.naima.service.diagnostic.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.naima.service.diagnostic.domain.Test;
import org.naima.service.diagnostic.repository.TestRepository;

import java.util.List;

@Service
public class TestService {
    @Autowired
    private TestRepository testRespository;

    public List<Test> findAll() {
        return testRespository.findAll();
    }
    public Test findById(String id) {
        return testRespository.findById(id);
    }
}
