package lk.grb.ceylonPottersPaletteLayered.dao.custom;

import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeSalaryDto;
import lk.grb.ceylonPottersPaletteLayered.entity.Salary;
import lk.grb.ceylonPottersPaletteLayered.util.CrudUtil;
import lk.grb.ceylonPottersPaletteLayered.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeSalaryDAO extends CrudUtil<Salary> {
    boolean save(EmployeeSalaryDto employeeSalaryDto) throws SQLException;

    boolean update(EmployeeSalaryDto employeeSalaryDto) throws SQLException;

    EmployeeSalaryDto getData(String id) throws SQLException;

    ArrayList<String> getAllSalaryId() throws SQLException;

    ArrayList<String> getSelectedAllSalaryId(String id) throws SQLException;

    ArrayList<String> getAllEmployeeId() throws SQLException;

    double getSalaryTotal() throws SQLException;

    String getSalaryId(String employeeId) throws SQLException;
}
