package lk.grb.ceylonPottersPaletteLayered.dao.custom;

import lk.grb.ceylonPottersPaletteLayered.dao.SuperDAO;
import lk.grb.ceylonPottersPaletteLayered.entity.CustomerOrder;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerOrderDetailDAO extends SuperDAO {
    boolean save(CustomerOrder entity) throws SQLException;

    ArrayList<String[]> getData(String id) throws SQLException;
}
