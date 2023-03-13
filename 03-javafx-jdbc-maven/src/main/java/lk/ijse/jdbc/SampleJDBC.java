package lk.ijse.jdbc;

/*
    @author DanujaV
    @created 3/8/23 - 4:10 PM   
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class SampleJDBC {
    private final static String URL = "jdbc:mysql://localhost:3306/ThogaKade";
    private final static Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "Danu25412541@");
    }

    public static void main(String[] args) throws SQLException {
        try(Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "INSERT INTO Customer(id, name, address, salary)" +
                    " VALUES (?, ?, ?, ?)";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, "C026");
            pstm.setString(2, "Sepala");
            pstm.setString(3, "USA");
            pstm.setDouble(4, 2566);

            int affectedRows = pstm.executeUpdate();
            System.out.println(affectedRows > 0 ? "customer added!" : "customer not added!");
        }
    }
}
