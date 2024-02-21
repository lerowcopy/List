package Application.DataBase;

import Application.Users.User;

import java.sql.*;
import java.util.Vector;

public class DataBase {
    public static Connection con = null;

    public void connect () throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:users.db");
        String sql = """
                CREATE TABLE IF NOT EXISTS users (
                     id INTEGER PRIMARY KEY,
                     name TEXT UNIQUE,
                     phone TEXT
                 )""";
        PreparedStatement st = con.prepareStatement(sql);
        st.execute();
    }

    public static Vector<String> SelectUsers() throws SQLException {
        String sql = "SELECT name FROM users";
        Vector<String> result = new Vector<>();
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

    public static void deleteUser(String name) throws SQLException {
        String sql = String.format("DELETE FROM users WHERE name = '%s'", name);
        execute(sql);
    }

    public static void updateUser(User user, String newName) throws SQLException {
        String sql = String.format("UPDATE users SET name = '%s', phone = '%s' WHERE name = '%s'", newName, user.phone, user.name);
        execute(sql);
    }

    public static Vector<String> selectUser(String name) throws SQLException {
        String sql = String.format("SELECT * FROM users WHERE name = '%s'", name);
        Vector<String> data = new Vector<>();

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        data.add(resultSet.getString(1));
        data.add(resultSet.getString(2));
        while (resultSet.next()){
            data.add(resultSet.getString(3));
        }
        return data;
    }
}
