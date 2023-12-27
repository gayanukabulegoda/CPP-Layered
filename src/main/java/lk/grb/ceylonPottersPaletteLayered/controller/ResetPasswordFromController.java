package lk.grb.ceylonPottersPaletteLayered.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lk.grb.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.grb.ceylonPottersPaletteLayered.bo.custom.UserBO;
import lk.grb.ceylonPottersPaletteLayered.dto.UserDto;
import lk.grb.ceylonPottersPaletteLayered.util.Navigation;
import lk.grb.ceylonPottersPaletteLayered.util.RegExPatterns;
import lk.grb.ceylonPottersPaletteLayered.util.StyleUtil;

import java.io.IOException;
import java.sql.SQLException;

public class ResetPasswordFromController {

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private PasswordField txtNewPassword;

    @FXML
    private Label lblConfirmPwAlert;

    @FXML
    private Label lblNewPwAlert;

    @FXML
    private JFXButton btnResetPassword;

    @FXML
    private ImageView imgBackBtn;

    @FXML
    private Pane backPane;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void btnResetPasswordOnAction(ActionEvent event) throws IOException, SQLException {

        if (validatePassword()) {
            UserDto userDto = new UserDto();

            userDto.setPassword(txtConfirmPassword.getText());
            userDto.setUser_Name(userBO.getUserName(OTPVerifyFormController.employeeId));

            if (userBO.updateUser(userDto)) {
                Navigation.close(event);
                Navigation.switchNavigation("loginForm.fxml", event);
            } else {
                lblNewPwAlert.setText("............");
                lblConfirmPwAlert.setText("Unable to Change Your Password! Try again!!");
            }
        }
    }

    private boolean validatePassword() {
        boolean result = true;

        if (RegExPatterns.passwordPattern(txtNewPassword.getText())) {
            lblNewPwAlert.setText("Invalid Password! Try again!!");
            result = false;
        }

        if ((RegExPatterns.passwordPattern(txtConfirmPassword.getText())) | (!txtNewPassword.getText().equals(txtConfirmPassword.getText()))) {
            lblConfirmPwAlert.setText("Invalid Password Confirmation!!");
            result = false;
        }
        return result;
    }

    @FXML
    void txtNewPasswordOnKeyPressed(KeyEvent event) {
        lblNewPwAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.passwordPattern(txtNewPassword.getText())) {
                lblNewPwAlert.setText("Invalid Password! Try again!!");
                event.consume();
            } else {
                txtConfirmPassword.requestFocus();
            }
        }
    }

    @FXML
    void txtConfirmPasswordOnKeyPressed(KeyEvent event) throws SQLException, IOException {
        lblConfirmPwAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if ((RegExPatterns.passwordPattern(txtConfirmPassword.getText())) | (!txtNewPassword.getText().equals(txtConfirmPassword.getText()))) {
                lblConfirmPwAlert.setText("Invalid Password Confirmation!!");
                event.consume();
            } else {
                ActionEvent actionEvent = new ActionEvent(
                        event.getSource(),
                        event.getTarget()
                );
                btnResetPasswordOnAction(actionEvent);
            }
        }
    }

    @FXML
    void confirmPasswordOnMouseClicked(MouseEvent event) {
        lblConfirmPwAlert.setText(" ");
    }

    @FXML
    void newPasswordOnMouseClicked(MouseEvent event) {
        lblNewPwAlert.setText(" ");
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("loginForm.fxml", event);
    }

    @FXML
    void btnResetPasswordOnMouseEntered(MouseEvent event) {
        StyleUtil.signUpOrLogInBtnSelected(btnResetPassword);
    }

    @FXML
    void btnResetPasswordOnMouseExited(MouseEvent event) {
        StyleUtil.signUpOrLogInBtnUnselected(btnResetPassword);
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
