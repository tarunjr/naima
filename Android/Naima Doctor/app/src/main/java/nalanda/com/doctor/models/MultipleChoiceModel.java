package nalanda.com.doctor.models;

import java.util.List;

/**
 * Created by ps1 on 9/10/16.
 */
public class MultipleChoiceModel extends BaseModel {
    public List<String> values;

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
