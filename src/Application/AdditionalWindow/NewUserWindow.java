package Application.AdditionalWindow;

import Application.DataBase.DataBase;
import Application.Users.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Vector;

import Application.Application;

public class NewUserWindow extends JFrame {
    public JTextField name = new JTextField();
    public JLabel nameL = new JLabel("Name");
    Vector<JTextField> phones = new Vector<>();
    public JTextField phone = new JTextField();
    public JLabel phoneL = new JLabel("Phone");
    GridBagConstraints gbc;


    public JButton save = new JButton("Add");
    public JButton newPhone = new JButton("Add new phone");
    public JButton cancel = new JButton("Cancel");
    public int indexPhone = 0;

    public NewUserWindow (){
        setLayout(new GridBagLayout());
        setSize(300, 300);
        phones.add(phone);
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
                indexPhone, 2, 3, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );

        add(phoneL, gbc);

        gbc = new GridBagConstraints(
                indexPhone, 3, 3, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );

        add(phone, gbc);

        gbc = new GridBagConstraints(
                0, 4, 3, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );

        add(newPhone, gbc);

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

        cancel.addActionListener(e -> dispose());

        save.addActionListener(e -> {
            User person = new User(name.getText(), phone.getText());
            try {
                DataBase.addUser(person);
                Application.instance.addList();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });

        newPhone.addActionListener(e -> {
            JTextField phone = new JTextField();

        });

    }
}
