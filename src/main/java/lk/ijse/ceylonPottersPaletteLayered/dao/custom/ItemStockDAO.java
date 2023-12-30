package lk.ijse.ceylonPottersPaletteLayered.dao.custom;

import lk.ijse.ceylonPottersPaletteLayered.entity.ItemStock;
import lk.ijse.ceylonPottersPaletteLayered.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemStockDAO extends CrudUtil<ItemStock> {
    boolean updateFromPopUp(ItemStock entity) throws SQLException;

    boolean update(ArrayList<String[]> arrayList) throws SQLException;

    String getDescription(String id) throws SQLException;

    String getUnitPrice(String id) throws SQLException;

    String getQtyOnHand(String id) throws SQLException;

    String[] descAndUnitPriceGet(String id) throws SQLException;

    String getAvailableClayStock() throws SQLException;
}
