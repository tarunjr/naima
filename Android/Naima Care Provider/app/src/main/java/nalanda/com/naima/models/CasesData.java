package nalanda.com.naima.models;

import java.util.List;

/**
 * Created by ps1 on 9/11/16.
 */
public class CasesData {
    private List<CasesClinical> clinical;

    public List<CasesClinical> getClinical() {
        return clinical;
    }

    public void setClinical(List<CasesClinical> clinical) {
        this.clinical = clinical;
    }
}
