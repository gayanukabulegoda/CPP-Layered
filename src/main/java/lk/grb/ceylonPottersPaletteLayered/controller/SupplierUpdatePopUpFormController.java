package lk.grb.ceylonPottersPaletteLayered.controller;

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
import lk.grb.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.grb.ceylonPottersPaletteLayered.bo.custom.SupplierBO;
import lk.grb.ceylonPottersPaletteLayered.dto.SupplierDto;
import lk.grb.ceylonPottersPaletteLayered.util.Navigation;
import lk.grb.ceylonPottersPaletteLayered.util.RegExPatterns;
import lk.grb.ceylonPottersPaletteLayered.util.StyleUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SupplierUpdatePopUpFormController implements Initializable {

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
    private Pane updateBtnPane;

    @FXML
    private Label lblContactNoAlert;

    @FXML
    private Label lblSupplierEmailAlert;

    @FXML
    private Label lblSupplierNameAlert;

    public static String supplierId;

    SupplierBO supplierBO =
            (SupplierBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.SUPPLIER);

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

        if(validateSupplier()) {
            SupplierDto supplierDto = new SupplierDto();

            supplierDto.setSupplier_Id(supplierId);
            supplierDto.setName(txtSupplierName.getText());
            supplierDto.setContact_No(txtContactNo.getText());
            supplierDto.setEmail(txtSupplierEmail.getText());

            boolean updated = supplierBO.updateSupplier(supplierDto);

            if (updated) {
                Navigation.closePane();
                SupplierManageFormController.getInstance().allSupplierId();
            }
        }
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
    void txtContactNoOnKeyPressed(KeyEvent event) {
        lblContactNoAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.contactNoPattern(txtContactNo.getText())) {
                lblContactNoAlert.setText("Invalid Contact Number!!");
                event.consume();
            } else {
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
                btnUpdateOnAction();
            }
        }
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
    void btnUpdateOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(updateBtnPane);
    }

    @FXML
    void btnUpdateOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(updateBtnPane);
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

    public void setData() {
        try {
            SupplierDto supplierDto = supplierBO.getSupplierData(supplierId);

            txtSupplierName.setText(supplierDto.getName());
            txtContactNo.setText(supplierDto.getContact_No());
            txtSupplierEmail.setText(supplierDto.getEmail());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }
}
