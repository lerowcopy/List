package Application.AdditionalWindow.Action.ForEditPanel;

import Application.AdditionalWindow.EditUserWindow;
import Application.DataBase.DataBase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

public class NewContactActionForEdit implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        EditUserWindow editUserInstance = EditUserWindow.instance;
        Vector<String> types;
        try {
            types = DataBase.getContact_Type();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        JComboBox<String> box = new JComboBox<>(types);
        String result = JOptionPane.showInputDialog(editUserInstance, box);

        editUserInstance.contact.add(result);
        editUserInstance.contactsList.setListData(editUserInstance.contact);

        try {
            editUserInstance.contactsType.add(DataBase.getIdByType(box.getItemAt(box.getSelectedIndex())));

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
