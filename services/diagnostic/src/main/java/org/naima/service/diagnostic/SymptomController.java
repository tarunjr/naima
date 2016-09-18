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
public class SymptomController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/symptoms")
    public List<Symptom> symptom() {
      ObjectMapper mapper = new ObjectMapper();
      List<Symptom> symptoms = new ArrayList<Symptom>();
      ValueOperations<String,String> valueOps = redisTemplate.opsForValue();

      try {
        Set<String> keys = redisTemplate.keys("CSM-*");

        for(String key: keys) {
           String jsonInString  =  valueOps.get(key);
           Symptom symptom = mapper.readValue(jsonInString, Symptom.class);
           //System.out.println(symptom.getTitle());
           //System.out.println(jsonInString);
           symptoms.add(symptom);
        }
      } catch (com.fasterxml.jackson.core.JsonProcessingException jpex) {
      } catch (java.io.IOException iox) {
      }
      return symptoms;
    }
    @RequestMapping("/symptoms/id/{id}")
    public Symptom symptomById(@PathVariable(value="id") String symId ) {
      ObjectMapper mapper = new ObjectMapper();
      ValueOperations<String,String>  valueOps = redisTemplate.opsForValue();
      Symptom symptom = null;

      System.out.println(symId);
      try {
          String jsonInString  = valueOps.get(symId);
          symptom = mapper.readValue(jsonInString, Symptom.class);
      } catch (com.fasterxml.jackson.core.JsonProcessingException jpex) {
      } catch (java.io.IOException iox) {
      }
      return symptom;
    }
}
