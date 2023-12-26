package lk.grb.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.bo.custom.RepairStockBO;
import lk.grb.ceylonPottersPaletteLayered.dto.ProductStockDto;
import lk.grb.ceylonPottersPaletteLayered.dto.RepairStockDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class RepairStockBOImpl implements RepairStockBO {
    @Override
    public RepairStockDto getRepairStockData(String id) throws SQLException {
        return null;
    }

    @Override
    public ProductStockDto getProductStockData(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean removeRepairStock(String product_Id, String qty) throws SQLException {
        return false;
    }

    @Override
    public boolean updateRepairStock(String product_Id, String qty) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<String> getAllProductId() throws SQLException {
        return null;
    }

    @Override
    public String getProductDescription(String id) throws SQLException {
        return null;
    }
}
