package Application.AdditionalWindow;

import Application.Application;
import Application.DataBase.DataBase;
import Application.Users.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Vector;

public class EditUserWindow extends JPanel {


    public EditUserWindow (Vector<String> user) {
        Application application = Application.instance;

        setLayout(new GridBagLayout());
        setSize(300, 400);
        setVisible(true);

        JTextField name = new JTextField(user.get(1));
        JTextField phone = new JTextField(user.get(2));

        JButton cancel = new JButton("Cancel");
        JButton save = new JButton("Save");

        GridBagConstraints gbc = new GridBagConstraints(
                0, 0, 2, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );

        add(name, gbc);

        gbc = new GridBagConstraints(
                0, 1, 2, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );

        add(phone, gbc);

        gbc = new GridBagConstraints(
                0, 2, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );

        add(cancel, gbc);

        gbc = new GridBagConstraints(
                1, 2, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );

        add(save, gbc);

        cancel.addActionListener(e -> {
            application.remove(this);
            application.setSize(300, 500);
            application.add(application.mainPanel);
        });

        save.addActionListener(e -> {
            try {
                DataBase.updateUser(new User(user.get(1), user.get(2)), name.getText());
                Application.addList();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
