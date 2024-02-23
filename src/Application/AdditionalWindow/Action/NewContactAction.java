package Application.AdditionalWindow.Action;

import Application.AdditionalWindow.NewUserWindow;
import Application.DataBase.DataBase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

public class NewContactAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        NewUserWindow newUser = NewUserWindow.instance;
        Vector<String> types;
        try {
            types = DataBase.getContact_Type();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        JComboBox<String> box = new JComboBox<>(types);
        String result = JOptionPane.showInputDialog(newUser, box);

        newUser.contacts.add(result);
        newUser.fieldJList.setListData(newUser.contacts);

        try {
            newUser.contactTypesToAdd.add(DataBase.getIdByType(box.getItemAt(box.getSelectedIndex())));

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
