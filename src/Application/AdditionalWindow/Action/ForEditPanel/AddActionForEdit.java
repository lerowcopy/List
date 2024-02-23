package Application.AdditionalWindow.Action.ForEditPanel;

import Application.AdditionalWindow.EditUserWindow;
import Application.Application;
import Application.ApplicationPanel;
import Application.DataBase.DataBase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddActionForEdit implements ActionListener {
    EditUserWindow editUserInstance = EditUserWindow.instance;
    Application application = Application.instance;
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            DataBase.updateUser(editUserInstance.saveName ,editUserInstance.name.getText(), editUserInstance.contact, editUserInstance.contactsType);
            application.remove(editUserInstance);
            application.add(Application.mainPanel);
            application.setSize(300, 300);
            ApplicationPanel.list.setListData(DataBase.SelectUsers());
            Application.mainPanel.updateUI();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
