package lk.grb.ceylonPottersPaletteLayered.util;

import lk.grb.ceylonPottersPaletteLayered.dao.SuperDAO;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudUtil<T> extends SuperDAO {
    boolean save(T dto) throws SQLException;

    T getData(String id) throws SQLException;

    boolean update(T dto) throws SQLException;

    boolean delete(String id) throws SQLException;

    ArrayList<String> getAllId() throws SQLException;
}
