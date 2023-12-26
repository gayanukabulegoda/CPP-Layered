package lk.grb.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.bo.custom.UserBO;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeDto;
import lk.grb.ceylonPottersPaletteLayered.dto.UserDto;

import java.sql.SQLException;

public class UserBOImpl implements UserBO {
    @Override
    public boolean updateUser(UserDto dto) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteUser(String id) throws SQLException {
        return false;
    }

    @Override
    public EmployeeDto getEmployeeData(String id) throws SQLException {
        return null;
    }

    @Override
    public String getEmployeeId(String userName) throws SQLException {
        return null;
    }

    @Override
    public String getEmployeeName(String id) throws SQLException {
        return null;
    }

    @Override
    public String getEmployeeRole(String id) throws SQLException {
        return null;
    }

    @Override
    public String getUserName(String employeeId) throws SQLException {
        return null;
    }

    @Override
    public String getUserRole(String userName) throws SQLException {
        return null;
    }

    @Override
    public String checkUsernameAndPassword(String userName, String password) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveUser(UserDto dto) throws SQLException {
        return false;
    }
}
