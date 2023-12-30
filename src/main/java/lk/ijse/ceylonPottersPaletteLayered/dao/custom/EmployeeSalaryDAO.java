package lk.ijse.ceylonPottersPaletteLayered.dao.custom;

import lk.ijse.ceylonPottersPaletteLayered.entity.Salary;
import lk.ijse.ceylonPottersPaletteLayered.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeSalaryDAO extends CrudUtil<Salary> {
    ArrayList<String> getSelectedAllSalaryId(String id) throws SQLException;

    ArrayList<String> getAllEmployeeId() throws SQLException;

    double getSalaryTotal() throws SQLException;

    String getSalaryId(String employeeId) throws SQLException;
}
