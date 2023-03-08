package lk.ijse.jdbc;/*
    @author DanujaV
    @created 3/8/23 - 2:04 PM   
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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

    private static void updateCustomer() {
        // UPDATE Customer SET name = \"Saman\", address = \"Galle\", salary = 10000 WHERE id = \"C001\""
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        insertCustomer();

        deleteCustomer();

        updateCustomer();
    }

}
