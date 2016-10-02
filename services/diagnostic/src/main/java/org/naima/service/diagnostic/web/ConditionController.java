package org.naima.service.diagnostic.web;

import org.naima.service.diagnostic.domain.Condition;
import org.naima.service.diagnostic.service.ConditionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class ConditionController {
    @Autowired
    private ConditionService conditionService;

    @RequestMapping("/conditions")
    public List<Condition> conditions() {
        return conditionService.findAll();
    }
    @RequestMapping("/conditions/id/{id}")
    public Condition conditionById(@PathVariable(value="id") String conditionId ) {
        return  conditionService.findById(conditionId);
    }
}
