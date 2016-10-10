package nalanda.com.naima.widgets;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import nalanda.com.naima.R;
import nalanda.com.naima.models.BaseModel;
import nalanda.com.naima.viewmodel.BaseViewModel;
import nalanda.com.naima.viewmodel.FooterButtonModel;

/**
 * Created by ps1 on 9/11/16.
 */
public class FooterButton implements BaseView {
    private FooterButtonModel footerButtonModel;

    public FooterButton() {
    }

    public FooterButton(FooterButtonModel footerButtonModel) {
        this.footerButtonModel = footerButtonModel;
    }

    @Override
    public View getView(Activity activity) {
        View view = activity.getLayoutInflater().inflate(R.layout.footer_button, null, false);

        if(footerButtonModel != null && footerButtonModel.getButtonLabel() != 0) {
            ((Button) view.findViewById(R.id.footer_button)).setText(footerButtonModel.getButtonLabel());
        }

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
