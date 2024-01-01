package lk.ijse.ceylonPottersPaletteLayered.bo.custom;

import lk.ijse.ceylonPottersPaletteLayered.bo.SuperBO;
import lk.ijse.ceylonPottersPaletteLayered.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    ArrayList<String> getAllEmployeeId() throws SQLException;

    boolean saveEmployee(EmployeeDto dto) throws SQLException;

    EmployeeDto getEmployeeData(String id) throws SQLException;

    String getEmployeeContactNo(String id) throws SQLException;

    boolean updateEmployee(EmployeeDto dto) throws SQLException;

    String setEmployeeName(String firstName, String lastName);

    ArrayList<String> getAllEmployeeContactNumbers() throws SQLException;

    ArrayList<String> getAllEmployeeEmails() throws SQLException;

    ArrayList<String> getAllEmployeeNic() throws SQLException;
}
