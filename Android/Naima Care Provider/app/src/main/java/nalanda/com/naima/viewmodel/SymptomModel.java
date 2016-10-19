package nalanda.com.naima.viewmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ps1 on 9/10/16.
 */
public class SymptomModel implements BaseViewModel{

    List<SymptomItemModel> symptomItemList;

    public List<SymptomItemModel> getSymptomItemList() {
        return symptomItemList;
    }

    public void setSymptomItemList(List<SymptomItemModel> symptomItemList) {
        this.symptomItemList = symptomItemList;
    }
}
