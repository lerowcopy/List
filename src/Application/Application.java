package Application;

import Application.AdditionalWindow.EditUserWindow;
import Application.AdditionalWindow.NewUserWindow;
import Application.DataBase.DataBase;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Vector;

public class Application extends JFrame {

    private static JList<String> list = new JList<>();
    JPanel panel = new JPanel(new BorderLayout());
    GridBagConstraints gbc;
    public static Application instance;


    public Application () throws SQLException {
        DataBase dataBase = new DataBase();
        dataBase.connect();

        instance = this;
        setSize(300, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

            panel.add(list, BorderLayout.CENTER);
        }
        else{
            panel.add(list, BorderLayout.CENTER);
        }

        gbc = new GridBagConstraints(
                0, 6, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );

        JButton newUser = new JButton("+");
        add(newUser, gbc);

        gbc = new GridBagConstraints(
                1, 6, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );

        JButton deleteUser = new JButton("-");
        add(deleteUser, gbc);

        gbc = new GridBagConstraints(
                2, 6, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 5, 1, 5), 0, 0
        );

        JButton editUser = new JButton("edit");
        add(editUser, gbc);

        newUser.addActionListener(e -> {
            NewUserWindow wnd = new NewUserWindow();
            wnd.setVisible(true);
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
            try {
                Vector<String> users = DataBase.SelectUsers();
                EditUserWindow wnd = new EditUserWindow(DataBase.selectUser(users.get(list.getSelectedIndex())));
                wnd.setVisible(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void addList() throws SQLException {
        Vector<String> users = DataBase.SelectUsers();

        list.setListData(users);

        panel.updateUI();
    }

    public static void main (String[] args) throws SQLException {
        Application wnd = new Application();
        wnd.setVisible(true);
    }
}
