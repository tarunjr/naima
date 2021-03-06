package nalanda.com.naima.widgets;

import android.app.Activity;
import android.util.SparseBooleanArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nalanda.com.naima.R;
import nalanda.com.naima.models.BaseModel;
import nalanda.com.naima.models.CaseClinical;
import nalanda.com.naima.models.MultipleChoiceModel;
import nalanda.com.naima.viewmodel.BaseViewModel;
import nalanda.com.naima.viewmodel.SymptomItemModel;

/**
 * Created by ps1 on 9/10/16.
 */
public class MultipleChoiceView extends GridTypeView {
    private MultipleChoiceModel multipleChoiceModel;
    private SymptomItemModel multipleChoiceViewModel;
    private View view;

    public MultipleChoiceView(BaseViewModel dataViewModel) {
        multipleChoiceModel = new MultipleChoiceModel();
        multipleChoiceViewModel = (SymptomItemModel) dataViewModel;
    }

    @Override
    public View getView(Activity activity) {
        view = activity.getLayoutInflater().inflate(R.layout.multiple_choice_input_layout, null, false);

        ((TextView)view.findViewById(R.id.title)).setText(multipleChoiceViewModel.getTitle());

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(activity, R.layout.symptom_multiple_choice, R.id.multiple_choice_text, multipleChoiceViewModel.getOptions());

//        // Drop down layout style - list view with radio button
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);

        // attaching data adapter to spinner
        GridView gridView = (GridView) view.findViewById(R.id.multiple_choice_grid);
        gridView.setAdapter(dataAdapter);
        gridView.setNumColumns(2);
        gridView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

//        customizeListView();

        setDynamicHeight(((GridView) view.findViewById(R.id.multiple_choice_grid)));

        return view;
    }

    @Override
    public BaseModel getClinicalData() {
        int count = ((GridView) view.findViewById(R.id.multiple_choice_grid)).getCount();
        SparseBooleanArray checkedPositions = ((GridView) view.findViewById(R.id.multiple_choice_grid)).getCheckedItemPositions();
        if(checkedPositions.size() > 0) {
            List<String> values = new ArrayList<String>();
            ArrayAdapter<String> adapter = (ArrayAdapter<String>) ((GridView) view.findViewById(R.id.multiple_choice_grid)).getAdapter();

            for (int i = 0; i < count; i++) {
                if (checkedPositions.get(i)) {
                    values.add(adapter.getItem(i));
                }
            }

            CaseClinical caseClinical = new CaseClinical();
            caseClinical.setSymptom(new SymptomItemModel());
            caseClinical.getSymptom().setId(multipleChoiceViewModel.getId());
            caseClinical.getSymptom().setName(multipleChoiceViewModel.getName());
            caseClinical.setValue(values);

            return caseClinical;
        } else {
            return null;
        }
    }

    public SymptomItemModel getViewModel() {
        return multipleChoiceViewModel;
    }

//    private void customizeListView() {
//        GridView listView = (GridView) view.findViewById(R.id.multiple_choice_grid);
//        listView.setOnTouchListener(new View.OnTouchListener() {
//            // Setting on Touch Listener for handling the touch inside ScrollView
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // Disallow the touch request for parent scroll on touch of child view
//                v.getParent().requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });
//
//        ListAdapter listAdapter = listView.getAdapter();
//        if (listAdapter == null)
//            return;
//
//        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
//        int totalHeight = 0;
//        View view = null;
//        for (int i = 0; i < listAdapter.getCount(); i++) {
//            view = listAdapter.getView(i, view, listView);
//            if (i == 0)
//                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));
//
//            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
//            totalHeight += view.getMeasuredHeight();
//        }
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
//        listView.setLayoutParams(params);
//    }
}
