package lk.ijse.ceylonPottersPaletteLayered.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.ijse.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.CustomerBO;
import lk.ijse.ceylonPottersPaletteLayered.dto.CustomerDto;
import lk.ijse.ceylonPottersPaletteLayered.util.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerAddPopUpFormController {

    @FXML
    private Pane AddBtnPane;

    @FXML
    private Pane cancelBtnPane;

    @FXML
    private Pane closeIconPane;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblCancel;

    @FXML
    private TextField txtContactNo;

    @FXML
    private TextField txtCustomerEmail;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private Label lblContactNoAlert;

    @FXML
    private Label lblCustomerEmailAlert;

    @FXML
    private Label lblCustomerNameAlert;

    CustomerBO customerBO =
            (CustomerBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.CUSTOMER);

    @FXML
    void btnAddOnAction() throws SQLException {
        CustomerDto customerDto = new CustomerDto();

        ArrayList<String> list = customerBO.getAllCustomerId();

        if (checkContactNoAvailability()) return;
        if (checkEmailAvailability()) return;

        if (validateCustomer()) {

            customerDto.setCustomer_Id(NewId.newId(list, NewId.GetType.CUSTOMER));
            customerDto.setName(txtCustomerName.getText());
            customerDto.setContact_No(txtContactNo.getText());
            customerDto.setEmail(txtCustomerEmail.getText());
            customerDto.setTime(DateTimeUtil.timeNow());
            customerDto.setDate(DateTimeUtil.dateNow());
            customerDto.setUser_Name(GlobalFormController.user);

            if (customerBO.saveCustomer(customerDto)) {
                Navigation.closePane();
                CustomerManageFormController.getInstance().allCustomerId();
            }
        }
    }

    private boolean checkContactNoAvailability() throws SQLException {
        ArrayList<String> allCustomerContactNumbers = customerBO.getAllCustomerContactNumbers();

        for (String contactNo : allCustomerContactNumbers) {
            if (txtContactNo.getText().equals(contactNo)) {
                lblContactNoAlert.setText("Contact Number Already Exists!!");
                return true;
            }
        }
        return false;
    }

    private boolean checkEmailAvailability() throws SQLException {
        ArrayList<String> allCustomerEmails = customerBO.getAllCustomerEmails();

        for (String email : allCustomerEmails) {
            if (txtCustomerEmail.getText().equals(email)) {
                lblCustomerEmailAlert.setText("Email Already Exists!!");
                return true;
            }
        }
        return false;
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    private boolean validateCustomer() {
        boolean result = true;

        if (RegExPatterns.namePattern(txtCustomerName.getText())) {
            lblCustomerNameAlert.setText("Invalid Customer Name!!");
            result = false;
        }

        if (RegExPatterns.emailPattern(txtCustomerEmail.getText())) {
            lblCustomerEmailAlert.setText("Invalid Email Address!!");
            result = false;
        }

        if (RegExPatterns.contactNoPattern(txtContactNo.getText())) {
            lblContactNoAlert.setText("Invalid Contact Number!!");
            result = false;
        }
        return result;
    }

    @FXML
    void customerNameOnMouseClick(MouseEvent event) {
        lblCustomerNameAlert.setText(" ");
    }

    @FXML
    void contactNoOnMouseClick(MouseEvent event) {
        lblContactNoAlert.setText(" ");
    }

    @FXML
    void emailOnMouseClick(MouseEvent event) {
        lblCustomerEmailAlert.setText(" ");
    }

    @FXML
    void txtCustomerNameOnKeyPressed(KeyEvent event) {
        lblCustomerNameAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.namePattern(txtCustomerName.getText())) {
                lblCustomerNameAlert.setText("Invalid Customer Name!!");
                event.consume(); // Prevent the default behavior
            } else {
                // Move focus to the second TextField when Enter key is pressed
                txtContactNo.requestFocus();
            }
        }
    }

    @FXML
    void txtContactNoOnKeyPressed(KeyEvent event) throws SQLException {
        lblContactNoAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.contactNoPattern(txtContactNo.getText())) {
                lblContactNoAlert.setText("Invalid Contact Number!!");
                event.consume();
            } else {
                if (checkContactNoAvailability()) return;
                txtCustomerEmail.requestFocus();
            }
        }
    }

    @FXML
    void txtEmailOnKeyPressed(KeyEvent event) throws SQLException {
        lblCustomerEmailAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.emailPattern(txtCustomerEmail.getText())) {
                lblCustomerEmailAlert.setText("Invalid Email Address!!");
                event.consume();
            } else {
                if (checkEmailAvailability()) return;
                btnAddOnAction();
            }
        }
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

    @FXML
    void btnAddOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(AddBtnPane);
    }

    @FXML
    void btnAddOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(AddBtnPane);
    }
}
