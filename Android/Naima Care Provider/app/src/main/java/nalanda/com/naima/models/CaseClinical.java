package nalanda.com.naima.models;

import java.util.List;

import nalanda.com.naima.viewmodel.SymptomItemModel;

/**
 * Created by ps1 on 9/11/16.
 */
public class CaseClinical extends BaseModel{
//    private String id;
//
//    private String name;

    private SymptomItemModel symptom;

    private List<String> value;

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }


    public SymptomItemModel getSymptom() {
        return symptom;
    }

    public void setSymptom(SymptomItemModel symptom) {
        this.symptom = symptom;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }
}
