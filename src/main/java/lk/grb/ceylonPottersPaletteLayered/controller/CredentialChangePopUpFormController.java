package lk.grb.ceylonPottersPaletteLayered.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.grb.ceylonPottersPaletteLayered.bo.custom.UserBO;
import lk.grb.ceylonPottersPaletteLayered.dto.UserDto;
import lk.grb.ceylonPottersPaletteLayered.util.*;

import java.sql.SQLException;

public class CredentialChangePopUpFormController {

    @FXML
    private Pane SaveBtnPane;

    @FXML
    private JFXButton btnSave;

    @FXML
    private Pane cancelBtnPane;

    @FXML
    private Pane closeIconPane;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblCancel;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private PasswordField txtCurrentPassword;

    @FXML
    private PasswordField txtNewPassword;

    @FXML
    private Label lblConfirmPwAlert;

    @FXML
    private Label lblCurrentPwAlert;

    @FXML
    private Label lblNewPwAlert;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {

        if (validatePasswords()) {
            UserDto userDto = new UserDto();

            userDto.setUser_Name(GlobalFormController.user);
            userDto.setPassword(txtConfirmPassword.getText());

            LoginFormController.password = txtConfirmPassword.getText();
            boolean updated = userBO.updateUser(userDto);

            if (updated) {
                Navigation.closePane();
            } else {
                lblConfirmPwAlert.setText("Error!! Try Again!");
            }
        }
    }

    private boolean validatePasswords() {
        boolean result = true;

        if (!LoginFormController.password.equals(txtCurrentPassword.getText())) {
            lblCurrentPwAlert.setText("Wrong Password!!");
            result = false;
        }

        if (RegExPatterns.passwordPattern(txtNewPassword.getText())) {
            lblNewPwAlert.setText("Invalid Password!!\nPassword should contain at least 6 characters");
            result = false;
        }

        if (!txtNewPassword.getText().equals(txtConfirmPassword.getText())){
            lblConfirmPwAlert.setText("New Password & Confirmation Doesn't Match!!");
            result = false;
        }
        return result;
    }

    @FXML
    void txtCurrentPasswordOnKeyPressed(KeyEvent event) {
        lblCurrentPwAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (!LoginFormController.password.equals(txtCurrentPassword.getText())) {
                lblCurrentPwAlert.setText("Wrong Password!!");
                event.consume();
            } else {
                txtNewPassword.setEditable(true);
                txtConfirmPassword.setEditable(true);
                txtNewPassword.requestFocus();
            }
        }
    }

    @FXML
    void txtNewPasswordOnKeyPressed(KeyEvent event) {
        lblNewPwAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.passwordPattern(txtNewPassword.getText())) {
                lblNewPwAlert.setText("Invalid Password!!\nPassword should contain at least 6 characters");
                event.consume();
            } else {
                txtConfirmPassword.requestFocus();
            }
        }
    }

    @FXML
    void txtConfirmNewPasswordOnKeyPressed(KeyEvent event) throws SQLException {
        lblConfirmPwAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (!txtNewPassword.getText().equals(txtConfirmPassword.getText())){
                lblConfirmPwAlert.setText("New Password & Confirmation Doesn't Match!!");
                event.consume();
            } else {
                btnSave.setVisible(true);

                ActionEvent actionEvent = new ActionEvent(
                        event.getSource(),
                        event.getTarget()
                );
                btnSaveOnAction(actionEvent);
            }
        }
    }

    @FXML
    void btnSaveOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(SaveBtnPane);
    }

    @FXML
    void btnSaveOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(SaveBtnPane);
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
    void btnCancelOnMouseEntered(MouseEvent event) {
        StyleUtil.cancelBtnSelected(cancelBtnPane, lblCancel);
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        StyleUtil.cancelBtnUnselected(cancelBtnPane, lblCancel);
    }
}
