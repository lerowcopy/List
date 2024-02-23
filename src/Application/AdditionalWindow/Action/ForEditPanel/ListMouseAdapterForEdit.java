package Application.AdditionalWindow.Action.ForEditPanel;

import Application.AdditionalWindow.EditUserWindow;
import Application.DataBase.DataBase;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Vector;

public class ListMouseAdapterForEdit extends MouseAdapter {
    EditUserWindow editUserInstance = EditUserWindow.instance;
    public void mouseClicked(MouseEvent e){
        if (e.getClickCount() == 2){
            Vector<String> types;
            try {
                types = DataBase.getContact_Type();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            JComboBox<String> box = new JComboBox<>(types);
            String result = JOptionPane.showInputDialog(editUserInstance, box);

            int selectedIndex = editUserInstance.contactsList.getSelectedIndex();
            editUserInstance.contact.set(selectedIndex, result);
            editUserInstance.contactsList.setListData(editUserInstance.contact);

            try {
                if (box.getSelectedIndex() != -1){
                    editUserInstance.contactsType.set(selectedIndex, DataBase.getIdByType(box.getItemAt(box.getSelectedIndex())));

                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
