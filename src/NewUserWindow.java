import DataBase.DataBase;
import Users.User;

import javax.swing.*;
import java.awt.*;
import java.nio.file.attribute.GroupPrincipal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewUserWindow extends JFrame {
    public JTextField name = new JTextField();
    public JTextField phone = new JTextField();
    GridBagConstraints gbc = new GridBagConstraints();


    public JButton save = new JButton("Save");
    public JButton cancel = new JButton("Cancel");

    public NewUserWindow (){
        setLayout(new GridBagLayout());
        setSize(300, 300);

        gbc = new GridBagConstraints(
                0, 0, 3, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );
        add(name, gbc);

        gbc = new GridBagConstraints(
                0, 1, 3, 1, 1, 1,
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

        cancel.addActionListener(e -> {dispose();});

        save.addActionListener(e -> {
            User person = new User(name.getText(), phone.getText());
            List<String> users = null;
            try {
                DataBase.addUser(person);
                users = DataBase.SelectUsers();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            DefaultListModel<String> model = new DefaultListModel<>();

            for (String name : users){
                model.addElement(name);
            }

             Application.instance.CreateList(model);
            dispose();
        });

    }
}
