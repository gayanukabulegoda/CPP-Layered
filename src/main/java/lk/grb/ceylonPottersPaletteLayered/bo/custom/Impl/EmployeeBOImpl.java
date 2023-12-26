package lk.grb.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.bo.custom.EmployeeBO;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    @Override
    public ArrayList<String> getAllEmployeeId() throws SQLException {
        return null;
    }

    @Override
    public boolean saveEmployee(EmployeeDto dto) throws SQLException {
        return false;
    }

    @Override
    public EmployeeDto getEmployeeData(String id) throws SQLException {
        return null;
    }

    @Override
    public String getEmployeeContactNo(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean updateEmployee(EmployeeDto dto) throws SQLException {
        return false;
    }
}
