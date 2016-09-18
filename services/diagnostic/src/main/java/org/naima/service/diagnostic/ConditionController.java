package org.naima.service.diagnostic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class ConditionController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/conditions")
    public List<Condition> conditions() {
      ObjectMapper mapper = new ObjectMapper();
      ValueOperations<String,String> valueOps = redisTemplate.opsForValue();
      List<Condition> conditions = new ArrayList<Condition>();

      try {
        Set<String> keys = redisTemplate.keys("CND-*");

        for(String key: keys) {
           String jsonInString  =  valueOps.get(key);
           Condition condition = mapper.readValue(jsonInString, Condition.class);
           conditions.add(condition);
        }
      } catch (com.fasterxml.jackson.core.JsonProcessingException jpex) {
      } catch (java.io.IOException iox) {
      }
      return conditions;
    }
    @RequestMapping("/conditions/id/{id}")
    public Condition conditionById(@PathVariable(value="id") String conditionId ) {
      ObjectMapper mapper = new ObjectMapper();
      ValueOperations<String,String>  valueOps = redisTemplate.opsForValue();
      Condition condition = null;

      System.out.println(conditionId);
      try {
          String jsonInString  = valueOps.get(conditionId);
          condition = mapper.readValue(jsonInString, Condition.class);
      } catch (com.fasterxml.jackson.core.JsonProcessingException jpex) {
      } catch (java.io.IOException iox) {
      }
      return condition;
    }
}
