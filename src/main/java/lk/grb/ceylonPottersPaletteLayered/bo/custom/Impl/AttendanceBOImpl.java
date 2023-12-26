package lk.grb.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.bo.custom.AttendanceBO;
import lk.grb.ceylonPottersPaletteLayered.dao.DAOFactory;
import lk.grb.ceylonPottersPaletteLayered.dao.custom.EmployeeAttendanceDAO;
import lk.grb.ceylonPottersPaletteLayered.dao.custom.EmployeeDAO;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeAttendanceDto;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeDto;
import lk.grb.ceylonPottersPaletteLayered.entity.Attendance;
import lk.grb.ceylonPottersPaletteLayered.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceBOImpl implements AttendanceBO {

    EmployeeAttendanceDAO employeeAttendanceDAO =
            (EmployeeAttendanceDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.EMPLOYEE_ATTENDANCE);

    EmployeeDAO employeeDAO =
            (EmployeeDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public EmployeeAttendanceDto getAttendanceData(String id) throws SQLException {
        Attendance attendance = employeeAttendanceDAO.getData(id);
        return new EmployeeAttendanceDto(
                attendance.getAttendance_Id(),
                attendance.getEmployee_Id(),
                attendance.getDate(),
                attendance.getTime()
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
    public ArrayList<String> getSelectedAllAttendanceId(String id) throws SQLException {
        return employeeAttendanceDAO.getSelectedAllAttendanceId(id);
    }

    @Override
    public ArrayList<String> getAllAttendanceId() throws SQLException {
        return employeeAttendanceDAO.getAllId();
    }

    @Override
    public boolean saveAttendance(EmployeeAttendanceDto dto) throws SQLException {
        return employeeAttendanceDAO.save(
                new Attendance(
                        dto.getAttendance_Id(),
                        dto.getEmployee_Id(),
                        dto.getDate(),
                        dto.getTime())
        );
    }

    @Override
    public String getEmployeeName(String id) throws SQLException {
        return employeeDAO.getEmployeeName(id);
    }

    @Override
    public boolean updateAttendance(EmployeeAttendanceDto dto) throws SQLException {
        return employeeAttendanceDAO.update(
                new Attendance(
                        dto.getAttendance_Id(),
                        dto.getEmployee_Id(),
                        dto.getDate(),
                        dto.getTime()
                )
        );
    }
}
