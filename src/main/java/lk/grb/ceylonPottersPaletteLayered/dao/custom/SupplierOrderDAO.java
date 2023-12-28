package lk.grb.ceylonPottersPaletteLayered.dao.custom;

import lk.grb.ceylonPottersPaletteLayered.entity.SupplierOrder;
import lk.grb.ceylonPottersPaletteLayered.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierOrderDAO extends CrudUtil<SupplierOrder> {
    ArrayList<String> getSelectedAllSupplierOrderId(String id) throws SQLException;

    ArrayList<String> getSupplierId(String id) throws SQLException;

    String getSupplierIdForOrder(String id) throws SQLException;

    double getOrderTotal() throws SQLException;
}
