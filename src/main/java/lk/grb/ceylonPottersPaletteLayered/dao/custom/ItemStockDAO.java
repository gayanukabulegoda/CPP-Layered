package lk.grb.ceylonPottersPaletteLayered.dao.custom;

import lk.grb.ceylonPottersPaletteLayered.db.DbConnection;
import lk.grb.ceylonPottersPaletteLayered.dto.ItemStockDto;
import lk.grb.ceylonPottersPaletteLayered.entity.ItemStock;
import lk.grb.ceylonPottersPaletteLayered.util.CrudUtil;
import lk.grb.ceylonPottersPaletteLayered.util.SQLUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemStockDAO extends CrudUtil<ItemStock> {
    boolean save(ItemStockDto itemStockDto) throws SQLException;

    boolean updateFromPopUp(ItemStockDto itemStockDto) throws SQLException;

    ItemStockDto getData(String id) throws SQLException;

    boolean update(ArrayList<String[]> arrayList) throws SQLException;

    boolean delete(String id) throws SQLException;

    ArrayList<String> getAllItemId() throws SQLException;

    String getDescription(String id) throws SQLException;

    String getUnitPrice(String id) throws SQLException;

    String getQtyOnHand(String id) throws SQLException;

    String[] descAndUnitPriceGet(String id) throws SQLException;

    String getAvailableClayStock() throws SQLException;
}
