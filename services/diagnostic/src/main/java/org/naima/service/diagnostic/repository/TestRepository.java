package org.naima.service.diagnostic.repository;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import org.naima.service.diagnostic.domain.Test;

@Repository
public class TestRepository {
  @Autowired
  private StringRedisTemplate redisTemplate;

  public List<Test> findAll() {
    ObjectMapper mapper = new ObjectMapper();
    List<Test> tests = new ArrayList<Test>();
    ValueOperations<String,String> valueOps = redisTemplate.opsForValue();

    try {
      String keyPattern = KeyNameSpace.TestNamespace + ":*";
      Set<String> keys = redisTemplate.keys(keyPattern);

      for(String key: keys) {
         System.out.println(key);
         String json  =  valueOps.get(key);
         System.out.println(json);
         Test test = mapper.readValue(json, Test.class);
         tests.add(test);
      }
    } catch (com.fasterxml.jackson.core.JsonProcessingException jpex) {
    } catch (java.io.IOException iox) {
    }
    return tests;
  }

  public Test findById(String id) {
    ObjectMapper mapper = new ObjectMapper();
    ValueOperations<String,String>  valueOps = redisTemplate.opsForValue();
    Test test = null;

    try {
        String json  = valueOps.get(id);
        test = mapper.readValue(json, Test.class);
    } catch (com.fasterxml.jackson.core.JsonProcessingException jpex) {
    } catch (java.io.IOException iox) {
    }
    return test;
  }
}
