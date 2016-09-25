package org.naima.service.diagnostic;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

@RestController
public class VitalStatController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/vitalstats")
    public List<VitalStat> vitalStats() {
      ObjectMapper mapper = new ObjectMapper();
      List<VitalStat> stats = new ArrayList<VitalStat>();
      ValueOperations<String,String> valueOps = redisTemplate.opsForValue();

      try {
        String keyPattern = KeyNameSpace.StandardNamespace + ":*";
        Set<String> keys = redisTemplate.keys(keyPattern);

        for(String key: keys) {
           String jsonInString  =  valueOps.get(key);
           VitalStat stat = mapper.readValue(jsonInString, VitalStat.class);
           stats.add(stat);
        }
      } catch (com.fasterxml.jackson.core.JsonProcessingException jpex) {
      } catch (java.io.IOException iox) {
      }
      return stats;
    }
    @RequestMapping("/vitalstats/id/{id}")
    public VitalStat vitalStatsById(@PathVariable(value="id") String id ) {
      ObjectMapper mapper = new ObjectMapper();
      ValueOperations<String,String>  valueOps = redisTemplate.opsForValue();
      VitalStat stat = null;

      System.out.println(id);
      try {
          String jsonInString  = valueOps.get(id);
          stat = mapper.readValue(jsonInString, VitalStat.class);
      } catch (com.fasterxml.jackson.core.JsonProcessingException jpex) {
      } catch (java.io.IOException iox) {
      }
      return stat;
    }
}
