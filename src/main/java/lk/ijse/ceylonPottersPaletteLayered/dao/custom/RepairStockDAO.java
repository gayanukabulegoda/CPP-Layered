package lk.ijse.ceylonPottersPaletteLayered.dao.custom;

import lk.ijse.ceylonPottersPaletteLayered.entity.RepairStock;
import lk.ijse.ceylonPottersPaletteLayered.util.CrudUtil;

import java.sql.SQLException;

public interface RepairStockDAO extends CrudUtil<RepairStock> {
    boolean update(String id, String qty) throws SQLException;

    boolean updateDecrement(String id, String qty) throws SQLException;

    boolean save(String productId, int qty) throws SQLException;
}
