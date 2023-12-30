package lk.ijse.ceylonPottersPaletteLayered.dao.custom;

import lk.ijse.ceylonPottersPaletteLayered.entity.Employee;
import lk.ijse.ceylonPottersPaletteLayered.util.CrudUtil;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudUtil<Employee> {
    String getEmployeeName(String id) throws SQLException;

    String getEmployeeRole(String id) throws SQLException;

    String getEmployeeContactNo(String id) throws SQLException;
}
