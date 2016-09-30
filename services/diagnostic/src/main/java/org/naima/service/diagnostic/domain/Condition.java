package org.naima.service.diagnostic.domain;

import java.util.List;

public class Condition {
    private String id;
    private String name;
    private Speciality speciality;
    private List<String> symptoms;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Speciality getSpeciality() {
        return this.speciality;
    }
    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }
    public List<String> getSymptoms() {
        return this.symptoms;
    }
    public void setSymptoms(List<String>  symptoms) {
        this.symptoms = symptoms;
    }
}
