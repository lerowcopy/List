package Application;

import Application.AdditionalWindow.NewUserWindow;
import Application.DataBase.DataBase;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Vector;

public class Application extends JFrame {

    public JPanel mainPanel;
    public JPanel newUserWindow;
    public JPanel editUserWindow;
    static JList<String> list = ApplicationPanel.list;
    static JPanel panel = ApplicationPanel.panel;

    public static Application instance;

    public Application () throws SQLException {
        instance = this;
        DataBase dataBase = new DataBase();
        dataBase.connect();

        setSize(300, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mainPanel = new ApplicationPanel();
        newUserWindow = new NewUserWindow();

        add(mainPanel);
    }

    public static void addList() throws SQLException {
        Vector<String> users = DataBase.SelectUsers();

        list.setListData(users);

        panel.updateUI();
    }

    public static void main (String[] args) throws SQLException {
        Application wnd = new Application();
        wnd.setVisible(true);
    }
}
