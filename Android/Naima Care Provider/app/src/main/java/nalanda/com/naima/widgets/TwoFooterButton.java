package nalanda.com.naima.widgets;

import android.app.Activity;
import android.view.View;

import nalanda.com.naima.R;
import nalanda.com.naima.models.BaseModel;
import nalanda.com.naima.viewmodel.BaseViewModel;

/**
 * Created by ps1 on 9/11/16.
 */
public class TwoFooterButton implements BaseView {
    @Override
    public View getView(Activity activity) {
        return activity.getLayoutInflater().inflate(R.layout.two_footer_button, null, false);
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
