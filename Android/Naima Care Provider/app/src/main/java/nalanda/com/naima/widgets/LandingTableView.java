package nalanda.com.naima.widgets;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import nalanda.com.naima.R;
import nalanda.com.naima.models.BaseModel;
import nalanda.com.naima.viewmodel.BaseViewModel;
import nalanda.com.naima.viewmodel.LandingTableViewModel;

/**
 * Created by ps1 on 10/2/16.
 */
public class LandingTableView implements BaseView {
    LandingTableViewModel landingTableViewModel;

    LandingTableView(BaseViewModel viewModel) {
        landingTableViewModel = (LandingTableViewModel) viewModel;
    }

    @Override
    public View getView(Activity activity) {
        View view = activity.getLayoutInflater().inflate(R.layout.landing_layout, null, false);
        ((TextView) view.findViewById(R.id.landing_title)).setText(landingTableViewModel.getTitle());
        ((TextView) view.findViewById(R.id.landing_content)).setText(landingTableViewModel.getContent());

        return view;
    }

    @Override
    public BaseModel getClinicalData() {
        return null;
    }

    @Override
    public BaseViewModel getViewModel() {
        return null;
    }
}
