package nalanda.com.naima.widgets;

import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import nalanda.com.naima.R;
import nalanda.com.naima.models.BaseModel;
import nalanda.com.naima.models.ComboboxModel;
import nalanda.com.naima.viewmodel.BaseViewModel;
import nalanda.com.naima.viewmodel.SymptomItemModel;

/**
 * Created by ps1 on 9/10/16.
 */
public class ComboboxView implements BaseView{
    private ComboboxModel comboboxModel;
    private SymptomItemModel dataViewItemModel[];
    private View view;

    public ComboboxView(BaseViewModel dataViewModel[]) {
        comboboxModel = new ComboboxModel();
        this.dataViewItemModel = dataViewItemModel;
    }

    @Override
    public View getView(Activity activity) {
        view = activity.getLayoutInflater().inflate(R.layout.combo_input_layout, null, false);

        List<String> items = new ArrayList<String>();

        for(int i = 0; i < dataViewItemModel.length; i++) {
            items.add(dataViewItemModel[i].getInfo().getName());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, items);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        spinner.setAdapter(dataAdapter);

        return view;
    }

    @Override
    public BaseModel getClinicalData() {
        return null;
    }

    public int getSelectedIndex() {
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        return spinner.getSelectedItemPosition();
    }

    @Override
    public BaseViewModel getViewModel() {
        return null;
    }
}
