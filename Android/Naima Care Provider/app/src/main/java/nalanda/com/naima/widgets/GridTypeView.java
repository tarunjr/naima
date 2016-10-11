package nalanda.com.naima.widgets;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

import nalanda.com.naima.models.BaseModel;
import nalanda.com.naima.viewmodel.BaseViewModel;


/**
 * Created by ps1 on 9/10/16.
 */
public class GridTypeView implements BaseView {
    public View getView(Activity activity) {
        return null;
    }

    public BaseModel getClinicalData() {
        return null;
    }

    public BaseViewModel getViewModel() {
        return null;
    }

    /**
     * Set listview height based on listview children
     *
     * @param gridView
     */
    protected void setDynamicHeight(GridView gridView) {
        ListAdapter adapter = gridView.getAdapter();
        //check adapter if null
        if (adapter == null) {
            return;
        }
        int height = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(gridView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < ((adapter.getCount()/2) + 1); i++) {
            View listItem = adapter.getView(i, null, gridView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            height += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
        Log.i("Dynamic Height", "grid view elements height: " + height);
        Log.i("Dynamic Height", "spacing height: " + (gridView.getVerticalSpacing() * ((adapter.getCount() - 1)/2)));
        layoutParams.height = height + (gridView.getVerticalSpacing() * ((adapter.getCount() - 1)/2));
        gridView.setLayoutParams(layoutParams);
        gridView.requestLayout();
    }
}
