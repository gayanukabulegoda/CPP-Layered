package lk.grb.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.dao.custom.UserDAO;
import lk.grb.ceylonPottersPaletteLayered.dto.UserDto;
import lk.grb.ceylonPottersPaletteLayered.entity.User;
import lk.grb.ceylonPottersPaletteLayered.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public String checkUsernameAndPassword(String userName, String password) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtil.execute("SELECT user_Name FROM user WHERE user_Name=? AND password=?", userName, password);

        if (set.next()) {
            return set.getString(1);
        } else {
            return "No";
        }
    }

    @Override
    public String getRole(String userName) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT e.role " +
                "FROM employee e " +
                "JOIN user u ON e.employee_Id = u.employee_Id " +
                "WHERE u.user_Name = ?;", userName);

        if (set.next()) {
            return set.getString(1);
        } else {
            return "No";
        }
    }

    @Override
    public boolean update(User entity) throws SQLException {
        return SQLUtil.execute("UPDATE user SET " +
                        "password=? " +
                        "WHERE user_Name=?",
                entity.getPassword(),
                entity.getUser_Name()
        );
    }

    @Override
    public boolean save(User entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO user VALUES (?,?,?)",
                entity.getUser_Name(),
                entity.getPassword(),
                entity.getEmployeeId());
    }

    @Override
    public boolean delete(String userName) throws SQLException {
        return SQLUtil.execute("DELETE FROM user WHERE user_Name=?", userName);
    }

    @Override
    public String getEmployeeId(String userName) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT employee_Id FROM user WHERE user_Name=?", userName);

        if (set.next()) {
            return set.getString(1);
        } else {
            return "null";
        }
    }

    @Override
    public String getUserName(String employeeId) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT user_Name FROM user WHERE employee_Id=?", employeeId);

        if (set.next()) {
            return set.getString(1);
        } else {
            return "null";
        }
    }

    @Override
    public User getData(String id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException {
        return null;
    }
}
