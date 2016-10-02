package org.naima.service.diagnostic.web;

import org.naima.service.diagnostic.service.VitalStatService;
import org.naima.service.diagnostic.domain.VitalStat;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class VitalStatController {
    @Autowired
    private VitalStatService vitalStatService;

    @RequestMapping("/vitalstats")
    public List<VitalStat> vitalStats() {
        return vitalStatService.findAll();
    }
    @RequestMapping("/vitalstats/id/{id}")
    public VitalStat vitalStatsById(@PathVariable(value="id") String id ) {
        return vitalStatService.findById(id);
    }
}
