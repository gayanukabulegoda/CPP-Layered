package lk.grb.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.bo.custom.EmployeeBO;
import lk.grb.ceylonPottersPaletteLayered.dao.DAOFactory;
import lk.grb.ceylonPottersPaletteLayered.dao.custom.EmployeeDAO;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeDto;
import lk.grb.ceylonPottersPaletteLayered.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO =
            (EmployeeDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public ArrayList<String> getAllEmployeeId() throws SQLException {
        return employeeDAO.getAllId();
    }

    @Override
    public boolean saveEmployee(EmployeeDto dto) throws SQLException {
        return employeeDAO.save(
                new Employee(
                        dto.getEmployee_Id(),
                        dto.getFirst_Name(),
                        dto.getLast_Name(),
                        dto.getNic(),
                        dto.getHouse_No(),
                        dto.getStreet(),
                        dto.getCity(),
                        dto.getContact_No(),
                        dto.getEmail(),
                        dto.getRole(),
                        dto.getDate(),
                        dto.getTime(),
                        dto.getUserName()
                )
        );
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
    public String getEmployeeContactNo(String id) throws SQLException {
        return employeeDAO.getEmployeeContactNo(id);
    }

    @Override
    public boolean updateEmployee(EmployeeDto dto) throws SQLException {
        return employeeDAO.update(
                new Employee(
                        dto.getEmployee_Id(),
                        dto.getFirst_Name(),
                        dto.getLast_Name(),
                        dto.getNic(),
                        dto.getHouse_No(),
                        dto.getStreet(),
                        dto.getCity(),
                        dto.getContact_No(),
                        dto.getEmail(),
                        dto.getRole(),
                        dto.getDate(),
                        dto.getTime(),
                        dto.getUserName()
                )
        );
    }

    @Override
    public String setEmployeeName(String firstName, String lastName) {
        return firstName + " " + lastName;
    }
}
