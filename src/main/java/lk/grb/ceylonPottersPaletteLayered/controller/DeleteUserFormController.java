package lk.grb.ceylonPottersPaletteLayered.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.grb.ceylonPottersPaletteLayered.bo.custom.UserBO;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeDto;
import lk.grb.ceylonPottersPaletteLayered.qr.QrReader;
import lk.grb.ceylonPottersPaletteLayered.util.Navigation;
import lk.grb.ceylonPottersPaletteLayered.util.SendEmail;
import lk.grb.ceylonPottersPaletteLayered.util.StyleUtil;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class DeleteUserFormController implements Initializable {

    @FXML
    private JFXButton btnConfirm;

    @FXML
    private Pane userDataPane;

    @FXML
    private Pane cancelBtnPane;

    @FXML
    private Pane closeIconPane;

    @FXML
    private Pane deleteBtnPane;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblCancel;

    @FXML
    private Label lblEmployeeId;

    @FXML
    private Label lblEmployeeName;

    @FXML
    private Label lblRole;

    @FXML
    private Label lblUsername;

    @FXML
    private Label lblConfirmAlert;

    @FXML
    private Label lblUnsuccessfulAlert;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCancelOnMouseEntered(MouseEvent event) {
        StyleUtil.cancelBtnSelected(cancelBtnPane, lblCancel);
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        StyleUtil.cancelBtnUnselected(cancelBtnPane, lblCancel);
    }

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
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
    void btnDeleteOnAction(ActionEvent event) throws SQLException, IOException {
        try {
            confirmBtnOnAction(event);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void confirmBtnOnAction(ActionEvent event) throws MessagingException, SQLException, IOException {

        userDataPane.setStyle("-fx-border-color: #E8E8E8;" +
                "-fx-border-radius: 12px");
        btnConfirm.setStyle("-fx-border-color: silver;" +
                "-fx-text-fill: #727374;" +
                "-fx-border-radius: 12px");

        lblUnsuccessfulAlert.setText(" ");
        lblConfirmAlert.setText(" ");

        AtomicReference<String> id = startQR();

        if(lblEmployeeId.getText().equals(String.valueOf(id))) deleteUser(event);
        else {
            userDataPane.setStyle("-fx-border-color: red;" +
                    "-fx-border-radius: 12px");
            btnConfirm.setStyle("-fx-border-color: red;" +
                    "-fx-text-fill: red;" +
                    "-fx-border-radius: 12px");
            lblUnsuccessfulAlert.setText("Unsuccessful Authentication!!");
        }
    }

    private AtomicReference<String> startQR() {
        AtomicReference<String> id = new AtomicReference<>();

        Thread qrThread = new Thread(() -> id.set(QrReader.readQr()));
        qrThread.start();

        try {
            qrThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    private void deleteUser(ActionEvent event) throws SQLException, IOException, MessagingException {
        if (userBO.deleteUser(GlobalFormController.user)) {
            Navigation.close(event);
            Navigation.switchNavigation("loginForm.fxml", event);
            sendConfirmationMail();
        }
    }

    private void sendConfirmationMail() throws SQLException, MessagingException {
        SendEmail sendEmail = new SendEmail();
        EmployeeDto employeeDto = userBO.getEmployeeData(lblEmployeeId.getText());

        String subject = "Account Deleted";
        String body = "Hello " + employeeDto.getFirst_Name() +" "+ employeeDto.getLast_Name() +
                ",\nYour Ceylon Potter's Pallet Account:\n" +
                "DELETED Successfully..!!!";

        String[] detail = {employeeDto.getEmail(), subject, body};
        sendEmail.sendMail(detail);
    }

    @FXML
    void btnDeleteOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(deleteBtnPane);
    }

    @FXML
    void btnDeleteOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(deleteBtnPane);
    }

    private void setLabelValues() {
        lblUsername.setText(GlobalFormController.user);
        try {
            lblEmployeeId.setText(userBO.getEmployeeId(GlobalFormController.user));
            lblEmployeeName.setText(userBO.getEmployeeName(lblEmployeeId.getText()));
            lblRole.setText(userBO.getEmployeeRole(lblEmployeeId.getText()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLabelValues();
    }
}
