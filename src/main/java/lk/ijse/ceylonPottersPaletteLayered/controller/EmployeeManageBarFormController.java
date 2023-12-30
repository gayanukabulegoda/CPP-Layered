package lk.ijse.ceylonPottersPaletteLayered.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.EmployeeBO;
import lk.ijse.ceylonPottersPaletteLayered.util.Navigation;
import lk.ijse.ceylonPottersPaletteLayered.dto.EmployeeDto;
import lk.ijse.ceylonPottersPaletteLayered.util.StyleUtil;

import java.io.IOException;
import java.sql.SQLException;

public class EmployeeManageBarFormController {

    @FXML
    private ImageView deleteImg;

    @FXML
    private Text email;

    @FXML
    private Text id;

    @FXML
    private Text contact_No;

    @FXML
    private Text name;

    @FXML
    private Text role;

    @FXML
    private ImageView updateImg;

    @FXML
    private ImageView viewImg;

    EmployeeBO employeeBO =
            (EmployeeBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.EMPLOYEE);

    @FXML
    void deleteOnMouseClick(MouseEvent event) throws IOException {
        ConfirmationPopUpFormController.setId(id.getText());
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
        EmployeeUpdatePopUpFormController.employeeId = id.getText();
        Navigation.imgPopUpBackground("employeeUpdatePopUpForm.fxml");
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
        EmployeeViewPopUpFormController.employeeId = id.getText();
        Navigation.imgPopUpBackground("employeeViewPopUpForm.fxml");
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
            EmployeeDto employeedto = employeeBO.getEmployeeData(id);

            this.id.setText(employeedto.getEmployee_Id());
            name.setText(employeeBO.setEmployeeName(employeedto.getFirst_Name(), employeedto.getLast_Name()));
            role.setText(employeedto.getRole());
            email.setText(employeedto.getEmail());
            contact_No.setText(employeedto.getContact_No());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
