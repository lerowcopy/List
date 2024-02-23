package Application.AdditionalWindow;

import Application.AdditionalWindow.Action.ForEditPanel.AddActionForEdit;
import Application.AdditionalWindow.Action.ForEditPanel.CancelActionForEdit;
import Application.AdditionalWindow.Action.ForEditPanel.ListMouseAdapterForEdit;
import Application.AdditionalWindow.Action.ForEditPanel.NewContactActionForEdit;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class EditUserWindow extends JPanel {

    public Vector<String> contact;
    public Vector<String> contactsType;
    public JList<String> contactsList = new JList<>();
    public JTextField name;
    public JButton add = new JButton("Add new contact");
    public static EditUserWindow instance;
    public EditUserWindow (String nameE, Vector<String> contactE, Vector<String> contactsTypeE) {
        instance = this;

        contact = contactE;
        contactsType = contactsTypeE;

        setLayout(new GridBagLayout());
        setSize(300, 400);
        setVisible(true);

        name = new JTextField(nameE);

        JButton cancel = new JButton("Cancel");
        JButton save = new JButton("Save");

        GridBagConstraints gbc = new GridBagConstraints(
                0, 0, 2, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );

        add(name, gbc);

        gbc = new GridBagConstraints(
                0, 1, 2, 1, 1, 5,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );

        contactsList.setListData(contact);
        add(contactsList, gbc);

        gbc = new GridBagConstraints(
                0, 2, 2, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );

        add(add, gbc);

        gbc = new GridBagConstraints(
                0, 3, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );

        add(cancel, gbc);

        gbc = new GridBagConstraints(
                1, 3, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );

        add(save, gbc);

        cancel.addActionListener(new CancelActionForEdit());
        contactsList.addMouseListener(new ListMouseAdapterForEdit());
        add.addActionListener(new NewContactActionForEdit());
        save.addActionListener(new AddActionForEdit());
    }
}
