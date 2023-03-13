package lk.ijse.jdbc;

/*
    @author DanujaV
    @created 3/8/23 - 10:36 AM   
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Set;

public class JDBCConfig {
    private final static String URL = "jdbc:mysql://localhost:3306/ThogaKade";
    private final static Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "Danu25412541@");
    }
    private static Connection connection;
    private static Statement statement;
    private static void loadRegisterDriver() throws SQLException, ClassNotFoundException {
//        DriverManager.registerDriver(new Driver());
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    private static void establishedConnection() throws SQLException {
        /*connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ThogaKade",
                "root",
                "Danu25412541@"
        );*/

        connection = DriverManager.getConnection(URL, props);
    }

    private static void createStatement() throws SQLException {
        statement = connection.createStatement();
    }

    private static void executeQuery() throws SQLException {
        String sql = "CREATE TABLE gdsee(id INTEGER)";

        int affectedRows = statement.executeUpdate(sql);
        System.out.println("affectedRows: " + affectedRows);    //0
    }

    private static void closeConnection() throws SQLException {
        connection.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        loadRegisterDriver();   //1
        establishedConnection();    //2
        createStatement();  //3
        executeQuery(); //4
        closeConnection();  //5


    }
}
