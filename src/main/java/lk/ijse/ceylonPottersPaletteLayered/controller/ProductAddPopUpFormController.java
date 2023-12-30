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
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.ProductStockBO;
import lk.ijse.ceylonPottersPaletteLayered.dto.ProductStockDto;
import lk.ijse.ceylonPottersPaletteLayered.util.Navigation;
import lk.ijse.ceylonPottersPaletteLayered.util.NewId;
import lk.ijse.ceylonPottersPaletteLayered.util.RegExPatterns;
import lk.ijse.ceylonPottersPaletteLayered.util.StyleUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductAddPopUpFormController {

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
    private TextField txtCategory;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private Label lblCategoryAlert;

    @FXML
    private Label lblDescriptionAlert;

    @FXML
    private Label lblQtyAlert;

    @FXML
    private Label lblUnitPriceAlert;

    public static int producedTotalProductQuantity;

    ProductStockBO productStockBO =
            (ProductStockBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.PRODUCT_STOCK);

    @FXML
    void btnAddOnAction() throws SQLException {

        if(validateProduct()) {
            ProductStockDto productStockDto = new ProductStockDto();

            ArrayList<String> list = productStockBO.getAllProductId();

            productStockDto.setProduct_Id(NewId.newId(list, NewId.GetType.PRODUCT_STOCK));
            productStockDto.setDescription(txtDescription.getText());
            productStockDto.setQty_On_Hand(Integer.parseInt(txtQuantity.getText()));
            productStockDto.setUnit_Price(Double.parseDouble(txtUnitPrice.getText()));
            productStockDto.setCategory(txtCategory.getText());

            producedTotalProductQuantity += productStockDto.getQty_On_Hand();
            productStockDto.setQty(producedTotalProductQuantity);

            boolean saved = productStockBO.saveProduct(productStockDto);

            if (saved) {
                Navigation.closePane();
                ProductStockFormController.getInstance().allProductId();
            }
        }
    }

    private boolean validateProduct() {
        boolean result = true;

        if (RegExPatterns.namePattern(txtCategory.getText())) {
            lblCategoryAlert.setText("Invalid Category!!");
            result = false;
        }

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
                txtCategory.requestFocus();
            }
        }
    }

    @FXML
    void txtCategoryOnKeyPressed(KeyEvent event) {
        lblCategoryAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.namePattern(txtCategory.getText())) {
                lblCategoryAlert.setText("Invalid Category!!");
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
    void txtCategoryOnMouseClicked(MouseEvent event) {
        lblCategoryAlert.setText(" ");
    }

    @FXML
    void txtDescriptionOnMouseClicked(MouseEvent event) {
        lblDescriptionAlert.setText(" ");
    }

    @FXML
    void txtQtyOnMouseClicked(MouseEvent event) {
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
