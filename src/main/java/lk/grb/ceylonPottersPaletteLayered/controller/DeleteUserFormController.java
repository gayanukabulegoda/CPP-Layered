package lk.grb.ceylonPottersPaletteLayered.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPalette.dto.EmployeeDto;
import lk.grb.ceylonPottersPalette.model.EmployeeModel;
import lk.grb.ceylonPottersPalette.model.UserModel;
import lk.grb.ceylonPottersPalette.qr.QrReader;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.SendEmail;
import lk.grb.ceylonPottersPalette.util.StyleUtil;

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

    UserModel userModel = new UserModel();
    EmployeeModel employeeModel = new EmployeeModel();

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

        AtomicReference<String> id = new AtomicReference<>();

        Thread qrThread = new Thread(() -> id.set(QrReader.readQr()));
        qrThread.start();

        try {
            qrThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if(lblEmployeeId.getText().equals(String.valueOf(id))) {

            boolean delete = userModel.delete(GlobalFormController.user);
            if (delete) {
                Navigation.close(event);
                Navigation.switchNavigation("loginForm.fxml", event);

                SendEmail sendEmail = new SendEmail();
                EmployeeDto employeeDto = employeeModel.getData(lblEmployeeId.getText());

                String subject = "Account Deleted";
                String body = "Hello " + employeeDto.getFirst_Name() +" "+ employeeDto.getLast_Name() +
                        ",\nYour Ceylon Potter's Pallet Account:\n" +
                        "DELETED Successfully..!!!";

                String[] detail = {employeeDto.getEmail(), subject, body};
                sendEmail.sendMail(detail);
            }

        } else {
            userDataPane.setStyle("-fx-border-color: red;" +
                    "-fx-border-radius: 12px");
            btnConfirm.setStyle("-fx-border-color: red;" +
                    "-fx-text-fill: red;" +
                    "-fx-border-radius: 12px");
            lblUnsuccessfulAlert.setText("Unsuccessful Authentication!!");
        }
    }

    @FXML
    void btnDeleteOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(deleteBtnPane);
    }

    @FXML
    void btnDeleteOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(deleteBtnPane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblUsername.setText(GlobalFormController.user);

        try {
            lblEmployeeId.setText(userModel.getEmployeeId(GlobalFormController.user));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            lblEmployeeName.setText(employeeModel.getEmployeeName(lblEmployeeId.getText()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            lblRole.setText(employeeModel.getEmployeeRole(lblEmployeeId.getText()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
