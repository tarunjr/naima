package nalanda.com.naima.widgets;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import nalanda.com.naima.R;
import nalanda.com.naima.models.BaseModel;
import nalanda.com.naima.viewmodel.BaseViewModel;

/**
 * Created by ps1 on 9/11/16.
 */
public class NextFooterButton extends FooterButton {
    @Override
    public View getView(Activity activity) {
        View view = super.getView(activity);
        ((Button) view.findViewById(R.id.footer_button)).setText(R.string.next);

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
