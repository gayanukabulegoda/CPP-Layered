package lk.ijse.ceylonPottersPaletteLayered.dao.custom;

import lk.ijse.ceylonPottersPaletteLayered.dao.SuperDAO;
import lk.ijse.ceylonPottersPaletteLayered.entity.SupplierOrder;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierOrderDetailDAO extends SuperDAO {
    boolean save(SupplierOrder entity) throws SQLException;

    SupplierOrder getData(String id) throws SQLException;

    ArrayList<String[]> getDataAsAnArray(String id) throws SQLException;
}
