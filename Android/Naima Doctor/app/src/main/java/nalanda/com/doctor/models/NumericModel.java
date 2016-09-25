package nalanda.com.doctor.models;

/**
 * Created by ps1 on 9/10/16.
 */

public class NumericModel extends BaseModel{
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private int value;
}