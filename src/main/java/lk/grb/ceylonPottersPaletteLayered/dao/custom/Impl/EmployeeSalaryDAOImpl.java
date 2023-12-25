package lk.grb.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.dao.custom.EmployeeSalaryDAO;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeSalaryDto;
import lk.grb.ceylonPottersPaletteLayered.entity.Salary;
import lk.grb.ceylonPottersPaletteLayered.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeSalaryDAOImpl implements EmployeeSalaryDAO {
    @Override
    public boolean save(Salary entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO salary VALUES (?,?,?,?,?,?,?,?)",
                entity.getSalary_Id(),
                entity.getEmployee_Id(),
                entity.getWorked_Day_Count(),
                entity.getSalary(),
                entity.getBonus(),
                entity.getTotal_Payment(),
                entity.getDate(),
                entity.getTime());
    }

    @Override
    public boolean update(Salary entity) throws SQLException {

        return SQLUtil.execute("UPDATE salary SET " +
                        "employee_Id=?," +
                        "worked_Day_Count=?," +
                        "salary=?," +
                        "bonus=?," +
                        "total_Payment=?" +
                        "WHERE salary_Id=?",
                entity.getEmployee_Id(),
                entity.getWorked_Day_Count(),
                entity.getSalary(),
                entity.getBonus(),
                entity.getTotal_Payment(),
                entity.getSalary_Id()
        );
    }

    @Override
    public Salary getData(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM salary WHERE salary_Id=?", id);

        Salary entity = new Salary();

        if(resultSet.next()){
            entity.setSalary_Id(resultSet.getString(1));
            entity.setEmployee_Id(resultSet.getString(2));
            entity.setWorked_Day_Count(resultSet.getInt(3));
            entity.setSalary(resultSet.getDouble(4));
            entity.setBonus(resultSet.getDouble(5));
            entity.setTotal_Payment(resultSet.getDouble(6));
            entity.setDate(resultSet.getString(7));
            entity.setTime(resultSet.getString(8));
        }
        return entity;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT salary_Id FROM salary ORDER BY salary_Id desc");
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public ArrayList<String> getSelectedAllSalaryId(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT salary_Id FROM salary WHERE employee_Id = ? ORDER BY salary_Id desc", id);
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public ArrayList<String> getAllEmployeeId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT employee_Id FROM salary");
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public double getSalaryTotal() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT SUM(total_Payment) FROM salary");

        if (resultSet.next()) {
            return resultSet.getDouble(1);
        }
        return 0.0;
    }

    @Override
    public String getSalaryId(String employeeId) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT salary_Id FROM salary WHERE employee_Id=?", employeeId);

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }
}
