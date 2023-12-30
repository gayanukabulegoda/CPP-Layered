package lk.grb.ceylonPottersPaletteLayered.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.grb.ceylonPottersPaletteLayered.bo.custom.SalaryBO;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeSalaryDto;
import lk.grb.ceylonPottersPaletteLayered.util.Navigation;
import lk.grb.ceylonPottersPaletteLayered.util.StyleUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeSalaryViewPopUpFormController implements Initializable {

    @FXML
    private Pane btnClosePane;

    @FXML
    private Pane closeIconPane;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblBonus;

    @FXML
    private Label lblEmployeeId;

    @FXML
    private Label lblPaymentDate;

    @FXML
    private Label lblPaymentTime;

    @FXML
    private Label lblSalary;

    @FXML
    private Label lblTotalPayment;

    public static String salaryId;

    SalaryBO salaryBO =
            (SalaryBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.SALARY);

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

    public void setData() {

        EmployeeSalaryDto employeeSalaryDto;
        try {
            employeeSalaryDto = salaryBO.getSalaryData(salaryId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        lblEmployeeId.setText(employeeSalaryDto.getEmployee_Id());
        lblSalary.setText(String.valueOf(employeeSalaryDto.getSalary()));
        lblBonus.setText(String.valueOf(employeeSalaryDto.getBonus()));
        lblTotalPayment.setText(String.valueOf(employeeSalaryDto.getTotal_Payment()));
        lblPaymentDate.setText(employeeSalaryDto.getDate());
        lblPaymentTime.setText(employeeSalaryDto.getTime());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }
}
