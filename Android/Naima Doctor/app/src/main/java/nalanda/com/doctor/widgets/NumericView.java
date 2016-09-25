package nalanda.com.doctor.widgets;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import nalanda.com.doctor.R;
import nalanda.com.doctor.models.BaseModel;
import nalanda.com.doctor.models.CaseClinical;
import nalanda.com.doctor.models.NumericModel;
import nalanda.com.doctor.viewmodel.BaseViewModel;
import nalanda.com.doctor.viewmodel.SymptomItemModel;

/**
 * Created by ps1 on 9/10/16.
 */
public class NumericView implements BaseView {
    private NumericModel numericModel;

    private SymptomItemModel numericViewModel;
    private View view;

    public NumericView(BaseViewModel numericViewModel) {
        this.numericViewModel = (SymptomItemModel) numericViewModel;
    }

    @Override
    public View getView(Activity activity) {
        view = activity.getLayoutInflater().inflate(R.layout.numeric_input_layout, null, false);
        ((EditText)view.findViewById(R.id.numericField)).setHint(numericViewModel.getInfo().getTitle());

        return view;
    }

    @Override
    public BaseModel getClinicalData() {
        String value = ((EditText) view.findViewById(R.id.numericField)).getText().toString();
        if(!TextUtils.isEmpty(value)) {
            CaseClinical caseClinical = new CaseClinical();
            caseClinical.setName((numericViewModel).getInfo().getName());
            caseClinical.setId(numericViewModel.getId());
            List<String> values = new ArrayList<String>();
            values.add(value);
            caseClinical.setValue(values);

            return caseClinical;
        } else {
            return numericModel;
        }
    }

    @Override
    public BaseViewModel getViewModel() {
        return numericViewModel;
    }
}
