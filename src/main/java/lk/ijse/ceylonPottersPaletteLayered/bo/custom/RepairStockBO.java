package lk.ijse.ceylonPottersPaletteLayered.bo.custom;

import lk.ijse.ceylonPottersPaletteLayered.bo.SuperBO;
import lk.ijse.ceylonPottersPaletteLayered.dto.ProductStockDto;
import lk.ijse.ceylonPottersPaletteLayered.dto.RepairStockDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RepairStockBO extends SuperBO {
    RepairStockDto getRepairStockData(String id) throws SQLException;

    ProductStockDto getProductStockData(String id) throws SQLException;

    boolean removeRepairStock(String product_Id, String qty) throws SQLException;

    boolean updateRepairStock(String product_Id, String qty) throws SQLException;

    ArrayList<String> getAllProductId() throws SQLException;

    String getProductDescription(String id) throws SQLException;
}
