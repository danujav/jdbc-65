package lk.ijse.thogakade.model;

/*
    @author DanujaV
    @created 3/13/23 - 11:48 AM   
*/

import lk.ijse.thogakade.db.DBConnection;
import lk.ijse.thogakade.dto.CartDTO;
import lk.ijse.thogakade.dto.Item;
import lk.ijse.thogakade.util.CrudUtil;

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
        String sql = "INSERT INTO Item(code, description, unitPrice, qtyOnHand) VALUES(?, ?, ?, ?)";

        return CrudUtil.execute(sql, code, description, unitPrice, qtyOnHand);



    }
    public static boolean update(Item item) throws SQLException {
        String sql = "UPDATE Item SET description = ?, unitPrice = ?, qtyOnHand = ? WHERE code = ?";
        return CrudUtil.execute(sql, item.getDescription(), item.getUnitPrice(), item.getQtyOnHand(), item.getCode());
    }

    public static Item search(String code) throws SQLException {
        String sql = "SELECT * FROM Item WHERE code = ?";
        ResultSet resultSet = CrudUtil.execute(sql, code);

        if(resultSet.next()) {
            String item_code = resultSet.getString(1);
            String item_description = resultSet.getString(2);
            Double item_unit_price = resultSet.getDouble(3);
            Integer item_qty_on_hand = resultSet.getInt(4);

            return new Item(item_code, item_description, item_unit_price, item_qty_on_hand);
        }
        return null;
    }

    public static List<Item> searchAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Item");
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

    public static boolean updateQty(List<CartDTO> cartDTOList) throws SQLException {
        for (CartDTO dto : cartDTOList) {
            if(!updateQty(dto)) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(CartDTO dto) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "UPDATE Item SET qtyOnHand = (qtyOnHand - ?) WHERE code = ?";

        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1, dto.getQty());
        pstm.setString(2, dto.getCode());

        return pstm.executeUpdate() > 0;
    }
}
