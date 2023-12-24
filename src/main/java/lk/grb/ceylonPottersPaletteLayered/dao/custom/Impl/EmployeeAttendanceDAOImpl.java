package lk.grb.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.dao.custom.EmployeeAttendanceDAO;
import lk.grb.ceylonPottersPaletteLayered.db.DbConnection;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeAttendanceDto;
import lk.grb.ceylonPottersPaletteLayered.util.SQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeAttendanceDAOImpl implements EmployeeAttendanceDAO {
    @Override
    public boolean save(EmployeeAttendanceDto employeeAttendanceDto) throws SQLException {
        return SQLUtil.execute("INSERT INTO attendance VALUES (?,?,?,?)",
                employeeAttendanceDto.getAttendance_Id(),
                employeeAttendanceDto.getEmployee_Id(),
                employeeAttendanceDto.getDate(),
                employeeAttendanceDto.getTime());
    }

    @Override
    public boolean update(EmployeeAttendanceDto employeeAttendanceDto) throws SQLException {

        return SQLUtil.execute("UPDATE attendance SET " +
                        "employee_Id=?" +
                        "WHERE attendance_Id=?",
                employeeAttendanceDto.getEmployee_Id(),
                employeeAttendanceDto.getAttendance_Id()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM attendance WHERE attendance_Id=?", id);
    }

    @Override
    public EmployeeAttendanceDto getData(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM attendance WHERE attendance_Id=?", id);

        EmployeeAttendanceDto employeeAttendanceDto = new EmployeeAttendanceDto();

        if(resultSet.next()){
            employeeAttendanceDto.setEmployee_Id(resultSet.getString(2));
            employeeAttendanceDto.setDate(resultSet.getString(3));
            employeeAttendanceDto.setTime(resultSet.getString(4));
        }
        return employeeAttendanceDto;
    }

    @Override
    public ArrayList<String> getAllAttendanceId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT attendance_Id FROM attendance ORDER BY date desc, time desc");
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public ArrayList<String> getSelectedAllAttendanceId(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT attendance_Id FROM attendance WHERE employee_Id = ? ORDER BY date desc, time desc", id);
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public String workedDayCount(String id) throws SQLException {
        String sql = "SELECT COUNT(*) AS work_days FROM attendance WHERE employee_Id = ? AND MONTH(date) = MONTH(CURDATE())";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,id);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public String getTodayAttendance() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) " +
                "FROM attendance " +
                "WHERE date = CURDATE()");

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}
