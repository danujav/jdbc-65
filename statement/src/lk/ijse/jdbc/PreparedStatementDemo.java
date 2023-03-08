package lk.ijse.jdbc;

/*
    @author DanujaV
    @created 3/8/23 - 3:34 PM   
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class PreparedStatementDemo {
    private final static String URL = "jdbc:mysql://localhost:3306/ThogaKade";
    private final static Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "Danu25412541@");
    }
    private static void insertCustomer(String id, String name, String address, double salary) throws ClassNotFoundException, SQLException {
        //automatically JVM load this Driver to the Heap
//        Class.forName("com.mysql.cj.jdbc.Driver");


        Connection con = DriverManager.getConnection(URL, props);
        String sql = "INSERT INTO Customer(id, name, address, salary)" +
                " VALUES (?, ?, ?, ?)";

        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, id);
        pstm.setString(2, name);
        pstm.setString(3, address);
        pstm.setDouble(4, salary);

        int affectedRows = pstm.executeUpdate();
        System.out.println(affectedRows > 0 ? "customer added!" : "customer not added!");

        con.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String id = "C024", name = "Kasuni", address = "Jaffna";
        double salary = 25000;

        insertCustomer(id, name, address, salary);
    }
}
