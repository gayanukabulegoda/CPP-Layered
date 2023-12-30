package lk.ijse.ceylonPottersPaletteLayered.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lk.ijse.ceylonPottersPaletteLayered.util.Navigation;
import lk.ijse.ceylonPottersPaletteLayered.util.RegExPatterns;
import lk.ijse.ceylonPottersPaletteLayered.util.SendEmail;
import lk.ijse.ceylonPottersPaletteLayered.util.StyleUtil;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class SignUpOTPVerifyFormController implements Initializable {

    @FXML
    private TextField txtOTP;

    @FXML
    private Label lblOtpAlert;

    @FXML
    private ImageView btnBackImg;

    @FXML
    private ImageView imgBackBtn;

    @FXML
    private Pane btnBackPane;

    @FXML
    private Pane backPane;

    @FXML
    private JFXButton btnVerify;

    public static String employeeId;
    private String otp;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("signUpEmployeeConfirmForm.fxml", event);
    }

    @FXML
    void btnVerifyOnMouseEntered(MouseEvent event) {
        StyleUtil.signUpOrLogInBtnSelected(btnVerify);
    }

    @FXML
    void btnVerifyOnMouseExited(MouseEvent event) {
        StyleUtil.signUpOrLogInBtnUnselected(btnVerify);
    }

    @FXML
    void btnBackOnMouseEntered(MouseEvent event) {
        StyleUtil.otpBackBtnSelected(btnBackPane, btnBackImg);
    }

    @FXML
    void btnBackOnMouseExited(MouseEvent event) {
        StyleUtil.otpBackBtnUnselected(btnBackPane, btnBackImg);
    }

    @FXML
    void btnBackIconOnMouseEntered(MouseEvent event) {
        backPane.setVisible(true);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.2), backPane);
        transition.setFromX(-backPane.getWidth()); // Slide in from the left
        transition.setToX(0);
        transition.play();

        StyleUtil.powerOffOrBackBtnSelected(imgBackBtn);
    }

    @FXML
    void btnBackIconOnMouseExited(MouseEvent event) {
        backPane.setVisible(false);
        StyleUtil.powerOffOrBackBtnUnselected(imgBackBtn);
    }

    @FXML
    void btnVerifyOnAction(ActionEvent event) throws IOException {

        if (validateOtp()) {
            Navigation.close(event);
            Navigation.switchNavigation("signUpForm.fxml", event);
        }
    }

    private boolean validateOtp() {

        if (RegExPatterns.otpPattern(txtOTP.getText()) | (!otp.equals(txtOTP.getText()))) {
            lblOtpAlert.setText("Wrong OTP!! Try Again!!");
            return false;
        }
        return true;
    }

    @FXML
    void txtOTPOnKeyPressed(KeyEvent event) throws IOException {
        lblOtpAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.otpPattern(txtOTP.getText()) | (!otp.equals(txtOTP.getText()))) {
                lblOtpAlert.setText("Wrong OTP!! Try Again!!");
                event.consume();
            } else {
                // Convert the key event to an ActionEvent
                ActionEvent actionEvent = new ActionEvent(
                        event.getSource(),
                        event.getTarget()
                );
                btnVerifyOnAction(actionEvent);
            }
        }
    }

    @FXML
    void txtOtpOnMouseClicked(MouseEvent event) {
        lblOtpAlert.setText(" ");
    }

    private String generateOTP() {
        String numbers = "0123456789";
        StringBuilder otp = new StringBuilder(6);

        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(numbers.length());
            char digit = numbers.charAt(index);
            otp.append(digit);
        }

        return otp.toString();
    }

    private void sendOTPMail() {
        try {
            String email = "ceylonpotterspallet@gmail.com";
            String subject = "OTP Verification";
            String body = "OTP : " + otp;

            String[] detail = {email, subject, body};
            SendEmail.sendMail(detail);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        otp = generateOTP();
        sendOTPMail();
    }
}
