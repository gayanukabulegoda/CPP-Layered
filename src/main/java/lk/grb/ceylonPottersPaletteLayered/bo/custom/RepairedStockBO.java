package lk.grb.ceylonPottersPaletteLayered.bo.custom;

import lk.grb.ceylonPottersPaletteLayered.bo.SuperBO;
import lk.grb.ceylonPottersPaletteLayered.dto.ProductStockDto;
import lk.grb.ceylonPottersPaletteLayered.dto.RepairStockDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RepairedStockBO extends SuperBO {
    RepairStockDto getRepairStockData(String id) throws SQLException;

    ProductStockDto getProductStockData(String id) throws SQLException;

    boolean removeRepairStock(String product_Id, String qty) throws SQLException;

    boolean updateRepairStock(String product_Id, String qty) throws SQLException;

    ArrayList<String> getAllProductId() throws SQLException;

    String getProductDescription(String id) throws SQLException;
}
