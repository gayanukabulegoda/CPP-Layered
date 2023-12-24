package lk.grb.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.dao.custom.ItemStockDAO;
import lk.grb.ceylonPottersPaletteLayered.db.DbConnection;
import lk.grb.ceylonPottersPaletteLayered.dto.ItemStockDto;
import lk.grb.ceylonPottersPaletteLayered.util.SQLUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemStockDAOImpl implements ItemStockDAO {
    @Override
    public boolean save(ItemStockDto itemStockDto) throws SQLException {
        return SQLUtil.execute("INSERT INTO item_Stock VALUES (?,?,?,?)",
                itemStockDto.getItem_Id(),
                itemStockDto.getDescription(),
                itemStockDto.getUnit_Price(),
                itemStockDto.getQty_On_Hand());
    }

    @Override
    public boolean updateFromPopUp(ItemStockDto itemStockDto) throws SQLException {
        return SQLUtil.execute("UPDATE item_Stock SET " +
                        "description=?," +
                        "qty_On_Hand=?," +
                        "unit_Price=?" +
                        "WHERE item_Id=?",
                itemStockDto.getDescription(),
                itemStockDto.getQty_On_Hand(),
                itemStockDto.getUnit_Price(),
                itemStockDto.getItem_Id()
        );
    }

    @Override
    public ItemStockDto getData(String id) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT * FROM item_Stock WHERE item_Id=?", id);

        ItemStockDto itemStockDto = new ItemStockDto();

        if (set.next()) {
            itemStockDto.setItem_Id(set.getString(1));
            itemStockDto.setDescription(set.getString(2));
            itemStockDto.setUnit_Price(Integer.parseInt(set.getString(3)));
            itemStockDto.setQty_On_Hand(Integer.parseInt(set.getString(4)));
        }
        return itemStockDto;
    }

    @Override
    public boolean update(ArrayList<String[]> arrayList) throws SQLException {
        String sql = "UPDATE item_Stock SET qty_On_Hand = qty_On_Hand + ? WHERE item_Id=?";
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
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM item_Stock WHERE item_Id=?", id);
    }

    @Override
    public ArrayList<String> getAllItemId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT item_Id FROM item_Stock ORDER BY LENGTH(item_Id),item_Id");
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public String getDescription(String id) throws SQLException {

        String sql = ("SELECT description FROM item_Stock WHERE item_Id=?");
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

        String sql = ("SELECT unit_Price FROM item_Stock WHERE item_Id=?");
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

        String sql = ("SELECT qty_On_Hand FROM item_Stock WHERE item_Id=?");
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
        String sql = "SELECT description, unit_Price FROM item_Stock WHERE item_Id=?";

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
    public String getAvailableClayStock() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT qty_On_Hand " +
                "FROM item_Stock " +
                "WHERE description like '%Clay'");

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}
