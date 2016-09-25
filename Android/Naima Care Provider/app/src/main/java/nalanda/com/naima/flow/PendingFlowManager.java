package nalanda.com.naima.flow;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import nalanda.com.naima.R;
import nalanda.com.naima.fragment.BaseDataFragment;
import nalanda.com.naima.models.CaseData;
import nalanda.com.naima.models.CaseDataModel;
import nalanda.com.naima.models.CasesDataModel;
import nalanda.com.naima.network.VolleyUtil;
import nalanda.com.naima.widgets.NextFooterButton;
import nalanda.com.naima.widgets.WidgetFactory;

/**
 * Created by ps1 on 9/11/16.
 */
public class PendingFlowManager {
    public static final String pendingUrl = "http://ec2-54-175-135-100.compute-1.amazonaws.com:3000/cases/pending/cp/PR-01";
    private BaseDataFragment mDataFragment;
    Gson gson;
    View view;

    public PendingFlowManager(BaseDataFragment dataFragment) {
        mDataFragment = dataFragment;

        gson = new Gson();
    }

    public void startFlow() {
        VolleyUtil.getInstance().doGet(mDataFragment.getContext(), pendingUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                getView(mDataFragment.getActivity(), response);

                List<View> views = new ArrayList<View>();
                views.add(view);

                mDataFragment.updateView(views);

                Button footerButton = (Button) (new NextFooterButton()).getView(mDataFragment.getActivity());
                footerButton.setText(R.string.done);
                footerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDataFragment.getActivity().onBackPressed();
                    }
                });
            }
        });
    }

    public View getView(Activity activity, String response) {
        CasesDataModel[] caseDataModels = gson.fromJson(response, CasesDataModel[].class);

        view = activity.getLayoutInflater().inflate(R.layout.pending_list, null, false);

        ((TextView)view.findViewById(R.id.title)).setText("List of Pending cases");

        List<String> items = new ArrayList<String>();
        for (CasesDataModel casesDataModel : caseDataModels) {
            items.add(casesDataModel.getPatient().getName() + " - " + casesDataModel.getProvider().getName());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(activity, R.layout.spinner_text, items);

//        // Drop down layout style - list view with radio button
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);

        // attaching data adapter to spinner
        ListView listView = (ListView) view.findViewById(R.id.pending_items_list);
        listView.setAdapter(dataAdapter);

        customizeListView();

        return view;
    }

    private void customizeListView() {
        ListView listView = (ListView) view.findViewById(R.id.pending_items_list);
        listView.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
