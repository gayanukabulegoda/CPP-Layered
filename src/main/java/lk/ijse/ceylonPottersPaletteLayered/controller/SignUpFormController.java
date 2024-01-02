package lk.ijse.ceylonPottersPaletteLayered.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
import lk.ijse.ceylonPottersPaletteLayered.dto.UserDto;
import lk.ijse.ceylonPottersPaletteLayered.util.StyleUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SignUpFormController {

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblPasswordAlert;

    @FXML
    private Label lblUserNameAlert;

    @FXML
    private TextField txtUsername;

    @FXML
    private JFXButton btnSignUp;

    @FXML
    private ImageView imgBackBtn;

    @FXML
    private Pane backPane;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("loginForm.fxml", event);
    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) throws IOException, SQLException {
        if (checkUserNamesAvailability()) return;

        if (validateCredentials()) {
            UserDto userDto = new UserDto();

            userDto.setUser_Name(txtUsername.getText());
            userDto.setPassword(txtPassword.getText());
            userDto.setEmployeeId(SignUpOTPVerifyFormController.employeeId);

            if (userBO.saveUser(userDto)) {
                Navigation.close(event);
                GlobalFormController.user = txtUsername.getText();
                Navigation.switchNavigation("globalForm.fxml", event);
            } else {
                lblUserNameAlert.setText("............");
                lblPasswordAlert.setText("Oops! Unable to Save Your Data!!");
            }
        }
    }

    private boolean checkUserNamesAvailability() throws SQLException {
        ArrayList<String> allUserNames = userBO.getAllUserNames();
        for (String userName : allUserNames) {
            if (txtUsername.getText().equals(userName)) {
                lblUserNameAlert.setText("UserName Already Exists!!");
                return true;
            }
        }
        return false;
    }

    @FXML
    void txtUserNameOnMouseClicked(MouseEvent event) {
        lblUserNameAlert.setText(" ");
    }

    @FXML
    void txtPasswordOnMouseClicked(MouseEvent event) {
        lblPasswordAlert.setText(" ");
    }

    private boolean validateCredentials() {
        boolean result = true;

        if (RegExPatterns.userNamePattern(txtUsername.getText())) {
            lblUserNameAlert.setText("Enter a valid Username!!");
            result = false;
        }

        if (RegExPatterns.passwordPattern(txtPassword.getText())) {
            lblPasswordAlert.setText("Invalid Password!!");
            result = false;
        }
        return result;
    }

    @FXML
    void txtUsernameOnKeyPressed(KeyEvent event) throws SQLException {
        lblUserNameAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.userNamePattern(txtUsername.getText())) {
                lblUserNameAlert.setText("Enter a valid Username!!");
                event.consume();
            } else {
                if (checkUserNamesAvailability()) return;
                txtPassword.requestFocus();
            }
        }
    }

    @FXML
    void txtPasswordOnKeyPressed(KeyEvent event) throws SQLException, IOException {
        lblPasswordAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.passwordPattern(txtPassword.getText())) {
                lblPasswordAlert.setText("Invalid Password!!");
                event.consume();
            } else {
                ActionEvent actionEvent = new ActionEvent(
                        event.getSource(),
                        event.getTarget()
                );
                btnSignUpOnAction(actionEvent);
            }
        }
    }

    @FXML
    void btnSignUpOnMouseEntered(MouseEvent event) {
        StyleUtil.signUpOrLogInBtnSelected(btnSignUp);
    }

    @FXML
    void btnSignUpOnMouseExited(MouseEvent event) {
        StyleUtil.signUpOrLogInBtnUnselected(btnSignUp);
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
