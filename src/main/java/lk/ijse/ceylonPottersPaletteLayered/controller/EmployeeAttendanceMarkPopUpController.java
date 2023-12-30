package lk.ijse.ceylonPottersPaletteLayered.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.ijse.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.AttendanceBO;
import lk.ijse.ceylonPottersPaletteLayered.util.Navigation;
import lk.ijse.ceylonPottersPaletteLayered.util.NewId;
import lk.ijse.ceylonPottersPaletteLayered.dto.EmployeeAttendanceDto;
import lk.ijse.ceylonPottersPaletteLayered.util.DateTimeUtil;
import lk.ijse.ceylonPottersPaletteLayered.util.StyleUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeAttendanceMarkPopUpController implements Initializable {

    @FXML
    private Label lblCmbEmployeeIdAlert;

    @FXML
    private Pane closeIconPane;

    @FXML
    private JFXComboBox<String> cmbEmployeeId;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblEmployeeName;

    @FXML
    private Pane markAttendaceBtnPane;

    static AttendanceBO attendanceBO =
            (AttendanceBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.ATTENDANCE);

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnMarkAttendanceOnAction() throws SQLException {

        if (validateEmployeeAttendance()) {
            EmployeeAttendanceDto employeeAttendanceDto = new EmployeeAttendanceDto();

            ArrayList<String> list = attendanceBO.getAllAttendanceId();

            employeeAttendanceDto.setAttendance_Id(NewId.newId(list, NewId.GetType.ATTENDANCE_ID));
            employeeAttendanceDto.setEmployee_Id(cmbEmployeeId.getSelectionModel().getSelectedItem());
            employeeAttendanceDto.setDate(DateTimeUtil.dateNow());
            employeeAttendanceDto.setTime(DateTimeUtil.timeNow());

            boolean save = attendanceBO.saveAttendance(employeeAttendanceDto);

            if (save) {
                Navigation.closePane();
                EmployeeAttendanceFormController.getInstance().allAttendanceId();
            }
        }
    }

    public static void markAttendanceOViaQr(String id) throws SQLException {

        EmployeeAttendanceDto employeeAttendanceDto = new EmployeeAttendanceDto();

        ArrayList<String> list = attendanceBO.getAllAttendanceId();

        employeeAttendanceDto.setAttendance_Id(NewId.newId(list, NewId.GetType.ATTENDANCE_ID));
        employeeAttendanceDto.setEmployee_Id(id);
        employeeAttendanceDto.setDate(DateTimeUtil.dateNow());
        employeeAttendanceDto.setTime(DateTimeUtil.timeNow());

        boolean save = attendanceBO.saveAttendance(employeeAttendanceDto);

        if (save) {
            EmployeeAttendanceFormController.getInstance().allAttendanceId();
        }
    }

    private boolean validateEmployeeAttendance() {

        if ((cmbEmployeeId.getSelectionModel().getSelectedItem()) == null) {
            lblCmbEmployeeIdAlert.setText("Select an Employee!!");
            return false;
        }
        return true;
    }

    @FXML
    void cmbEmployeeIdOnKeyPressed(KeyEvent event) throws SQLException {
        lblCmbEmployeeIdAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if ((cmbEmployeeId.getSelectionModel().getSelectedItem()) == null) {
                lblCmbEmployeeIdAlert.setText("Select an Employee!!");
                event.consume();
            } else {
                btnMarkAttendanceOnAction();
            }
        }
    }

    @FXML
    void cmbEmployeeIdOnMouseClicked(MouseEvent event) {
        lblCmbEmployeeIdAlert.setText(" ");
    }

    @FXML
    void cmbEmployeeIdOnAction(ActionEvent event) throws SQLException {
        lblEmployeeName.setText(attendanceBO.getEmployeeName(String.valueOf(cmbEmployeeId.getSelectionModel().getSelectedItem())));
    }

    public void setDataInComboBox() {
        ArrayList<String> roles;
        try {
            roles = attendanceBO.getAllEmployeeId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        cmbEmployeeId.getItems().addAll(roles);
    }

    @FXML
    void btnMarkAttendanceOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(markAttendaceBtnPane);
    }

    @FXML
    void btnMarkAttendanceOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(markAttendaceBtnPane);
    }

    @FXML
    void btnCloseIconOnMouseEntered(MouseEvent event) {
        StyleUtil.closeIconBtnSelected(closeIconPane, imgCloseIcon);
    }

    @FXML
    void btnCloseIconOnMouseExited(MouseEvent event) {
        StyleUtil.closeIconBtnUnselected(closeIconPane, imgCloseIcon);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDataInComboBox();
    }
}
