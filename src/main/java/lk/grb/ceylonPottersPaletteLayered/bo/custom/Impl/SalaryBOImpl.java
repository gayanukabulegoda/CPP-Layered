package lk.grb.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.bo.custom.SalaryBO;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeDto;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeSalaryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryBOImpl implements SalaryBO {
    @Override
    public EmployeeSalaryDto getSalaryData(String id) throws SQLException {
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
    public ArrayList<String> getAllSalaryId() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> getSelectedAllSalaryId(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean saveSalary(EmployeeSalaryDto dto) throws SQLException {
        return false;
    }

    @Override
    public String getEmployeeName(String id) throws SQLException {
        return null;
    }

    @Override
    public String workedDayCount(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean update(EmployeeSalaryDto dto) throws SQLException {
        return false;
    }
}
