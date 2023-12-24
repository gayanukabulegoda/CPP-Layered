package lk.grb.ceylonPottersPaletteLayered.dao.custom;

import lk.grb.ceylonPottersPaletteLayered.dto.UserDto;
import lk.grb.ceylonPottersPaletteLayered.entity.User;
import lk.grb.ceylonPottersPaletteLayered.util.CrudUtil;
import lk.grb.ceylonPottersPaletteLayered.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserDAO extends CrudUtil<User> {
    String checkUsernameAndPassword(String userName, String password) throws SQLException, ClassNotFoundException;

    String getRole(String userName) throws SQLException;

    String getEmployeeId(String userName) throws SQLException;

    String getUserName(String employeeId) throws SQLException;
}
