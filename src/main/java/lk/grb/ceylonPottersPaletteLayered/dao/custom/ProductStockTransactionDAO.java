package lk.grb.ceylonPottersPaletteLayered.dao.custom;

import lk.grb.ceylonPottersPaletteLayered.dao.SuperDAO;
import lk.grb.ceylonPottersPaletteLayered.dto.ProductStockDto;

import java.sql.SQLException;

public interface ProductStockTransactionDAO extends SuperDAO {
    boolean saveProduct(ProductStockDto productStockDto) throws SQLException;
}
