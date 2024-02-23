package Application.AdditionalWindow.Action.ForEditPanel;

import Application.Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelActionForEdit implements ActionListener {
    Application application = Application.instance;
    @Override
    public void actionPerformed(ActionEvent e) {
        application.add(Application.mainPanel);
        application.remove(application.editUserWindow);
        application.setSize(300, 300);
    }
}
