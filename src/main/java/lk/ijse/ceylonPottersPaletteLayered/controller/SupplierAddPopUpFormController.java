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
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.SupplierBO;
import lk.ijse.ceylonPottersPaletteLayered.dto.SupplierDto;
import lk.ijse.ceylonPottersPaletteLayered.util.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierAddPopUpFormController {

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
    private TextField txtSupplierEmail;

    @FXML
    private TextField txtSupplierName;

    @FXML
    private Label lblContactNoAlert;

    @FXML
    private Label lblSupplierEmailAlert;

    @FXML
    private Label lblSupplierNameAlert;

    SupplierBO supplierBO =
            (SupplierBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.SUPPLIER);

    @FXML
    void btnAddOnAction() throws SQLException {
        if(validateSupplier()) {
            SupplierDto supplierDto = new SupplierDto();

            ArrayList<String> list = supplierBO.getAllSupplierId();

            if (checkContactNoAvailability()) return;
            if (checkEmailAvailability()) return;

            supplierDto.setSupplier_Id(NewId.newId(list, NewId.GetType.SUPPLIER));
            supplierDto.setName(txtSupplierName.getText());
            supplierDto.setEmail(txtSupplierEmail.getText());
            supplierDto.setContact_No(txtContactNo.getText());
            supplierDto.setTime(DateTimeUtil.timeNow());
            supplierDto.setDate(DateTimeUtil.dateNow());
            supplierDto.setUser_Name(GlobalFormController.user);

            if (supplierBO.saveSupplier(supplierDto)) {
                Navigation.closePane();
                SupplierManageFormController.getInstance().allSupplierId();
            }
        }
    }

    private boolean checkContactNoAvailability() throws SQLException {
        ArrayList<String> allSupplierContactNumbers = supplierBO.getAllSupplierContactNumbers();

        for (String contactNo : allSupplierContactNumbers) {
            if (txtContactNo.getText().equals(contactNo)) {
                lblContactNoAlert.setText("Contact Number Already Exists!!");
                return true;
            }
        }
        return false;
    }

    private boolean checkEmailAvailability() throws SQLException {
        ArrayList<String> allSupplierEmails = supplierBO.getAllSupplierEmails();

        for (String email : allSupplierEmails) {
            if (txtSupplierEmail.getText().equals(email)) {
                lblSupplierEmailAlert.setText("Email Already Exists!!");
                return true;
            }
        }
        return false;
    }

    private boolean validateSupplier() {
        boolean result = true;

        if (RegExPatterns.namePattern(txtSupplierName.getText())) {
            lblSupplierNameAlert.setText("Invalid Supplier Name!!");
            result = false;
        }

        if (RegExPatterns.contactNoPattern(txtContactNo.getText())) {
            lblContactNoAlert.setText("Invalid Contact Number!!");
            result = false;
        }

        if (RegExPatterns.emailPattern(txtSupplierEmail.getText())) {
            lblSupplierEmailAlert.setText("Invalid Email Address!!");
            result = false;
        }
        return result;
    }

    @FXML
    void txtContactNoOnMouseClicked(MouseEvent event) {
        lblContactNoAlert.setText(" ");
    }

    @FXML
    void txtSupplierEmailOnMouseClicked(MouseEvent event) {
        lblSupplierEmailAlert.setText(" ");
    }

    @FXML
    void txtSupplierNameOnMouseClicked(MouseEvent event) {
        lblSupplierNameAlert.setText(" ");
    }

    @FXML
    void txtSupplierNameOnKeyPressed(KeyEvent event) {
        lblSupplierNameAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.namePattern(txtSupplierName.getText())) {
                lblSupplierNameAlert.setText("Invalid Supplier Name!!");
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
                if (checkContactNoAvailability()) return;
                txtSupplierEmail.requestFocus();
            }
        }
    }

    @FXML
    void txtEmailOnKeyPressed(KeyEvent event) throws SQLException {
        lblSupplierEmailAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.emailPattern(txtSupplierEmail.getText())) {
                lblSupplierEmailAlert.setText("Invalid Email Address!!");
                event.consume();
            } else {
                if (checkEmailAvailability()) return;
                btnAddOnAction();
            }
        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnAddOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(AddBtnPane);
    }

    @FXML
    void btnAddOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(AddBtnPane);
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
