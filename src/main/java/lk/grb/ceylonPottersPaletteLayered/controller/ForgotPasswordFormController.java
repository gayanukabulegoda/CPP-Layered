package lk.grb.ceylonPottersPaletteLayered.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lk.grb.ceylonPottersPalette.model.UserModel;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.RegExPatterns;
import lk.grb.ceylonPottersPalette.util.StyleUtil;

import java.io.IOException;
import java.sql.SQLException;

public class ForgotPasswordFormController {

    @FXML
    private TextField txtUsername;

    @FXML
    private Label lblUserNameAlert;

    @FXML
    private Pane backPane;

    @FXML
    private JFXButton btnResetPassword;

    @FXML
    private ImageView imgBackBtn;

    UserModel userModel = new UserModel();

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("loginForm.fxml",event);
    }

    @FXML
    void btnResetPasswordOnAction(ActionEvent event) throws IOException, SQLException {

        if(validateUserName()) {
            String employeeId = userModel.getEmployeeId(txtUsername.getText());

            if (userModel.getUserName(employeeId).equals(txtUsername.getText())) {

                OTPVerifyFormController.employeeId = employeeId;

                Navigation.close(event);
                Navigation.switchNavigation("OTPVerifyForm.fxml", event);
            } else {
                lblUserNameAlert.setText("Invalid UserName!! Try Again!!");
            }
        }
    }

    private boolean validateUserName() {

        if (RegExPatterns.userNamePattern(txtUsername.getText())) {
            lblUserNameAlert.setText("Invalid Username!!");
            return false;
        }
        return true;
    }

    @FXML
    void txtUsernameOnKeyPressed(KeyEvent event) throws SQLException, IOException {
        lblUserNameAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.userNamePattern(txtUsername.getText())) {
                lblUserNameAlert.setText("Invalid Username!!");
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
    void userNameOnMouseClicked(MouseEvent event) {
        lblUserNameAlert.setText(" ");
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
