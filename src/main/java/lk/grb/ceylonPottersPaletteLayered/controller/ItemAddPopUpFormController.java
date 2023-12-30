package lk.grb.ceylonPottersPaletteLayered.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.grb.ceylonPottersPaletteLayered.bo.custom.ItemStockBO;
import lk.grb.ceylonPottersPaletteLayered.dto.ItemStockDto;
import lk.grb.ceylonPottersPaletteLayered.util.Navigation;
import lk.grb.ceylonPottersPaletteLayered.util.NewId;
import lk.grb.ceylonPottersPaletteLayered.util.RegExPatterns;
import lk.grb.ceylonPottersPaletteLayered.util.StyleUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemAddPopUpFormController {

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
    private TextField txtDescription;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private Label lblDescriptionAlert;

    @FXML
    private Label lblQtyAlert;

    @FXML
    private Label lblUnitPriceAlert;

    ItemStockBO itemStockBO =
            (ItemStockBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.ITEM_STOCK);

    @FXML
    void btnAddOnAction() throws SQLException {

        if(validateItem()) {
            ItemStockDto itemStockDto = new ItemStockDto();

            ArrayList<String> list = itemStockBO.getAllItemId();

            itemStockDto.setItem_Id(NewId.newId(list, NewId.GetType.ITEM_STOCK));
            itemStockDto.setDescription(txtDescription.getText());
            itemStockDto.setUnit_Price(Double.parseDouble(txtUnitPrice.getText()));
            itemStockDto.setQty_On_Hand(Integer.parseInt(txtQuantity.getText()));

            boolean saved = itemStockBO.saveItem(itemStockDto);

            if (saved) {
                Navigation.closePane();
                ItemStockFormController.getInstance().allItemId();
            }
        }
    }

    private boolean validateItem() {
        boolean result = true;

        if (RegExPatterns.pricePattern(txtUnitPrice.getText())) {
            lblUnitPriceAlert.setText("Invalid Unit Price!!");
            result = false;
        }

        if (RegExPatterns.namePattern(txtDescription.getText())) {
            lblDescriptionAlert.setText("Invalid Description!!");
            result = false;
        }

        if (RegExPatterns.qtyPattern(txtQuantity.getText())) {
            lblQtyAlert.setText("Invalid Quantity!!");
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
            if (RegExPatterns.pricePattern(txtUnitPrice.getText())) {
                lblUnitPriceAlert.setText("Invalid Unit Price!!");
                event.consume();
            } else {
                txtQuantity.requestFocus();
            }
        }
    }

    @FXML
    void txtQuantityOnKeyPressed(KeyEvent event) throws SQLException {
        lblQtyAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.qtyPattern(txtQuantity.getText())) {
                lblQtyAlert.setText("Invalid Quantity!!");
                event.consume();
            } else {
                btnAddOnAction();
            }
        }
    }

    @FXML
    void txtDescriptionOnMouseClicked(MouseEvent event) {
        lblDescriptionAlert.setText(" ");
    }

    @FXML
    void txtQuantityOnMouseClicked(MouseEvent event) {
        lblQtyAlert.setText(" ");
    }

    @FXML
    void txtUnitPriceOnMouseClicked(MouseEvent event) {
        lblUnitPriceAlert.setText(" ");
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
