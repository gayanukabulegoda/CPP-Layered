package lk.grb.ceylonPottersPaletteLayered.dao.custom;

import lk.grb.ceylonPottersPaletteLayered.db.DbConnection;
import lk.grb.ceylonPottersPaletteLayered.dto.ProductStockDto;
import lk.grb.ceylonPottersPaletteLayered.entity.ProductStock;
import lk.grb.ceylonPottersPaletteLayered.util.CrudUtil;
import lk.grb.ceylonPottersPaletteLayered.util.SQLUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductStockDAO extends CrudUtil<ProductStock> {
    boolean updateFromPopUp(ProductStock entity) throws SQLException;

    boolean update(ArrayList<String[]> arrayList) throws SQLException;

    boolean update(String id, String qty) throws SQLException;

    boolean updateIncrement(String id, String qty) throws SQLException;

    String getDescription(String id) throws SQLException;

    String getUnitPrice(String id) throws SQLException;

    String getQtyOnHand(String id) throws SQLException;

    String[] descAndUnitPriceGet(String id) throws SQLException;

    String getQtyTotal(String id) throws SQLException;
}
