package lk.grb.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.dao.custom.EmployeeDAO;
import lk.grb.ceylonPottersPaletteLayered.db.DbConnection;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeDto;
import lk.grb.ceylonPottersPaletteLayered.util.SQLUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean save(EmployeeDto employeeDTO) throws SQLException {
        return SQLUtil.execute("INSERT INTO employee VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)",
                employeeDTO.getEmployee_Id(),
                employeeDTO.getFirst_Name(),
                employeeDTO.getLast_Name(),
                employeeDTO.getNic(),
                employeeDTO.getHouse_No(),
                employeeDTO.getStreet(),
                employeeDTO.getCity(),
                employeeDTO.getContact_No(),
                employeeDTO.getEmail(),
                employeeDTO.getRole(),
                employeeDTO.getDate(),
                employeeDTO.getTime(),
                employeeDTO.getUserName());
    }

    @Override
    public EmployeeDto getData(String id) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT * FROM employee WHERE employee_Id=?", id);

        EmployeeDto employeeDTO = new EmployeeDto();

        if (set.next()) {
            employeeDTO.setEmployee_Id(set.getString(1));
            employeeDTO.setFirst_Name(set.getString(2));
            employeeDTO.setLast_Name(set.getString(3));
            employeeDTO.setNic(set.getString(4));
            employeeDTO.setHouse_No(set.getString(5));
            employeeDTO.setStreet(set.getString(6));
            employeeDTO.setCity(set.getString(7));
            employeeDTO.setContact_No(set.getString(8));
            employeeDTO.setEmail(set.getString(9));
            employeeDTO.setRole(set.getString(10));
            employeeDTO.setDate(set.getString(11));
            employeeDTO.setTime(set.getString(12));
            employeeDTO.setUserName(set.getString(13));
        }
        return employeeDTO;
    }

    @Override
    public boolean update(EmployeeDto employeeDTO) throws SQLException {
        return SQLUtil.execute("UPDATE employee SET " +
                        "first_Name=?," +
                        "last_Name=?," +
                        "nic=?," +
                        "house_No=? ," +
                        "street=? ," +
                        "city=?," +
                        "contact_No=?," +
                        "email=?," +
                        "role=?" +
                        "WHERE employee_Id=?",
                employeeDTO.getFirst_Name(),
                employeeDTO.getLast_Name(),
                employeeDTO.getNic(),
                employeeDTO.getHouse_No(),
                employeeDTO.getStreet(),
                employeeDTO.getCity(),
                employeeDTO.getContact_No(),
                employeeDTO.getEmail(),
                employeeDTO.getRole(),
                employeeDTO.getEmployee_Id()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM employee WHERE employee_Id=?", id);
    }

    @Override
    public ArrayList<String> getAllEmployeeId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT employee_Id FROM employee ORDER BY LENGTH(employee_Id),employee_Id");
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public String getEmployeeName(String id) throws SQLException {

        String sql = ("SELECT first_Name FROM employee WHERE employee_Id=?");
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1,id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public String getEmployeeRole(String id) throws SQLException {

        String sql = ("SELECT role FROM employee WHERE employee_Id=?");
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1,id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public String getEmployeeContactNo(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT contact_No FROM employee WHERE employee_Id=?", id);

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}
