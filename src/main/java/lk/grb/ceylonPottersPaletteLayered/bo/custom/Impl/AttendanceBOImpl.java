package lk.grb.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.bo.custom.AttendanceBO;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeAttendanceDto;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceBOImpl implements AttendanceBO {

    @Override
    public EmployeeAttendanceDto getAttendanceData(String id) throws SQLException {
        return null;
    }

    @Override
    public EmployeeDto getEmployeeData(String id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> getAllEmployeeId() throws SQLException {
        return null;
    }

    @Override
    public String getEmployeeContactNo(String id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> getSelectedAllAttendanceId(String id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> getAllAttendanceId() throws SQLException {
        return null;
    }

    @Override
    public boolean saveAttendance(EmployeeAttendanceDto dto) throws SQLException {
        return false;
    }

    @Override
    public String getEmployeeName(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean updateAttendance(EmployeeAttendanceDto dto) throws SQLException {
        return false;
    }
}
