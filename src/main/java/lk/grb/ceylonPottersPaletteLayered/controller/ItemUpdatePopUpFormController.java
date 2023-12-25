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
import lk.grb.ceylonPottersPaletteLayered.dto.ItemStockDto;
import lk.grb.ceylonPottersPaletteLayered.model.ItemStockModel;
import lk.grb.ceylonPottersPaletteLayered.util.Navigation;
import lk.grb.ceylonPottersPaletteLayered.util.RegExPatterns;
import lk.grb.ceylonPottersPaletteLayered.util.StyleUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemUpdatePopUpFormController implements Initializable {

    @FXML
    private Pane cancelBtnPane;

    @FXML
    private Pane closeIconPane;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblCancel;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private Pane updateBtnPane;

    @FXML
    private Label lblDescriptionAlert;

    @FXML
    private Label lblQuantityAlert;

    @FXML
    private Label lblUnitPriceAlert;

    public static String itemId;

    ItemStockModel itemStockModel = new ItemStockModel();

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

        if(validateItem()) {
            ItemStockDto itemStockDto = new ItemStockDto();

            itemStockDto.setItem_Id(itemId);
            itemStockDto.setDescription(txtDescription.getText());
            itemStockDto.setUnit_Price(Double.parseDouble(txtUnitPrice.getText()));
            itemStockDto.setQty_On_Hand(Integer.parseInt(txtQuantity.getText()));

            boolean updated = itemStockModel.updateFromPopUp(itemStockDto);

            if (updated) {
                Navigation.closePane();
                ItemStockFormController.getInstance().allItemId();
            }
        }
    }

    private boolean validateItem() {
        boolean result = true;

        if (RegExPatterns.qtyOrUnitPricePattern(txtUnitPrice.getText())) {
            lblUnitPriceAlert.setText("Invalid Unit Price!!");
            result = false;
        }

        if (RegExPatterns.namePattern(txtDescription.getText())) {
            lblDescriptionAlert.setText("Invalid Description!!");
            result = false;
        }

        if (RegExPatterns.qtyOrUnitPricePattern(txtQuantity.getText())) {
            lblQuantityAlert.setText("Invalid Quantity!!");
            result = false;
        }
        return result;
    }

    @FXML
    void txtDescriptionOnKeyPressed(KeyEvent event) {
        lblDescriptionAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.namePattern(txtDescription.getText())) {
                lblDescriptionAlert.setText("Invalid Description!!");
                event.consume();
            } else {
                txtUnitPrice.requestFocus();
            }
        }
    }

    @FXML
    void txtUnitPriceOnKeyPressed(KeyEvent event) {
        lblUnitPriceAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.qtyOrUnitPricePattern(txtUnitPrice.getText())) {
                lblUnitPriceAlert.setText("Invalid Unit Price!!");
                event.consume();
            } else {
                txtQuantity.requestFocus();
            }
        }
    }

    @FXML
    void txtQuantityOnKeyPressed(KeyEvent event) throws SQLException {
        lblQuantityAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.qtyOrUnitPricePattern(txtQuantity.getText())) {
                lblQuantityAlert.setText("Invalid Quantity!!");
                event.consume();
            } else {
                btnUpdateOnAction();
            }
        }
    }

    @FXML
    void txtDescriptionOnMouseClicked(MouseEvent event) {
        lblDescriptionAlert.setText(" ");
    }

    @FXML
    void txtQuantityOnMouseClicked(MouseEvent event) {
        lblQuantityAlert.setText(" ");
    }

    @FXML
    void txtUnitPriceOnMouseClicked(MouseEvent event) {
        lblUnitPriceAlert.setText(" ");
    }

    public void setData() {
        try {
            ItemStockDto itemStockDto = itemStockModel.getData(itemId);

            txtDescription.setText(itemStockDto.getDescription());
            txtQuantity.setText(String.valueOf(itemStockDto.getQty_On_Hand()));
            txtUnitPrice.setText(String.valueOf(itemStockDto.getUnit_Price()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }
}
