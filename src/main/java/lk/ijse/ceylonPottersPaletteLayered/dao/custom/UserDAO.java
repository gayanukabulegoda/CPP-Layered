package lk.ijse.ceylonPottersPaletteLayered.dao.custom;

import lk.ijse.ceylonPottersPaletteLayered.entity.User;
import lk.ijse.ceylonPottersPaletteLayered.util.CrudUtil;

import java.sql.SQLException;

public interface UserDAO extends CrudUtil<User> {
    String checkUsernameAndPassword(String userName, String password) throws SQLException;

    String getRole(String userName) throws SQLException;

    String getEmployeeId(String userName) throws SQLException;

    String getUserName(String employeeId) throws SQLException;
}
