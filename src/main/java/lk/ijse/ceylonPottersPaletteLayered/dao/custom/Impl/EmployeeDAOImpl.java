package lk.ijse.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.ijse.ceylonPottersPaletteLayered.dao.custom.EmployeeDAO;
import lk.ijse.ceylonPottersPaletteLayered.util.SQLUtil;
import lk.ijse.ceylonPottersPaletteLayered.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean save(Employee entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO employee VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)",
                entity.getEmployee_Id(),
                entity.getFirst_Name(),
                entity.getLast_Name(),
                entity.getNic(),
                entity.getHouse_No(),
                entity.getStreet(),
                entity.getCity(),
                entity.getContact_No(),
                entity.getEmail(),
                entity.getRole(),
                entity.getDate(),
                entity.getTime(),
                entity.getUserName());
    }

    @Override
    public Employee getData(String id) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT * FROM employee WHERE employee_Id=?", id);

        Employee entity = new Employee();

        if (set.next()) {
            entity.setEmployee_Id(set.getString(1));
            entity.setFirst_Name(set.getString(2));
            entity.setLast_Name(set.getString(3));
            entity.setNic(set.getString(4));
            entity.setHouse_No(set.getString(5));
            entity.setStreet(set.getString(6));
            entity.setCity(set.getString(7));
            entity.setContact_No(set.getString(8));
            entity.setEmail(set.getString(9));
            entity.setRole(set.getString(10));
            entity.setDate(set.getString(11));
            entity.setTime(set.getString(12));
            entity.setUserName(set.getString(13));
        }
        return entity;
    }

    @Override
    public boolean update(Employee entity) throws SQLException {
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
                entity.getFirst_Name(),
                entity.getLast_Name(),
                entity.getNic(),
                entity.getHouse_No(),
                entity.getStreet(),
                entity.getCity(),
                entity.getContact_No(),
                entity.getEmail(),
                entity.getRole(),
                entity.getEmployee_Id());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM employee WHERE employee_Id=?", id);
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException {
        ResultSet resultSet = SQLUtil.
                execute("SELECT employee_Id FROM employee ORDER BY LENGTH(employee_Id),employee_Id");

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public String getEmployeeName(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.
                execute("SELECT first_Name FROM employee WHERE employee_Id=?", id);

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public String getEmployeeRole(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.
                execute("SELECT role FROM employee WHERE employee_Id=?", id);

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public String getEmployeeContactNo(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.
                execute("SELECT contact_No FROM employee WHERE employee_Id=?", id);

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllContactNumbers() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT contact_No FROM employee");

        ArrayList<String> contactNumbers = new ArrayList<>();

        while (resultSet.next()) {
            contactNumbers.add(resultSet.getString(1));
        }
        return contactNumbers;
    }

    @Override
    public ArrayList<String> getAllEmails() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT email FROM employee");

        ArrayList<String> emails = new ArrayList<>();

        while (resultSet.next()) {
            emails.add(resultSet.getString(1));
        }
        return emails;
    }

    @Override
    public ArrayList<String> getAllNic() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT nic FROM employee");

        ArrayList<String> emails = new ArrayList<>();

        while (resultSet.next()) {
            emails.add(resultSet.getString(1));
        }
        return emails;
    }
}
