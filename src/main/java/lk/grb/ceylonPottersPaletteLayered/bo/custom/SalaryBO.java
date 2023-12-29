package lk.grb.ceylonPottersPaletteLayered.bo.custom;

import lk.grb.ceylonPottersPaletteLayered.bo.SuperBO;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeDto;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeSalaryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalaryBO extends SuperBO {
    EmployeeSalaryDto getSalaryData(String id) throws SQLException;

    EmployeeDto getEmployeeData(String id) throws SQLException;

    ArrayList<String> getAllEmployeeId() throws SQLException;

    String getEmployeeContactNo(String id) throws SQLException;

    ArrayList<String> getAllSalaryId() throws SQLException;

    ArrayList<String> getSelectedAllSalaryId(String id) throws SQLException;

    boolean saveSalary(EmployeeSalaryDto dto) throws SQLException;

    String getEmployeeName(String id) throws SQLException;

    String workedDayCount(String id) throws SQLException;

    boolean updateSalary(EmployeeSalaryDto dto) throws SQLException;

    String setEmployeeName(String firstName, String lastName);

    double getTotalSalaryAmount(String salary, String bonus);
}
