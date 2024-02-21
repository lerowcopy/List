import DataBase.DataBase;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Application extends JFrame {

    private static JList<String> list = new JList<>();
    private JButton newUser = new JButton("+");
    private  JButton deleteUser = new JButton("-");
    private JButton editUser = new JButton("edit");
    private DataBase dataBase = new DataBase();
    private GridBagConstraints gbc = new GridBagConstraints();

    public static Application instance;


    public Application () throws SQLException {
        instance = this;
        setSize(300, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        DefaultListModel<String> model = new DefaultListModel<>();
        list = new JList<>(model);

        list.setBounds(100, 100, 75, 75);


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

        dataBase.connect();

        if (!DataBase.SelectUsers().isEmpty()){
            List<String> users = new ArrayList<>();
            users = DataBase.SelectUsers();

            DefaultListModel<String> user = new DefaultListModel<>();

            for (String name : users){
                user.addElement(name);
            }

            JList <String> list = new JList<>(model);
            list.setBounds(100, 100, 75, 75);

            gbc = new GridBagConstraints(
                    0, 0, 3, 5, 1, 1,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(1, 5, 1, 5), 0, 0
            );

            add(list, gbc);
        }

        newUser.addActionListener(e -> {
            remove(list);
            NewUserWindow wnd = new NewUserWindow();
            wnd.setVisible(true);
        });
    }

    public void CreateList(DefaultListModel<String> model){

    }

    public static void main (String[] args) throws SQLException {
        Application wnd = new Application();
        wnd.setVisible(true);
    }
};
