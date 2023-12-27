package lk.grb.ceylonPottersPaletteLayered.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.grb.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.grb.ceylonPottersPaletteLayered.bo.custom.SalaryBO;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeDto;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeSalaryDto;
import lk.grb.ceylonPottersPaletteLayered.util.Navigation;
import lk.grb.ceylonPottersPaletteLayered.util.StyleUtil;

import java.io.IOException;
import java.sql.SQLException;

public class EmployeeSalaryBarFormController {

    @FXML
    private Text date;

    @FXML
    private Text id;

    @FXML
    private Text name;

    @FXML
    private Text payment;

    @FXML
    private ImageView updateImg;

    @FXML
    private ImageView viewImg;

    private String salary_Id;

    SalaryBO salaryBO =
            (SalaryBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.SALARY);

    @FXML
    void updateOnMouseClick(MouseEvent event) throws IOException {
        EmployeeSalaryUpdatePopUpFormController.salaryId = salary_Id;
        Navigation.imgPopUpBackground("employeeSalaryUpdatePopUpForm.fxml");
    }

    @FXML
    void updateOnMouseEntered(MouseEvent event) {
        StyleUtil.updateImgSelected(updateImg);
    }

    @FXML
    void updateOnMouseExited(MouseEvent event) {
        StyleUtil.updateImgUnselected(updateImg);
    }

    @FXML
    void viewDetailsOnMouseClick(MouseEvent event) throws IOException {
        EmployeeSalaryViewPopUpFormController.salaryId = salary_Id;
        Navigation.imgPopUpBackground("employeeSalaryViewPopUpForm.fxml");
    }

    @FXML
    void viewOnMouseEntered(MouseEvent event) {
        StyleUtil.viewImgSelected(viewImg);
    }

    @FXML
    void viewOnMouseExited(MouseEvent event) {
        StyleUtil.viewImgUnselected(viewImg);
    }

    public void setData(String id) {

        try {
            EmployeeSalaryDto employeeSalaryDto = salaryBO.getSalaryData(id);
            EmployeeDto employeeDto = salaryBO.getEmployeeData(employeeSalaryDto.getEmployee_Id());

            this.id.setText(employeeSalaryDto.getEmployee_Id());
            name.setText(employeeDto.getFirst_Name() + " " + employeeDto.getLast_Name());
            payment.setText(String.valueOf(employeeSalaryDto.getTotal_Payment()));
            date.setText(employeeSalaryDto.getDate());
            salary_Id = id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
