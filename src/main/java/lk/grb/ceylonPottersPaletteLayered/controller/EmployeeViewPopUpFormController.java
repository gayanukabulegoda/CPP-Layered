package lk.grb.ceylonPottersPaletteLayered.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeDto;
import lk.grb.ceylonPottersPaletteLayered.model.EmployeeModel;
import lk.grb.ceylonPottersPaletteLayered.util.Navigation;
import lk.grb.ceylonPottersPaletteLayered.util.StyleUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeViewPopUpFormController implements Initializable {

    @FXML
    private Pane btnClosePane;

    @FXML
    private Pane closeIconPane;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblContactNo;

    @FXML
    private Label lblEmployeeId;

    @FXML
    private Label lblEmployeeName;

    @FXML
    private Label lblRegisteredDate;

    @FXML
    private Label lblRegisteredTime;

    @FXML
    private Label lblUser;

    public static String employeeId;

    EmployeeModel employeeModel = new EmployeeModel();

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseIconOnMouseEntered(MouseEvent event) {
        StyleUtil.closeIconBtnSelected(closeIconPane, imgCloseIcon);
    }

    @FXML
    void btnCloseIconOnMouseExited(MouseEvent event) {
        StyleUtil.closeIconBtnUnselected(closeIconPane, imgCloseIcon);
    }

    @FXML
    void btnCloseOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(btnClosePane);
    }

    @FXML
    void btnCloseOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(btnClosePane);
    }

    public void setData() throws SQLException {

        EmployeeDto employeeDto = employeeModel.getData(employeeId);

        lblEmployeeId.setText(employeeDto.getEmployee_Id());
        lblEmployeeName.setText(employeeDto.getFirst_Name() + " " + employeeDto.getLast_Name());
        lblContactNo.setText(employeeDto.getContact_No());
        lblRegisteredDate.setText(employeeDto.getDate());
        lblRegisteredTime.setText(employeeDto.getTime());
        lblUser.setText(employeeDto.getUserName());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
