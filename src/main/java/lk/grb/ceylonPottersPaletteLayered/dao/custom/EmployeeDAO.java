package lk.grb.ceylonPottersPaletteLayered.dao.custom;

import lk.grb.ceylonPottersPaletteLayered.db.DbConnection;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeDto;
import lk.grb.ceylonPottersPaletteLayered.entity.Employee;
import lk.grb.ceylonPottersPaletteLayered.util.CrudUtil;
import lk.grb.ceylonPottersPaletteLayered.util.SQLUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO extends CrudUtil<Employee> {
    boolean save(EmployeeDto employeeDTO) throws SQLException;

    EmployeeDto getData(String id) throws SQLException;

    boolean update(EmployeeDto employeeDTO) throws SQLException;

    boolean delete(String id) throws SQLException;

    ArrayList<String> getAllEmployeeId() throws SQLException;

    String getEmployeeName(String id) throws SQLException;

    String getEmployeeRole(String id) throws SQLException;

    String getEmployeeContactNo(String id) throws SQLException;
}
