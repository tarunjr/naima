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
public class TestController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/tests")
    public List<Test> tests() {
      ObjectMapper mapper = new ObjectMapper();
      ValueOperations<String,String> valueOps = redisTemplate.opsForValue();
      List<Test> tests = new ArrayList<Test>();

      try {
        String keyPattern = KeyNameSpace.TestNamespace + ":*";

        Set<String> keys = redisTemplate.keys(keyPattern);
        for(String key: keys) {
           String json  =  valueOps.get(key);
           Test test = mapper.readValue(json, Test.class);
           tests.add(test);
        }
      } catch (com.fasterxml.jackson.core.JsonProcessingException jpex) {
      } catch (java.io.IOException iox) {
      }
      return tests;
    }
    @RequestMapping("/tests/id/{id}")
    public Test conditionById(@PathVariable(value="id") String testId ) {
      ObjectMapper mapper = new ObjectMapper();
      ValueOperations<String,String>  valueOps = redisTemplate.opsForValue();
      Test test = null;

      System.out.println(testId);
      try {
          String json  = valueOps.get(testId);
          test = mapper.readValue(json, Test.class);
      } catch (com.fasterxml.jackson.core.JsonProcessingException jpex) {
      } catch (java.io.IOException iox) {
      }
      return test;
    }
}
