package lk.grb.ceylonPottersPaletteLayered.bo.custom;

import lk.grb.ceylonPottersPaletteLayered.bo.SuperBO;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeAttendanceDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendanceBO extends SuperBO {
    boolean saveAttendance(EmployeeAttendanceDto dto) throws SQLException;

    EmployeeAttendanceDto getAttendanceData(String id) throws SQLException;

    boolean updateAttendance(EmployeeAttendanceDto dto) throws SQLException;

    boolean deleteAttendance(String id) throws SQLException;

    ArrayList<String> getAllAttendanceId() throws SQLException;

    ArrayList<String> getSelectedAllAttendanceId(String id) throws SQLException;

    String workedDayCount(String id) throws SQLException;

    String getTodayAttendance() throws SQLException;
}
