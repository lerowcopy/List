package Application.AdditionalWindow.Action;

import Application.Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelAction implements ActionListener {
    Application application = Application.instance;
    @Override
    public void actionPerformed(ActionEvent e) {
        application.add(Application.mainPanel);
        application.remove(application.newUserWindow);
        application.setSize(300, 300);
    }
}
