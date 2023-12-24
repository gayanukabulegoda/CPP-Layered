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
    ArrayList<String> getSelectedAllAttendanceId(String id) throws SQLException;

    String workedDayCount(String id) throws SQLException;

    String getTodayAttendance() throws SQLException;
}
