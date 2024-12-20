package lk.ijse.ceylonPottersPaletteLayered.controller;

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
import lk.ijse.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.CustomerBO;
import lk.ijse.ceylonPottersPaletteLayered.dto.CustomerDto;
import lk.ijse.ceylonPottersPaletteLayered.util.Navigation;
import lk.ijse.ceylonPottersPaletteLayered.util.RegExPatterns;
import lk.ijse.ceylonPottersPaletteLayered.util.StyleUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerUpdatePopUpFormController implements Initializable {

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
    private Pane updateBtnPane;

    @FXML
    private Label lblContactNoAlert;

    @FXML
    private Label lblCustomerEmailAlert;

    @FXML
    private Label lblCustomerNameAlert;

    public static String customerId;

    private String previousContactNo;
    private String previousEmail;

    CustomerBO customerBO =
            (CustomerBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.CUSTOMER);

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnUpdateOnAction() throws SQLException {
        if (isUpdatedContactNoValid()) return;
        if (isUpdatedEmailValid()) return;

        if (validateCustomer()) {
            CustomerDto customerDto = new CustomerDto();

            customerDto.setCustomer_Id(CustomerUpdatePopUpFormController.customerId);
            customerDto.setName(txtCustomerName.getText());
            customerDto.setContact_No(txtContactNo.getText());
            customerDto.setEmail(txtCustomerEmail.getText());
            customerDto.setDate(customerDto.getDate());
            customerDto.setTime(customerDto.getTime());
            customerDto.setUser_Name(customerDto.getUser_Name());

            if (customerBO.updateCustomer(customerDto)) {
                Navigation.closePane();
                CustomerManageFormController.getInstance().allCustomerId();
            }
        }
    }

    private boolean isUpdatedContactNoValid() throws SQLException {
        if (previousContactNo.equals(txtContactNo.getText())) return false;
        return checkContactNoAvailability();
    }

    private boolean isUpdatedEmailValid() throws SQLException {
        if (previousEmail.equals(txtCustomerEmail.getText())) return false;
        return checkEmailAvailability();
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

    public void setData() {
        try {
            CustomerDto customerDto = customerBO.getCustomerData(customerId);

            txtCustomerName.setText(customerDto.getName());
            txtContactNo.setText(customerDto.getContact_No());
            txtCustomerEmail.setText(customerDto.getEmail());

            previousContactNo = customerDto.getContact_No();
            previousEmail = customerDto.getEmail();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
    void txtCustomerNameOnKeyPressed(KeyEvent event) {
        lblCustomerNameAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.namePattern(txtCustomerName.getText())) {
                lblCustomerNameAlert.setText("Invalid Customer Name!!");
                event.consume();
            } else {
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
                if (isUpdatedContactNoValid()) return;
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
                if (isUpdatedEmailValid()) return;
                btnUpdateOnAction();
            }
        }
    }

    @FXML
    void contactNoOnMouseClick(MouseEvent event) {
        lblContactNoAlert.setText(" ");
    }

    @FXML
    void customerNameOnMouseClick(MouseEvent event) {
        lblCustomerNameAlert.setText(" ");
    }

    @FXML
    void emailOnMouseClick(MouseEvent event) {
        lblCustomerEmailAlert.setText(" ");
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
    void btnUpdateOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(updateBtnPane);
    }

    @FXML
    void btnUpdateOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(updateBtnPane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }
}
