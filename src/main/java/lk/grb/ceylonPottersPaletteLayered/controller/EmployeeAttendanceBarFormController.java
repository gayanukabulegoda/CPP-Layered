package lk.grb.ceylonPottersPaletteLayered.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.grb.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.grb.ceylonPottersPaletteLayered.bo.custom.AttendanceBO;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeAttendanceDto;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeDto;
import lk.grb.ceylonPottersPaletteLayered.util.Navigation;
import lk.grb.ceylonPottersPaletteLayered.util.StyleUtil;

import java.io.IOException;
import java.sql.SQLException;

public class EmployeeAttendanceBarFormController {

    @FXML
    private Text date;

    @FXML
    private ImageView deleteImg;

    @FXML
    private ImageView updateImg;

    @FXML
    private Text id;

    @FXML
    private Text name;

    @FXML
    private Text time;

    private String employee_Attendance_Id;

    AttendanceBO attendanceBO =
            (AttendanceBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.ATTENDANCE);

    @FXML
    void deleteOnMouseClick(MouseEvent event) throws IOException {
        ConfirmationPopUpFormController.setId(employee_Attendance_Id);
        Navigation.imgPopUpBackground("confirmationPopUpForm.fxml");
    }

    @FXML
    void deleteOnMouseEntered(MouseEvent event) {
        StyleUtil.deleteImgSelected(deleteImg);
    }

    @FXML
    void deleteOnMouseExited(MouseEvent event) {
        StyleUtil.deleteImgUnselected(deleteImg);
    }


    @FXML
    void updateOnMouseClick(MouseEvent event) throws IOException {
        EmployeeAttendanceUpdatePopUpFormController.employeeAttendanceId = employee_Attendance_Id;
        Navigation.imgPopUpBackground("employeeAttendanceUpdatePopUpForm.fxml");
    }

    @FXML
    void updateOnMouseEntered(MouseEvent event) {
        StyleUtil.updateImgSelected(updateImg);
    }

    @FXML
    void updateOnMouseExited(MouseEvent event) {
        StyleUtil.updateImgUnselected(updateImg);
    }

    public void setData(String id) {
        try {
            EmployeeAttendanceDto employeeAttendanceDto = attendanceBO.getAttendanceData(id);
            EmployeeDto employeeDto = attendanceBO.getEmployeeData(employeeAttendanceDto.getEmployee_Id());

            this.id.setText(employeeAttendanceDto.getEmployee_Id());
            name.setText(attendanceBO.setEmployeeName(employeeDto.getFirst_Name(), employeeDto.getLast_Name()));
            time.setText(employeeAttendanceDto.getTime());
            date.setText(employeeAttendanceDto.getDate());
            employee_Attendance_Id = id;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
