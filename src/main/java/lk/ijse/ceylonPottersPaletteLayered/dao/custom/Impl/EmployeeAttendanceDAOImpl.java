package lk.ijse.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.ijse.ceylonPottersPaletteLayered.dao.custom.EmployeeAttendanceDAO;
import lk.ijse.ceylonPottersPaletteLayered.util.SQLUtil;
import lk.ijse.ceylonPottersPaletteLayered.entity.Attendance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeAttendanceDAOImpl implements EmployeeAttendanceDAO {
    @Override
    public boolean save(Attendance entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO attendance VALUES (?,?,?,?)",
                entity.getAttendance_Id(),
                entity.getEmployee_Id(),
                entity.getDate(),
                entity.getTime());
    }

    @Override
    public boolean update(Attendance entity) throws SQLException {
        return SQLUtil.execute("UPDATE attendance SET " +
                        "employee_Id=?" +
                        "WHERE attendance_Id=?",
                entity.getEmployee_Id(),
                entity.getAttendance_Id());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM attendance WHERE attendance_Id=?", id);
    }

    @Override
    public Attendance getData(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM attendance WHERE attendance_Id=?", id);

        Attendance entity = new Attendance();

        if(resultSet.next()){
            entity.setEmployee_Id(resultSet.getString(2));
            entity.setDate(resultSet.getString(3));
            entity.setTime(resultSet.getString(4));
        }
        return entity;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException {
        ResultSet resultSet = SQLUtil.
                execute("SELECT attendance_Id FROM attendance ORDER BY date desc, time desc");

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public ArrayList<String> getSelectedAllAttendanceId(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.
                execute("SELECT attendance_Id FROM attendance WHERE employee_Id = ? " +
                        "ORDER BY date desc, time desc", id);

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public ArrayList<String> getAllTodayEmployeeId() throws SQLException {
        ResultSet resultSet = SQLUtil.
                execute("SELECT employee_Id FROM attendance WHERE date = CURDATE()");

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public String workedDayCount(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.
                execute("SELECT COUNT(*) AS work_days FROM attendance " +
                        "WHERE employee_Id = ? AND MONTH(date) = MONTH(CURDATE())", id);

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public String getTodayAttendance() throws SQLException {
        ResultSet resultSet = SQLUtil.
                execute("SELECT COUNT(*) FROM attendance WHERE date = CURDATE()");

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}
