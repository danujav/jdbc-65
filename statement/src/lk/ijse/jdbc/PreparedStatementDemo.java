package lk.ijse.jdbc;

/*
    @author DanujaV
    @created 3/8/23 - 3:34 PM   
*/

import java.sql.*;
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

        // try-with-resource
        try (Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "INSERT INTO Customer(id, name, address, salary)" +
                    " VALUES (?, ?, ?, ?)";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, id);
            pstm.setString(2, name);
            pstm.setString(3, address);
            pstm.setDouble(4, salary);

            int affectedRows = pstm.executeUpdate();
            System.out.println(affectedRows > 0 ? "customer added!" : "customer not added!");

//            con.close();  //no more usefull
        }
    }

    private static void searchById(String id) throws SQLException {
        try(Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "SELECT * FROM Customer WHERE id = ?";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, id);

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()) {
                String cus_id = resultSet.getString(1);
                String cus_name = resultSet.getString(2);
                String cus_address = resultSet.getString(3);
                double cus_salary = resultSet.getDouble(4);

                System.out.println(cus_id + " - " + cus_name + " - " + cus_address + " - " + cus_salary);

            }
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String id = "C024", name = "Kasuni", address = "Jaffna";
        double salary = 25000;

        insertCustomer(id, name, address, salary);

        searchById(id);
    }
}
