package Application.AdditionalWindow.Action;

import Application.AdditionalWindow.NewUserWindow;
import Application.DataBase.DataBase;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Vector;

public class ListMouseAdapter extends MouseAdapter {
    private final NewUserWindow newUserInstance = NewUserWindow.instance;
    public void mouseClicked(MouseEvent e){
        if (e.getClickCount() == 2){
            Vector<String> types;
            try {
                types = DataBase.getContact_Type();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            JComboBox<String> box = new JComboBox<>(types);
            String result = JOptionPane.showInputDialog(newUserInstance, box);

            int selectedIndex = newUserInstance.fieldJList.getSelectedIndex();

            newUserInstance.contacts.set(selectedIndex, result);
            newUserInstance.fieldJList.setListData(newUserInstance.contacts);

            try {
                newUserInstance.contactTypesToAdd.set(selectedIndex, DataBase.getIdByType(box.getItemAt(box.getSelectedIndex())));

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
