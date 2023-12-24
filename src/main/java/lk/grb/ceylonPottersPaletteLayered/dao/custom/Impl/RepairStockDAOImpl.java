package lk.grb.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.dao.custom.RepairStockDAO;
import lk.grb.ceylonPottersPaletteLayered.db.DbConnection;
import lk.grb.ceylonPottersPaletteLayered.dto.RepairStockDto;
import lk.grb.ceylonPottersPaletteLayered.util.SQLUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepairStockDAOImpl implements RepairStockDAO {
    @Override
    public RepairStockDto getData(String id) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT * FROM repair_Stock WHERE product_Id=?", id);

        RepairStockDto repairStockDto = new RepairStockDto();

        if (set.next()) {
            repairStockDto.setProduct_Id(set.getString(1));
            repairStockDto.setQty_To_Repair(set.getString(2));
        }
        return repairStockDto;
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
}
