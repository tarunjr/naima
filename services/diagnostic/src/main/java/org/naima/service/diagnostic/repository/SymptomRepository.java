package org.naima.service.diagnostic.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import org.naima.service.diagnostic.domain.Symptom;

@Repository
public class SymptomRepository {

  @Autowired
  private StringRedisTemplate redisTemplate;

  public List<Symptom> findAll() {
    ObjectMapper mapper = new ObjectMapper();
    List<Symptom> symptoms = new ArrayList<Symptom>();
    ValueOperations<String,String> valueOps = redisTemplate.opsForValue();

    try {
      String keyPattern = KeyNameSpace.SymptomNamespace + ":*";
      Set<String> keys = redisTemplate.keys(keyPattern);

      for(String key: keys) {
         System.out.println(key);
         String json  =  valueOps.get(key);
         System.out.println(json);
         Symptom symptom = mapper.readValue(json, Symptom.class);
         //System.out.println(symptom.getTitle());
         //System.out.println(jsonInString);
         symptoms.add(symptom);
      }
    } catch (com.fasterxml.jackson.core.JsonProcessingException jpex) {
    } catch (java.io.IOException iox) {
    }
    return symptoms;
  }

  public Symptom findById(String id) {
    ObjectMapper mapper = new ObjectMapper();
    ValueOperations<String,String>  valueOps = redisTemplate.opsForValue();
    Symptom symptom = null;
    String key = KeyNameSpace.SymptomNamespace + ":" + id;

    System.out.println(id);
    try {
        String json  = valueOps.get(key);
        symptom = mapper.readValue(json, Symptom.class);
    } catch (com.fasterxml.jackson.core.JsonProcessingException jpex) {
    } catch (java.io.IOException iox) {
    }
    return symptom;
  }
}
