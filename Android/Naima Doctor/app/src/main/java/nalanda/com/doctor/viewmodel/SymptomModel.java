package nalanda.com.doctor.viewmodel;

import java.util.List;

/**
 * Created by ps1 on 9/10/16.
 */
public class SymptomModel extends BaseViewModel{

    List<SymptomItemModel> symptomItemList;

    public List<SymptomItemModel> getSymptomItemList() {
        return symptomItemList;
    }

    public void setSymptomItemList(List<SymptomItemModel> symptomItemList) {
        this.symptomItemList = symptomItemList;
    }
}
