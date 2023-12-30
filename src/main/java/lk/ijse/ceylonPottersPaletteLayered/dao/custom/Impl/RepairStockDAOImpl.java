package lk.ijse.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.ijse.ceylonPottersPaletteLayered.dao.custom.RepairStockDAO;
import lk.ijse.ceylonPottersPaletteLayered.util.SQLUtil;
import lk.ijse.ceylonPottersPaletteLayered.entity.RepairStock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepairStockDAOImpl implements RepairStockDAO {
    @Override
    public RepairStock getData(String id) throws SQLException {
        ResultSet set = SQLUtil.
                execute("SELECT * FROM repair_Stock WHERE product_Id=?", id);

        RepairStock entity = new RepairStock();

        if (set.next()) {
            entity.setProduct_Id(set.getString(1));
            entity.setQty_To_Repair(set.getString(2));
        }
        return entity;
    }

    @Override
    public boolean update(String id, String qty) throws SQLException {
        return SQLUtil.
                execute("UPDATE repair_Stock SET " +
                        "qty_To_Repair = qty_To_Repair + ? " +
                        "WHERE product_Id = ?", qty, id);
    }

    @Override
    public boolean updateDecrement(String id, String qty) throws SQLException {
        return SQLUtil.
                execute("UPDATE repair_Stock SET " +
                        "qty_To_Repair = qty_To_Repair - ? " +
                        "WHERE product_Id = ?", qty, id);
    }

    @Override
    public boolean save(String productId, int qty) throws SQLException {
        return SQLUtil.
                execute("INSERT INTO repair_Stock VALUES (?,?)", productId, qty);
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
