package lk.grb.ceylonPottersPaletteLayered.dao.custom;

import lk.grb.ceylonPottersPaletteLayered.dao.SuperDAO;

import java.sql.SQLException;

public interface RemoveRepairedStockDAO extends SuperDAO {
    boolean removeRepairStock(String product_Id, String qty) throws SQLException;
}
