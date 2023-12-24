package lk.grb.ceylonPottersPaletteLayered.dao.custom;

import lk.grb.ceylonPottersPaletteLayered.db.DbConnection;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeAttendanceDto;
import lk.grb.ceylonPottersPaletteLayered.entity.Attendance;
import lk.grb.ceylonPottersPaletteLayered.util.CrudUtil;
import lk.grb.ceylonPottersPaletteLayered.util.SQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeAttendanceDAO extends CrudUtil<Attendance> {
    boolean save(EmployeeAttendanceDto employeeAttendanceDto) throws SQLException;

    boolean update(EmployeeAttendanceDto employeeAttendanceDto) throws SQLException;

    boolean delete(String id) throws SQLException;

    EmployeeAttendanceDto getData(String id) throws SQLException;

    ArrayList<String> getAllAttendanceId() throws SQLException;

    ArrayList<String> getSelectedAllAttendanceId(String id) throws SQLException;

    String workedDayCount(String id) throws SQLException;

    String getTodayAttendance() throws SQLException;
}
