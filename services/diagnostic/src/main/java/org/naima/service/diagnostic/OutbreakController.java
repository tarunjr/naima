package org.naima.service.diagnostic;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OutbreakController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    private List<Outbreak> outbreaks;

    public  OutbreakController() {
        Speciality sp1 = new Speciality();
        sp1.setId("1");
        sp1.setName("Gastroentologist");

        Speciality sp2 = new Speciality();
        sp2.setId("4");
        sp2.setName("ENT");

        Condition c1 = new Condition();
        c1.setId("1");
        c1.setName("Acid Reflux");
        c1.setSpeciality(sp1);

        Condition c2 = new Condition();
        c2.setId("2");
        c2.setName("Diarhea");
        c2.setSpeciality(sp1);

        Condition c3 = new Condition();
        c3.setId("3");
        c3.setName("Sinus");
        c3.setSpeciality(sp2);

        Location l1 = new Location();
        l1.setLocality("KOPARGAON");
        l1.setSubDistrict("KHED");
        l1.setSubDistrict("PUNE");

        Location l2 = new Location();
        l2.setLocality("KOPARGAON");
        l2.setSubDistrict("TUMKUR");
        l2.setSubDistrict("BANGALORE");

        Outbreak ob1 = new Outbreak(l1,c1);
        Outbreak ob2 = new Outbreak(l1,c2);
        Outbreak ob3 = new Outbreak(l2,c3);

        outbreaks = new ArrayList<Outbreak>();
        outbreaks.add(ob1);
        outbreaks.add(ob2);
        outbreaks.add(ob3);
    }
    @RequestMapping(value="/outbreak", method=RequestMethod.GET)
    public List<Outbreak> outbreaks() {
        return outbreaks;
    }
    @RequestMapping(value="/outbreak/location",method=RequestMethod.POST, consumes="application/json")
    public List<Condition> outbreakByLocation(@RequestBody List<Location> locations) {

      List<Condition> conditions = new ArrayList<Condition>();
      for(Location l: locations) {
        for(Outbreak ob: outbreaks) {
            if(ob.getLocation().getLocality().equals(l.getLocality())) {
                conditions.add(ob.getCondition());
            }
        }
      }

      return conditions;
    }
    @RequestMapping(value="/outbreak/condition", method=RequestMethod.POST, consumes="application/json")
    public List<Location> outbreakByCondition(@RequestBody List<Condition> conditions) {

      List<Location> locations = new ArrayList<Location>();
      for(Condition c: conditions) {
        for(Outbreak ob: outbreaks) {
            if(ob.getCondition().getId().equals(c.getId())) {
                locations.add(ob.getLocation());
            }
        }
      }
      return locations;

    }
}
