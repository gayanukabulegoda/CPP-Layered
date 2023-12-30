package lk.ijse.ceylonPottersPaletteLayered.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lk.ijse.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.UserBO;
import lk.ijse.ceylonPottersPaletteLayered.util.Navigation;
import lk.ijse.ceylonPottersPaletteLayered.util.RegExPatterns;
import lk.ijse.ceylonPottersPaletteLayered.util.StyleUtil;

import java.io.IOException;
import java.sql.SQLException;

public class SignUpEmployeeConfirmFormController {

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private Label lblEmployeeIdAlert;

    @FXML
    private JFXButton btnConfirm;

    @FXML
    private Hyperlink hyperLogIn;

    @FXML
    private Pane backPane;

    @FXML
    private ImageView imgBackBtn;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("loginForm.fxml", event);
    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) throws IOException, SQLException {
        if(validateEmployeeId()) {
            if (userBO.getEmployeeName(txtEmployeeId.getText()) != null) {
                if (userBO.getUserName(txtEmployeeId.getText()).equals("null")) {
                    SignUpOTPVerifyFormController.employeeId = txtEmployeeId.getText();

                    Navigation.close(event);
                    Navigation.switchNavigation("signUpOTPVerifyForm.fxml", event);
                } else {
                    lblEmployeeIdAlert.setText("Already you have an Account!!");
                }
            } else {
                lblEmployeeIdAlert.setText("Invalid ID!! Try Again!!");
            }
        }
    }

    private boolean validateEmployeeId() {
        if (RegExPatterns.employeeIdPattern(txtEmployeeId.getText())) {
            lblEmployeeIdAlert.setText("Invalid ID!! Try Again!!");
            return false;
        }
        return true;
    }

    @FXML
    void txtEmployeeIdOnKeyPressed(KeyEvent event) throws SQLException, IOException {
        lblEmployeeIdAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.employeeIdPattern(txtEmployeeId.getText())) {
                lblEmployeeIdAlert.setText("Invalid ID!! Try Again!!");
                event.consume();
            } else {
                ActionEvent actionEvent = new ActionEvent(
                        event.getSource(),
                        event.getTarget()
                );
                btnConfirmOnAction(actionEvent);
            }
        }
    }

    @FXML
    void txtEmployeeIdOnMouseClicked(MouseEvent event) {
        lblEmployeeIdAlert.setText(" ");
    }

    @FXML
    void hyperLogInOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("loginForm.fxml", event);
    }

    @FXML
    void hyperLogInOnMouseEntered(MouseEvent event) {
        StyleUtil.hyperLinkSelected(hyperLogIn);
    }

    @FXML
    void hyperLogInOnMouseExited(MouseEvent event) {
        StyleUtil.hyperLinkUnselected(hyperLogIn);
    }

    @FXML
    void btnConfirmOnMouseEntered(MouseEvent event) {
        StyleUtil.signUpOrLogInBtnSelected(btnConfirm);
    }

    @FXML
    void btnConfirmOnMouseExited(MouseEvent event) {
        StyleUtil.signUpOrLogInBtnUnselected(btnConfirm);
    }

    @FXML
    void btnBackOnMouseEntered(MouseEvent event) {
        backPane.setVisible(true);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.2), backPane);
        transition.setFromX(-backPane.getWidth()); // Slide in from the left
        transition.setToX(0);
        transition.play();

        StyleUtil.powerOffOrBackBtnSelected(imgBackBtn);
    }

    @FXML
    void btnBackOnMouseExited(MouseEvent event) {
        backPane.setVisible(false);
        StyleUtil.powerOffOrBackBtnUnselected(imgBackBtn);
    }
}
