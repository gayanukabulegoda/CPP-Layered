package lk.grb.ceylonPottersPaletteLayered.dao.custom;

import lk.grb.ceylonPottersPaletteLayered.dao.SuperDAO;

import java.sql.SQLException;

public interface UpdateRepairStockDAO extends SuperDAO {
    boolean updateRepairStock(String product_Id, String qty) throws SQLException;
}
