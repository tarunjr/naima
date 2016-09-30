package org.naima.service.diagnostic.web;

import org.naima.service.diagnostic.service.TestService;
import org.naima.service.diagnostic.domain.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping("/tests")
    public List<Test> tests() {
        return testService.findAll();
    }
    @RequestMapping("/tests/id/{id}")
    public Test conditionById(@PathVariable(value="id") String testId ) {
        return testService.findById(testId);
    }
}
