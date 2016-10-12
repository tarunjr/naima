package nalanda.com.naima.models;

import java.util.List;

/**
 * Created by ps1 on 9/11/16.
 */
public class PendingData {
    private List<CasesClinical> clinical;

    private List<TestData> test;

    public List<CasesClinical> getClinical() {
        return clinical;
    }

    public void setClinical(List<CasesClinical> clinical) {
        this.clinical = clinical;
    }

    public List<TestData> getTest() {
        return test;
    }

    public void setTest(List<TestData> test) {
        this.test = test;
    }
}
