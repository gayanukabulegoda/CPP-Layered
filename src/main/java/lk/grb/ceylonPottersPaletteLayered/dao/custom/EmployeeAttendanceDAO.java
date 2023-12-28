package lk.grb.ceylonPottersPaletteLayered.dao.custom;

import lk.grb.ceylonPottersPaletteLayered.entity.Attendance;
import lk.grb.ceylonPottersPaletteLayered.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeAttendanceDAO extends CrudUtil<Attendance> {
    ArrayList<String> getSelectedAllAttendanceId(String id) throws SQLException;

    String workedDayCount(String id) throws SQLException;

    String getTodayAttendance() throws SQLException;
}
