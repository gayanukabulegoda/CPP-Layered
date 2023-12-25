package lk.grb.ceylonPottersPaletteLayered.controller;

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
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeAttendanceDto;
import lk.grb.ceylonPottersPaletteLayered.model.EmployeeAttendanceModel;
import lk.grb.ceylonPottersPaletteLayered.model.EmployeeModel;
import lk.grb.ceylonPottersPaletteLayered.util.Navigation;
import lk.grb.ceylonPottersPaletteLayered.util.StyleUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeAttendanceUpdatePopUpFormController implements Initializable {

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
    private Pane updateAttendaceBtnPane;

    public static String employeeAttendanceId;

    EmployeeModel employeeModel = new EmployeeModel();
    EmployeeAttendanceModel employeeAttendanceModel = new EmployeeAttendanceModel();

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnUpdateAttendanceOnAction() throws SQLException {

        if (validateEmployeeAttendance()) {
            EmployeeAttendanceDto employeeAttendanceDto = new EmployeeAttendanceDto();

            employeeAttendanceDto.setAttendance_Id(employeeAttendanceId);
            employeeAttendanceDto.setEmployee_Id(cmbEmployeeId.getSelectionModel().getSelectedItem());
            employeeAttendanceDto.setDate(employeeAttendanceDto.getDate());
            employeeAttendanceDto.setTime(employeeAttendanceDto.getTime());

            boolean updated = employeeAttendanceModel.update(employeeAttendanceDto);

            if (updated) {
                Navigation.closePane();
                EmployeeAttendanceFormController.getInstance().allAttendanceId();
            }
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
                btnUpdateAttendanceOnAction();
            }
        }
    }

    @FXML
    void cmbEmployeeIdOnMouseClicked(MouseEvent event) {
        lblCmbEmployeeIdAlert.setText(" ");
    }

    @FXML
    void cmbEmployeeIdOnAction(ActionEvent event) throws SQLException {
        lblEmployeeName.setText(employeeModel.getEmployeeName(String.valueOf(cmbEmployeeId.getSelectionModel().getSelectedItem())));
    }

    public void setDataInComboBox() throws SQLException {
        ArrayList<String> roles = employeeModel.getAllEmployeeId();
        cmbEmployeeId.getItems().addAll(roles);
    }

    @FXML
    void btnUpdateAttendanceOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(updateAttendaceBtnPane);
    }

    @FXML
    void btnUpdateAttendanceOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(updateAttendaceBtnPane);
    }

    @FXML
    void btnCloseIconOnMouseEntered(MouseEvent event) {
        StyleUtil.closeIconBtnSelected(closeIconPane, imgCloseIcon);
    }

    @FXML
    void btnCloseIconOnMouseExited(MouseEvent event) {
        StyleUtil.closeIconBtnUnselected(closeIconPane, imgCloseIcon);
    }

    public void setData() {
        try {
            EmployeeAttendanceDto employeeAttendanceDto = employeeAttendanceModel.getData(employeeAttendanceId);

            lblEmployeeName.setText(employeeModel.getEmployeeName(employeeAttendanceDto.getEmployee_Id()));
            cmbEmployeeId.setValue(employeeAttendanceDto.getEmployee_Id());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setDataInComboBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        setData();
    }
}
