package lk.ijse.thogakade.model;

/*
    @author DanujaV
    @created 3/13/23 - 11:48 AM   
*/

import lk.ijse.thogakade.db.DBConnection;
import lk.ijse.thogakade.dto.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ItemModel {
    private final static String URL = "jdbc:mysql://localhost:3306/ThogaKade";
    private final static Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "Danu25412541@");
    }
    public static boolean save(String code, String description, double unitPrice, int qtyOnHand) throws SQLException {
        try(Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "INSERT INTO Item(code, description, unitPrice, qtyOnHand) VALUES(?, ?, ?, ?)";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, code);
            pstm.setString(2, description);
            pstm.setDouble(3, unitPrice);
            pstm.setInt(4, qtyOnHand);

            int affectedRows = pstm.executeUpdate();
            if(affectedRows > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    /*public static boolean update(String code, String description, double unitPrice, int qtyOnHand) throws SQLException {
        try(Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "UPDATE Item SET description = ?, unitPrice = ?, qtyOnHand = ? WHERE code = ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, description);
            pstm.setDouble(2, unitPrice);
            pstm.setInt(3, qtyOnHand);
            pstm.setString(4, code);

            return pstm.executeUpdate() > 0;
        }

    }*/
    public static boolean update(Item item) throws SQLException {
        try(Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "UPDATE Item SET description = ?, unitPrice = ?, qtyOnHand = ? WHERE code = ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, item.getDescription());
            pstm.setDouble(2, item.getUnitPrice());
            pstm.setInt(3, item.getQtyOnHand());
            pstm.setString(4, item.getCode());

            return pstm.executeUpdate() > 0;
        }
    }

    public static Item search(String code) throws SQLException {
        try(Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "SELECT * FROM Item WHERE code = ?";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, code);

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()) {
                String item_code = resultSet.getString(1);
                String item_description = resultSet.getString(2);
                Double item_unit_price = resultSet.getDouble(3);
                Integer item_qty_on_hand = resultSet.getInt(4);

                return new Item(item_code, item_description, item_unit_price, item_qty_on_hand);
            }
            return null;
        }
    }

    public static List<Item> searchAll() throws SQLException {
        try(Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "SELECT * FROM Item";

            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();

            List<Item> dataList = new ArrayList<>();

            while (resultSet.next()) {
                String item_code = resultSet.getString(1);
                String item_description = resultSet.getString(2);
                Double item_unit_price = resultSet.getDouble(3);
                Integer item_qty_on_hand = resultSet.getInt(4);

                var item = new Item(item_code, item_description, item_unit_price, item_qty_on_hand);
                dataList.add(item);
            }
            return dataList;
        }
    }

    public static boolean delete(String code) throws SQLException {
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = "DELETE FROM Item WHERE code = ?";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, code);

            return pstm.executeUpdate() > 0;
        }
    }

    public static List<String> getCodes() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();

        List<String> codes = new ArrayList<>();

        String sql = "SELECT code FROM Item";
        ResultSet resultSet = con.createStatement().executeQuery(sql);
        while(resultSet.next()) {
            codes.add(resultSet.getString(1));
        }
        return codes;
    }

    public static Item searchById(String code) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Item WHERE code = ?";

        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, code);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            );
        }
        return null;
    }
}
