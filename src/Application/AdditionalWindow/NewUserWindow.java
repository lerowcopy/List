package Application.AdditionalWindow;

import Application.AdditionalWindow.Action.AddAction;
import Application.AdditionalWindow.Action.CancelAction;
import Application.AdditionalWindow.Action.ListMouseAdapter;
import Application.AdditionalWindow.Action.NewContactAction;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class NewUserWindow extends JPanel {
    public JTextField name = new JTextField();
    public Vector<String> contacts = new Vector<>();
    public Vector<String> contactTypesToAdd = new Vector<>();

    public JList<String> fieldJList = new JList<>();
    public JLabel nameL = new JLabel("Name");
    public JLabel contact = new JLabel("Contact");

    public JButton save = new JButton("Add");
    public JButton newContact = new JButton("Add new contact");
    public JButton cancel = new JButton("Cancel");
    GridBagConstraints gbc;
    public static NewUserWindow instance;


    public NewUserWindow (){
        instance = this;

        setLayout(new GridBagLayout());
        setSize(300, 300);
        setVisible(true);

        gbc = new GridBagConstraints(
                0, 0, 3, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );
        add(nameL, gbc);

        gbc = new GridBagConstraints(
                0, 1, 3, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );
        add(name, gbc);

        gbc = new GridBagConstraints(
                0, 2, 3, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );

        add(contact, gbc);

        gbc = new GridBagConstraints(
                0, 3, 3, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );

        add(fieldJList, gbc);

        gbc = new GridBagConstraints(
                0, 4, 3, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );

        add(newContact, gbc);

        gbc = new GridBagConstraints(
                0, 5, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );
        add(cancel, gbc);

        gbc = new GridBagConstraints(
                1, 5, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );

        add(save, gbc);


        cancel.addActionListener(new CancelAction());
        newContact.addActionListener(new NewContactAction());
        fieldJList.addMouseListener(new ListMouseAdapter());
        save.addActionListener(new AddAction());

    }
}
