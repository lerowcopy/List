package DataBase;

import Users.User;

import java.beans.PropertyEditorManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    public static Connection con = null;

    public void connect () throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:users.db");
        String sql = """
                CREATE TABLE IF NOT EXISTS users (
                     id INTEGER PRIMARY KEY,
                     name TEXT,
                     phone TEXT
                 )""";
        PreparedStatement st = con.prepareStatement(sql);
        st.execute();
    }

    public static List<String> SelectUsers() throws SQLException {
        String sql = "SELECT name FROM users";
        List<String> result = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            result.add(resultSet.getString(1));
        }
        return result;
    }

    public static void execute(String sql) throws SQLException {
        PreparedStatement st = con.prepareStatement(sql);
        st.execute();
    }

    public static void addUser(User user) throws SQLException {
        String sql = String.format("INSERT INTO users (name, phone) VALUES ('%s', '%s')", user.name, user.phone);
        execute(sql);
    }
}
