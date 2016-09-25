package nalanda.com.doctor.models;

import java.util.List;

/**
 * Created by ps1 on 9/11/16.
 */
public class CaseData {
    private List<CaseClinical> clinical;

    public List<CaseClinical> getClinical() {
        return clinical;
    }

    public void setClinical(List<CaseClinical> clinical) {
        this.clinical = clinical;
    }
}
