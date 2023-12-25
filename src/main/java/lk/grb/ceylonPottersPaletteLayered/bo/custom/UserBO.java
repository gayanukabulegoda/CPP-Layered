package lk.grb.ceylonPottersPaletteLayered.bo.custom;

import lk.grb.ceylonPottersPaletteLayered.bo.SuperBO;
import lk.grb.ceylonPottersPaletteLayered.dto.UserDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBO {
    boolean saveUser(UserDto dto) throws SQLException;

    UserDto getUserData(String id) throws SQLException;

    boolean updateUser(UserDto dto) throws SQLException;

    boolean deleteUser(String id) throws SQLException;

    ArrayList<String> getAllUserId() throws SQLException;

    String checkUsernameAndPassword(String userName, String password) throws SQLException, ClassNotFoundException;

    String getRole(String userName) throws SQLException;

    String getEmployeeId(String userName) throws SQLException;

    String getUserName(String employeeId) throws SQLException;
}
