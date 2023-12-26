package lk.grb.ceylonPottersPaletteLayered.bo.custom;

import lk.grb.ceylonPottersPaletteLayered.bo.SuperBO;
import lk.grb.ceylonPottersPaletteLayered.dto.ProductStockDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductStockBO extends SuperBO {
    ArrayList<String> getAllProductId() throws SQLException;

    boolean saveProduct(ProductStockDto dto) throws SQLException;

    ProductStockDto getProductData(String id) throws SQLException;

    String getProductDescription(String id) throws SQLException;

    boolean updateFromPopUp(ProductStockDto dto) throws SQLException;
}
