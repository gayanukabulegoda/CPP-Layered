package lk.grb.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.dao.custom.ProductStockDAO;
import lk.grb.ceylonPottersPaletteLayered.db.DbConnection;
import lk.grb.ceylonPottersPaletteLayered.dto.ProductStockDto;
import lk.grb.ceylonPottersPaletteLayered.entity.ProductStock;
import lk.grb.ceylonPottersPaletteLayered.util.SQLUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductStockDAOImpl implements ProductStockDAO {
    @Override
    public boolean save(ProductStock entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO product_Stock VALUES (?,?,?,?,?,?)",
                entity.getProduct_Id(),
                entity.getDescription(),
                entity.getQty_On_Hand(),
                entity.getUnit_Price(),
                entity.getCategory(),
                entity.getQty());
    }

    @Override
    public boolean updateFromPopUp(ProductStock entity) throws SQLException {
        return SQLUtil.execute("UPDATE product_Stock SET " +
                        "description=?," +
                        "qty_On_Hand=?," +
                        "unit_Price=?," +
                        "category=? " +
                        "WHERE product_Id=?",
                entity.getDescription(),
                entity.getQty_On_Hand(),
                entity.getUnit_Price(),
                entity.getCategory(),
                entity.getProduct_Id()
        );
    }

    @Override
    public ProductStock getData(String id) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT * FROM product_Stock WHERE product_Id=?", id);

        ProductStock entity = new ProductStock();

        if (set.next()) {
            entity.setProduct_Id(set.getString(1));
            entity.setDescription(set.getString(2));
            entity.setQty_On_Hand(Integer.parseInt(set.getString(3)));
            entity.setUnit_Price(Double.parseDouble(set.getString(4)));
            entity.setCategory(set.getString(5));
            entity.setQty(Integer.parseInt(set.getString(6)));
        }
        return entity;
    }

    @Override
    public boolean update(ArrayList<String[]> arrayList) throws SQLException {
        String sql = "UPDATE product_Stock SET qty_On_Hand = qty_On_Hand - ? WHERE product_Id=?";
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        for (int i = 0; i < arrayList.size() ; i++) {
            statement.setInt(1, Integer.parseInt(arrayList.get(i)[1]));
            statement.setString(2,arrayList.get(i)[0]);
            int value = statement.executeUpdate();

            if (value == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean update(String id, String qty) throws SQLException {
        String sql = "UPDATE product_Stock SET qty_On_Hand = qty_On_Hand - ? WHERE product_Id=?";
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        statement.setString(1,qty);
        statement.setString(2,id);
        int i = statement.executeUpdate();

        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateIncrement(String id, String qty) throws SQLException {
        String sql = "UPDATE product_Stock SET qty_On_Hand = qty_On_Hand + ? WHERE product_Id=?";
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        statement.setString(1,qty);
        statement.setString(2,id);
        int i = statement.executeUpdate();

        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE ps, rs " +
                        "FROM product_Stock ps " +
                        "JOIN repair_Stock rs ON ps.product_Id = rs.product_Id " +
                        "WHERE ps.product_Id = ?", id);
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT product_Id FROM product_Stock ORDER BY LENGTH(product_Id),product_Id");
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public String getDescription(String id) throws SQLException {

        String sql = ("SELECT description FROM product_Stock WHERE product_Id=?");
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1,id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public String getUnitPrice(String id) throws SQLException {

        String sql = ("SELECT unit_Price FROM product_Stock WHERE product_Id=?");
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1,id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public String getQtyOnHand(String id) throws SQLException {

        String sql = ("SELECT qty_On_Hand FROM product_Stock WHERE product_Id=?");
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1,id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public String[] descAndUnitPriceGet(String id) throws SQLException {
        String sql = "SELECT description, unit_Price FROM product_Stock WHERE product_Id=?";

        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setString(1,id);

        ResultSet resultSet = preparedStatement.executeQuery();

        String[] set = new String[2];

        if (resultSet.next()) {
            set[0] = resultSet.getString(1);
            set[1] = resultSet.getString(2);
         }

        return set;
    }

    @Override
    public String getQtyTotal(String id) throws SQLException {

        String sql = ("SELECT qty_On_Hand FROM product_Stock WHERE product_Id=?");
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1,id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean update(ProductStock dto) throws SQLException {
        return false;
    }
}