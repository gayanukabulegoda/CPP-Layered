package lk.grb.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.bo.custom.ProductStockBO;
import lk.grb.ceylonPottersPaletteLayered.dto.ProductStockDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductStockBOImpl implements ProductStockBO {
    @Override
    public ArrayList<String> getAllProductId() throws SQLException {
        return null;
    }

    @Override
    public boolean saveProduct(ProductStockDto dto) throws SQLException {
        return false;
    }

    @Override
    public ProductStockDto getProductData(String id) throws SQLException {
        return null;
    }

    @Override
    public String getProductDescription(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean updateFromPopUp(ProductStockDto dto) throws SQLException {
        return false;
    }
}
