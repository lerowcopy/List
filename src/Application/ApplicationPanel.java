package Application;

import Application.AdditionalWindow.EditUserWindow;
import Application.DataBase.DataBase;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Vector;

public class ApplicationPanel extends JPanel {
    public static JList<String> list = new JList<>();
    public static JPanel panel = new JPanel(new BorderLayout());
    private final JButton newUser = new JButton("+");
    private final JButton deleteUser = new JButton("-");
    private final JButton editUser = new JButton("edit");

    private final Application application;
    public static ApplicationPanel instance;

    GridBagConstraints gbc;

    public ApplicationPanel () throws SQLException {
        DataBase dataBase = new DataBase();
        dataBase.connect();
        application = Application.instance;
        instance = this;

        setSize(300, 250);
        setLayout(new GridBagLayout());

        gbc = new GridBagConstraints(
                0, 0, 3, 5, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );

        add(panel, gbc);

        if (!DataBase.SelectUsers().isEmpty()){
            Vector<String> users = DataBase.SelectUsers();

            list = new JList<>(users);

        }
        panel.add(list, BorderLayout.CENTER);

        gbc = new GridBagConstraints(
                0, 6, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );

        add(newUser, gbc);

        gbc = new GridBagConstraints(
                1, 6, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );

        add(deleteUser, gbc);

        gbc = new GridBagConstraints(
                2, 6, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );

        add(editUser, gbc);

        Listener();
    }

    private void Listener() {


        newUser.addActionListener(e -> {
            application.add(application.newUserWindow);
            application.remove(Application.mainPanel);
            application.setSize(300, 250);
        });


        deleteUser.addActionListener(e -> {
            try {
                Vector<String> users = DataBase.SelectUsers();
                DataBase.deleteUser(users.get(list.getSelectedIndex()));
                users.remove(list.getSelectedIndex());

                list.setListData(users);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            panel.updateUI();
        });

        editUser.addActionListener(e -> {
            Vector<String> contacts;
            Vector<String> contactsType;
            Vector<String> users;
            try {
                users = DataBase.SelectUsers();
                String name = users.get(list.getSelectedIndex());
                Vector<Vector<String>> data = DataBase.getContactByName(users.get(list.getSelectedIndex()));
                contacts = data.get(0);
                contactsType = data.get(1);
                application.editUserWindow = new EditUserWindow(name, contacts, contactsType);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            application.add(application.editUserWindow);
            application.remove(Application.mainPanel);
            application.setSize(300, 250);

        });
    }

    public static void addList() throws SQLException {
        Vector<String> users = DataBase.SelectUsers();

        list.setListData(users);

        panel.updateUI();
    }

}
