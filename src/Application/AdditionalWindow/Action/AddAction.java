package Application.AdditionalWindow.Action;

import Application.AdditionalWindow.NewUserWindow;
import Application.Application;
import Application.DataBase.DataBase;
import Application.ApplicationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddAction implements ActionListener {
    NewUserWindow newUserInstance = NewUserWindow.instance;
    Application application = Application.instance;
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            DataBase.addUser(newUserInstance.name.getText() ,newUserInstance.contacts, newUserInstance.contactTypesToAdd);
            application.remove(newUserInstance);
            application.add(Application.mainPanel);
            application.setSize(300, 300);
            ApplicationPanel.addList();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
