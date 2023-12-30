package lk.ijse.ceylonPottersPaletteLayered.dao.custom;

import lk.ijse.ceylonPottersPaletteLayered.entity.ProductStock;
import lk.ijse.ceylonPottersPaletteLayered.util.CrudUtil;

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
