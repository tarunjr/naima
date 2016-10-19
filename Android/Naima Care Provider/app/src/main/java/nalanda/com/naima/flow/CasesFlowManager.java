package nalanda.com.naima.flow;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import nalanda.com.naima.R;
import nalanda.com.naima.adapters.CasesListAdapter;
import nalanda.com.naima.fragment.BaseDataFragment;
import nalanda.com.naima.models.CasesDataModel;
import nalanda.com.naima.network.VolleyUtil;
import nalanda.com.naima.widgets.BaseView;
import nalanda.com.naima.widgets.CaseDetailView;
import nalanda.com.naima.widgets.NextFooterButton;
import nalanda.com.naima.widgets.WidgetFactory;

/**
 * Created by ps1 on 9/11/16.
 */
public class CasesFlowManager {
    public static final int PENDING_FLOW = 1;
    public static final int OPEN_FLOW = 2;
    public static final int CLOSED_FLOW = 3;

    public static final String pendingUrl =
            "http://ec2-54-161-5-199.compute-1.amazonaws.com:8082/api/v1/cases?status=pending&owner=careprovider&ownerid=PR-01";
    public static final String openUrl =
            "http://ec2-54-161-5-199.compute-1.amazonaws.com:8082/api/v1/cases?status=open&owner=careprovider&ownerid=PR-01";
    public static final String closedUrl =
            "http://ec2-54-161-5-199.compute-1.amazonaws.com:8082/api/v1/cases?status=closed&owner=careprovider&ownerid=PR-01";

    private String url;
    private String title;
    private BaseDataFragment mDataFragment;
    Gson gson;
    View view;

    public CasesFlowManager(BaseDataFragment dataFragment, int flow) {
        mDataFragment = dataFragment;

        initFlow(flow);

        gson = new Gson();
    }

    private void initFlow(int flow) {
        switch (flow) {
            case PENDING_FLOW:
                url = pendingUrl;
                title = "Pending Cases";
                break;
            case OPEN_FLOW:
                url = openUrl;
                title = "Open Cases";
                break;
            case CLOSED_FLOW:
                url = closedUrl;
                title = "Closed Cases";
                break;
        }
    }

    public void startFlow() {
        VolleyUtil.getInstance().doGet(mDataFragment.getContext(), url, new Response.Listener<String>() {
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

    public View getView(final Activity activity, String response) {
        CasesDataModel[] caseDataModels = gson.fromJson(response, CasesDataModel[].class);

        view = activity.getLayoutInflater().inflate(R.layout.pending_list, null, false);

        ((TextView)view.findViewById(R.id.title)).setText(title);

        // Creating adapter for spinner
        final CasesListAdapter casesListAdapter = new CasesListAdapter(activity, R.layout.case_item, caseDataModels);

        // attaching data adapter to spinner
        ListView listView = (ListView) view.findViewById(R.id.pending_items_list);
        listView.setAdapter(casesListAdapter);

        customizeListView();

        ((ListView) view.findViewById(R.id.pending_items_list)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToDetailedFlow(casesListAdapter.getItem(position));
            }
        });

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

    private void goToDetailedFlow(CasesDataModel casesDataModel) {
        CaseDetailView caseDetailView = new CaseDetailView(casesDataModel);

        List<View> viewList = new ArrayList<View>();
        View view = caseDetailView.getView(mDataFragment.getActivity());

        viewList.add(view);

        mDataFragment.updateView(viewList);
    }
}
