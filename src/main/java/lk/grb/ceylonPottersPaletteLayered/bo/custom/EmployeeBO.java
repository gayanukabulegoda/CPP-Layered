package lk.grb.ceylonPottersPaletteLayered.bo.custom;

import lk.grb.ceylonPottersPaletteLayered.bo.SuperBO;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    ArrayList<String> getAllEmployeeId() throws SQLException;

    boolean saveEmployee(EmployeeDto dto) throws SQLException;

    EmployeeDto getEmployeeData(String id) throws SQLException;

    String getEmployeeContactNo(String id) throws SQLException;

    boolean updateEmployee(EmployeeDto dto) throws SQLException;

    String setEmployeeName(String firstName, String lastName);
}
