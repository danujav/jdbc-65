package lk.ijse.jdbc;

/*
    @author DanujaV
    @created 3/8/23 - 2:04 PM   
*/

import java.sql.*;
import java.util.Properties;

public class StatementDemo {
    private final static String URL = "jdbc:mysql://localhost:3306/ThogaKade";
    private final static Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "Danu25412541@");
    }

    private static void insertCustomer() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ThogaKade",
                "root",
                "Danu25412541@"
        );
        Statement statement = connection.createStatement();

        String sql = "INSERT INTO Customer(id, name, address, salary) " +
                "VALUES(\"C023\", \"Nimna\", \"Kalutara\", 350000)";

        int affectedRows = statement.executeUpdate(sql);
        System.out.println(affectedRows > 0 ? "customer added!" : "customer not added!");

        connection.close();
    }

    private static void deleteCustomer() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection(URL, props);
        Statement statement = con.createStatement();

        String sql = "DELETE FROM Customer WHERE id = \"C023\"";
        int affectedRows = statement.executeUpdate(sql);
        System.out.println(affectedRows > 0 ? "customer deleted!" : "customer not deleted!");

        con.close();
    }

    private static void updateCustomer() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, props);
        Statement statement = con.createStatement();

        String sql = "UPDATE Customer SET name = \"Saman\", address = \"Galle\", salary = 10000 WHERE id = \"C001\"";
        int affectedRows = statement.executeUpdate(sql);

        System.out.println("affectedRows: " + affectedRows);
        System.out.println(affectedRows > 0 ? "customer updated!" : "customer not updated!");
    }

    private static void searchById() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, props);
        Statement statement = con.createStatement();

        String sql = "SELECT * FROM Customer WHERE id = \"C001\"";

        ResultSet resultSet = statement.executeQuery(sql);

        if(resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            double salary = resultSet.getDouble(4);

            System.out.println(id + " - " + name + " - " + address + " - " + salary);

        }
    }

    private static void searchAll() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, props);
        Statement statement = con.createStatement();

        String sql = "SELECT * FROM Customer";
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            double salary = resultSet.getDouble(4);
            System.out.println(id + " - " + name + " - " + address + " - " + salary);
        }
        /*resultSet.next();
        String id = resultSet.getString(1);
        String name = resultSet.getString(2);
        String address = resultSet.getString(3);
        double salary = resultSet.getDouble(4);
        System.out.println(id + " - " + name + " - " + address + " - " + salary);

        resultSet.next();
        String id2 = resultSet.getString(1);
        String name2 = resultSet.getString(2);
        String address2 = resultSet.getString(3);
        double salary2 = resultSet.getDouble(4);
        System.out.println(id2 + " - " + name2 + " - " + address2 + " - " + salary2);*/

        con.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        insertCustomer();

//        deleteCustomer();

        updateCustomer();

//        searchById();

        searchAll();
    }

}
