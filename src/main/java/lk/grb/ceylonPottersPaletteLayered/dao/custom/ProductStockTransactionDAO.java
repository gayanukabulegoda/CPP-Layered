package lk.grb.ceylonPottersPaletteLayered.dao.custom;

import lk.grb.ceylonPottersPaletteLayered.dao.SuperDAO;
import lk.grb.ceylonPottersPaletteLayered.dto.ProductStockDto;
import lk.grb.ceylonPottersPaletteLayered.entity.ProductStock;

import java.sql.SQLException;

public interface ProductStockTransactionDAO extends SuperDAO {
    boolean saveProduct(ProductStock entity) throws SQLException;
}
