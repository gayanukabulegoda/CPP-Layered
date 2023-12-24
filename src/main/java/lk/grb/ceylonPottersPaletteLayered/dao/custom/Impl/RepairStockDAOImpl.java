package lk.grb.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.dao.custom.RepairStockDAO;
import lk.grb.ceylonPottersPaletteLayered.db.DbConnection;
import lk.grb.ceylonPottersPaletteLayered.dto.RepairStockDto;
import lk.grb.ceylonPottersPaletteLayered.entity.RepairStock;
import lk.grb.ceylonPottersPaletteLayered.util.SQLUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepairStockDAOImpl implements RepairStockDAO {
    @Override
    public RepairStock getData(String id) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT * FROM repair_Stock WHERE product_Id=?", id);

        RepairStock entity = new RepairStock();

        if (set.next()) {
            entity.setProduct_Id(set.getString(1));
            entity.setQty_To_Repair(set.getString(2));
        }
        return entity;
    }

    @Override
    public boolean update(String id, String qty) throws SQLException {
        String sql = "UPDATE repair_Stock SET qty_To_Repair = qty_To_Repair + ? WHERE product_Id=?";
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
    public boolean updateDecrement(String id, String qty) throws SQLException {
        String sql = "UPDATE repair_Stock SET qty_To_Repair = qty_To_Repair - ? WHERE product_Id=?";
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
    public boolean save(String productId, int qty) throws SQLException {
        return SQLUtil.execute("INSERT INTO repair_Stock VALUES (?,?)",productId,qty);
    }

    @Override
    public boolean save(RepairStock dto) throws SQLException {
        return false;
    }

    @Override
    public boolean update(RepairStock dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException {
        return null;
    }
}
