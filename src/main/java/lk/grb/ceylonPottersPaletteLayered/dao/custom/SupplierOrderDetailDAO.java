package lk.grb.ceylonPottersPaletteLayered.dao.custom;

import lk.grb.ceylonPottersPaletteLayered.dao.SuperDAO;
import lk.grb.ceylonPottersPaletteLayered.entity.SupplierOrder;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierOrderDetailDAO extends SuperDAO {
    boolean save(SupplierOrder entity) throws SQLException;

    SupplierOrder getData(String id) throws SQLException;

    ArrayList<String[]> getDataAsAnArray(String id) throws SQLException;
}
