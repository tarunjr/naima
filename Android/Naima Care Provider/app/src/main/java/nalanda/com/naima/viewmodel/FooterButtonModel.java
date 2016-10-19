package nalanda.com.naima.viewmodel;

import nalanda.com.naima.widgets.FooterButton;

/**
 * Created by ps1 on 9/10/16.
 */
public class FooterButtonModel implements BaseViewModel{
    private int buttonLabel;

    public FooterButtonModel() {
        buttonLabel = 0;
    }

    public FooterButtonModel(int buttonLabel) {
        this.buttonLabel = buttonLabel;
    }

    public int getButtonLabel() {
        return buttonLabel;
    }

    public void setButtonLabel(int buttonLabel) {
        this.buttonLabel = buttonLabel;
    }
}
