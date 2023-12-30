package lk.ijse.ceylonPottersPaletteLayered.dao.custom;

import lk.ijse.ceylonPottersPaletteLayered.entity.Supplier;
import lk.ijse.ceylonPottersPaletteLayered.util.CrudUtil;

import java.sql.SQLException;

public interface SupplierDAO extends CrudUtil<Supplier> {
    String getSupplierName(String id) throws SQLException;

    String getSupplierCount() throws SQLException;

    String getSupplierContactNo(String id) throws SQLException;
}
