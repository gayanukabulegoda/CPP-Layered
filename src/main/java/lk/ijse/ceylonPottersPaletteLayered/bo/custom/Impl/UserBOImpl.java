package lk.ijse.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.ijse.ceylonPottersPaletteLayered.bo.custom.UserBO;
import lk.ijse.ceylonPottersPaletteLayered.dao.DAOFactory;
import lk.ijse.ceylonPottersPaletteLayered.dao.custom.EmployeeDAO;
import lk.ijse.ceylonPottersPaletteLayered.dao.custom.UserDAO;
import lk.ijse.ceylonPottersPaletteLayered.dto.EmployeeDto;
import lk.ijse.ceylonPottersPaletteLayered.dto.UserDto;
import lk.ijse.ceylonPottersPaletteLayered.entity.Employee;
import lk.ijse.ceylonPottersPaletteLayered.entity.User;

import java.sql.SQLException;

public class UserBOImpl implements UserBO {

    UserDAO userDAO =
            (UserDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.USER);

    EmployeeDAO employeeDAO =
            (EmployeeDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public boolean updateUser(UserDto dto) throws SQLException {
        return userDAO.update(
                new User(
                        dto.getUser_Name(),
                        dto.getPassword(),
                        dto.getEmployeeId()
                )
        );
    }

    @Override
    public boolean deleteUser(String id) throws SQLException {
        return userDAO.delete(id);
    }

    @Override
    public EmployeeDto getEmployeeData(String id) throws SQLException {
        Employee employee = employeeDAO.getData(id);
        return new EmployeeDto(
                employee.getEmployee_Id(),
                employee.getFirst_Name(),
                employee.getLast_Name(),
                employee.getNic(),
                employee.getHouse_No(),
                employee.getStreet(),
                employee.getCity(),
                employee.getContact_No(),
                employee.getEmail(),
                employee.getRole(),
                employee.getDate(),
                employee.getTime(),
                employee.getUserName()
        );
    }

    @Override
    public String getEmployeeId(String userName) throws SQLException {
        return userDAO.getEmployeeId(userName);
    }

    @Override
    public String getEmployeeName(String id) throws SQLException {
        return employeeDAO.getEmployeeName(id);
    }

    @Override
    public String getEmployeeRole(String id) throws SQLException {
        return employeeDAO.getEmployeeRole(id);
    }

    @Override
    public String getUserName(String employeeId) throws SQLException {
        return userDAO.getUserName(employeeId);
    }

    @Override
    public String getUserRole(String userName) throws SQLException {
        return userDAO.getRole(userName);
    }

    @Override
    public String checkUsernameAndPassword(String userName, String password) throws SQLException, ClassNotFoundException {
        return userDAO.checkUsernameAndPassword(userName, password);
    }

    @Override
    public boolean saveUser(UserDto dto) throws SQLException {
        return userDAO.save(
                new User(
                        dto.getUser_Name(),
                        dto.getPassword(),
                        dto.getEmployeeId()
                )
        );
    }
}
