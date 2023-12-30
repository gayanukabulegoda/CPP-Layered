package lk.ijse.ceylonPottersPaletteLayered.bo.custom;

import lk.ijse.ceylonPottersPaletteLayered.bo.SuperBO;
import lk.ijse.ceylonPottersPaletteLayered.dto.EmployeeAttendanceDto;
import lk.ijse.ceylonPottersPaletteLayered.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendanceBO extends SuperBO {
    EmployeeAttendanceDto getAttendanceData(String id) throws SQLException;

    EmployeeDto getEmployeeData(String id) throws SQLException;

    ArrayList<String> getAllEmployeeId() throws SQLException;

    String getEmployeeContactNo(String id) throws SQLException;

    ArrayList<String> getSelectedAllAttendanceId(String id) throws SQLException;

    ArrayList<String> getAllAttendanceId() throws SQLException;

    boolean saveAttendance(EmployeeAttendanceDto dto) throws SQLException;

    String getEmployeeName(String id) throws SQLException;

    boolean updateAttendance(EmployeeAttendanceDto dto) throws SQLException;

    String setEmployeeName(String firstName, String lastName);
}
