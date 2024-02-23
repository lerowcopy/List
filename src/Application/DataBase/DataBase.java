package Application.DataBase;

import java.sql.*;
import java.util.Vector;

public class DataBase {
    public static Connection con = null;

    public void connect () throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:users.db");
        String sql = """
                CREATE TABLE IF NOT EXISTS users (
                     id INTEGER PRIMARY KEY,
                     name TEXT UNIQUE
                 )""";
        execute(sql);

        sql = """
                CREATE TABLE IF NOT EXISTS contact(
                    id INTEGER PRIMARY KEY,
                    type TEXT,
                    contact TEXT,
                    idP INTEGER,
                    FOREIGN KEY (idP) REFERENCES users (id)
                );
                """;
        execute(sql);

        sql = """
                CREATE TABLE IF NOT EXISTS contact_type(
                    id INTEGER,
                    type TEXT
                )
                """;
        execute(sql);
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

    public static void deleteUser(String name) throws SQLException {
        String sql = String.format("DELETE FROM users WHERE name = '%s'", name);
        execute(sql);
    }

    public static Vector<Vector<String>> getContactByName(String name) throws SQLException {
        Vector<Vector<String>> result = new Vector<>();
        result.add(new Vector<>());
        result.add(new Vector<>());
        String sql = String.format("SELECT id FROM users WHERE name = '%s'", name);
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        int indexUser;

        indexUser = resultSet.getInt(1);

        sql = String.format("SELECT contact, type FROM contact WHERE idP = %d", indexUser);
        ps = con.prepareStatement(sql);
        resultSet = ps.executeQuery();

        while (resultSet.next()){
            result.get(0).add(resultSet.getString(1));
            result.get(1).add(resultSet.getString(2));
        }
        return result;
    }

    public static Vector<String> getContact_Type () throws SQLException {
        Vector<String> result = new Vector<>();

        String sql = "SELECT type FROM contact_type";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()){
            result.add(resultSet.getString(1));
        }

        return result;
    }

    public static String getIdByType (String type) throws SQLException {
        String id;

        String sql = String.format("SELECT id FROM contact_type WHERE type = '%s'", type);

        PreparedStatement ps = con.prepareStatement(sql);
        id = ps.executeQuery().getString(1);
        return id;
    }

    public static void addUser(String name, Vector<String> contacts, Vector<String> idContactsToAdd) throws SQLException {
        String sql = String.format("INSERT INTO users (name) VALUES ('%s')", name);
        execute(sql);

        int indexUser;

        sql = String.format("SELECT id FROM users WHERE name = '%s'", name);
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        indexUser = resultSet.getInt(1);

        for (int i = 0; i < contacts.size(); ++i){
            sql = String.format("INSERT INTO contact (type, contact, idP) VALUES ('%s', '%s', %d)", idContactsToAdd.get(i), contacts.get(i), indexUser);
            execute(sql);
        }
    }

    public static void updateUser (String name, Vector<String> contacts, Vector<String> idContactsToAdd) throws SQLException{
        int indexUser;

        String sql = String.format("SELECT id FROM users WHERE name = '%s'", name);
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        indexUser = resultSet.getInt(1);

        sql = String.format("DELETE FROM contact WHERE idP = %d", indexUser);
        execute(sql);

        for (int i = 0; i < contacts.size(); ++i){
            sql = String.format("INSERT INTO contact (type, contact, idP) VALUES ('%s', '%s', %d)", idContactsToAdd.get(i), contacts.get(i), indexUser);
            execute(sql);
        }

    }
}
