package Application;

import Application.AdditionalWindow.NewUserWindow;
import Application.DataBase.DataBase;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Application extends JFrame {

    public static JPanel mainPanel;
    public JPanel newUserWindow;
    public JPanel editUserWindow;

    public static Application instance;

    public Application () throws SQLException {
        instance = this;
        DataBase dataBase = new DataBase();
        dataBase.connect();

        setSize(300, 301);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mainPanel = new ApplicationPanel();
        newUserWindow = new NewUserWindow();

        add(mainPanel);
    }

    public static void main (String[] args) throws SQLException {
        Application wnd = new Application();
        wnd.setVisible(true);
    }
}
