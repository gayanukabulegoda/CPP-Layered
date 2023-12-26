package lk.grb.ceylonPottersPaletteLayered.bo.custom;

import lk.grb.ceylonPottersPaletteLayered.bo.SuperBO;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeDto;
import lk.grb.ceylonPottersPaletteLayered.dto.UserDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBO {
    boolean updateUser(UserDto dto) throws SQLException;

    boolean deleteUser(String id) throws SQLException;

    EmployeeDto getEmployeeData(String id) throws SQLException;

    String getEmployeeId(String userName) throws SQLException;

    String getEmployeeName(String id) throws SQLException;

    String getEmployeeRole(String id) throws SQLException;

    String getUserName(String employeeId) throws SQLException;

    String getUserRole(String userName) throws SQLException;

    String checkUsernameAndPassword(String userName, String password) throws SQLException, ClassNotFoundException;

    boolean saveUser(UserDto dto) throws SQLException;
}
