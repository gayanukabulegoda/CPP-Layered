package lk.grb.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.bo.custom.SalaryBO;
import lk.grb.ceylonPottersPaletteLayered.dao.DAOFactory;
import lk.grb.ceylonPottersPaletteLayered.dao.custom.EmployeeAttendanceDAO;
import lk.grb.ceylonPottersPaletteLayered.dao.custom.EmployeeDAO;
import lk.grb.ceylonPottersPaletteLayered.dao.custom.EmployeeSalaryDAO;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeDto;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeSalaryDto;
import lk.grb.ceylonPottersPaletteLayered.entity.Employee;
import lk.grb.ceylonPottersPaletteLayered.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryBOImpl implements SalaryBO {

    EmployeeSalaryDAO employeeSalaryDAO =
            (EmployeeSalaryDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.EMPLOYEE_SALARY);

    EmployeeDAO employeeDAO =
            (EmployeeDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    EmployeeAttendanceDAO employeeAttendanceDAO =
            (EmployeeAttendanceDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.EMPLOYEE_ATTENDANCE);

    @Override
    public EmployeeSalaryDto getSalaryData(String id) throws SQLException {
        Salary salary = employeeSalaryDAO.getData(id);
        return new EmployeeSalaryDto(
                salary.getSalary_Id(),
                salary.getEmployee_Id(),
                salary.getWorked_Day_Count(),
                salary.getSalary(),
                salary.getBonus(),
                salary.getTotal_Payment(),
                salary.getDate(),
                salary.getTime()
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
    public ArrayList<String> getAllEmployeeId() throws SQLException {
        return employeeDAO.getAllId();
    }

    @Override
    public String getEmployeeContactNo(String id) throws SQLException {
        return employeeDAO.getEmployeeContactNo(id);
    }

    @Override
    public ArrayList<String> getAllSalaryId() throws SQLException {
        return employeeSalaryDAO.getAllId();
    }

    @Override
    public ArrayList<String> getSelectedAllSalaryId(String id) throws SQLException {
        return employeeSalaryDAO.getSelectedAllSalaryId(id);
    }

    @Override
    public boolean saveSalary(EmployeeSalaryDto dto) throws SQLException {
        return employeeSalaryDAO.save(
                new Salary(
                        dto.getSalary_Id(),
                        dto.getEmployee_Id(),
                        dto.getWorked_Day_Count(),
                        dto.getSalary(),
                        dto.getBonus(),
                        dto.getTotal_Payment(),
                        dto.getDate(),
                        dto.getTime()
                )
        );
    }

    @Override
    public String getEmployeeName(String id) throws SQLException {
        return employeeDAO.getEmployeeName(id);
    }

    @Override
    public String workedDayCount(String id) throws SQLException {
        return employeeAttendanceDAO.workedDayCount(id);
    }

    @Override
    public boolean updateSalary(EmployeeSalaryDto dto) throws SQLException {
        return employeeSalaryDAO.update(
                new Salary(
                        dto.getSalary_Id(),
                        dto.getEmployee_Id(),
                        dto.getWorked_Day_Count(),
                        dto.getSalary(),
                        dto.getBonus(),
                        dto.getTotal_Payment(),
                        dto.getDate(),
                        dto.getTime()
                )
        );
    }
}
