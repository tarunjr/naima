package nalanda.com.naima.widgets;

import nalanda.com.naima.viewmodel.BaseViewModel;

/**
 * Created by ps1 on 9/10/16.
 */
public class WidgetFactory {
    public static final String WIDGET_NUMERIC = "Numeric";
    public static final String WIDGET_BOOLEAN = "Boolean";
    public static final String WIDGET_MULTIPLE_CHOICE = "List";
    public static final String WIDGET_LANDING = "LandingTable";
    public WidgetFactory() {
    }

    public BaseView getViewWidget(String viewName, BaseViewModel viewModel) {
        BaseView viewWidget = null;

        if(WIDGET_NUMERIC.equalsIgnoreCase(viewName)) {
            viewWidget = new NumericView(viewModel);
        } else if(WIDGET_BOOLEAN.equalsIgnoreCase(viewName)) {
            viewWidget = new ChoiceView(viewModel);
        } else if(WIDGET_MULTIPLE_CHOICE.equalsIgnoreCase(viewName)) {
            viewWidget = new MultipleChoiceView(viewModel);
        } else if(WIDGET_LANDING.equalsIgnoreCase(viewName)) {
            viewWidget = new LandingTableView(viewModel);
        }

        return viewWidget;
    }
}
