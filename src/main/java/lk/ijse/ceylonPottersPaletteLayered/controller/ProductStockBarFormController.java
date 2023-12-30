package lk.ijse.ceylonPottersPaletteLayered.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.ProductStockBO;
import lk.ijse.ceylonPottersPaletteLayered.util.Navigation;
import lk.ijse.ceylonPottersPaletteLayered.dto.ProductStockDto;
import lk.ijse.ceylonPottersPaletteLayered.util.StyleUtil;

import java.io.IOException;
import java.sql.SQLException;

public class ProductStockBarFormController {

    @FXML
    private Text category;

    @FXML
    private ImageView deleteImg;

    @FXML
    private Text description;

    @FXML
    private Text id;

    @FXML
    private Text quantity;

    @FXML
    private Text unitPrice;

    @FXML
    private ImageView updateImg;

    @FXML
    private ImageView viewImg;

    ProductStockBO productStockBO =
            (ProductStockBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.PRODUCT_STOCK);

    @FXML
    void deleteOnMouseClick(MouseEvent event) throws IOException {
        ConfirmationPopUpFormController.setId(id.getText());
        Navigation.imgPopUpBackground("confirmationPopUpForm.fxml");
    }

    @FXML
    void deleteOnMouseEntered(MouseEvent event) {
        StyleUtil.deleteImgSelected(deleteImg);
    }

    @FXML
    void deleteOnMouseExited(MouseEvent event) {
        StyleUtil.deleteImgUnselected(deleteImg);
    }

    @FXML
    void updateOnMouseClick(MouseEvent event) throws IOException {
        ProductUpdatePopUpFormController.productId = id.getText();
        Navigation.imgPopUpBackground("productUpdatePopUpForm.fxml");
    }

    @FXML
    void updateOnMouseEntered(MouseEvent event) {
        StyleUtil.updateImgSelected(updateImg);
    }

    @FXML
    void updateOnMouseExited(MouseEvent event) {
        StyleUtil.updateImgUnselected(updateImg);
    }

    @FXML
    void viewDetailsOnMouseClick(MouseEvent event) throws IOException {
        ProductViewPopUpFormController.productId = id.getText();
        Navigation.imgPopUpBackground("productViewPopUpForm.fxml");
    }

    @FXML
    void viewOnMouseEntered(MouseEvent event) {
        StyleUtil.viewImgSelected(viewImg);
    }

    @FXML
    void viewOnMouseExited(MouseEvent event) {
        StyleUtil.viewImgUnselected(viewImg);
    }

    public void setData(String id) {
        try {
            ProductStockDto productStockDto = productStockBO.getProductData(id);

            this.id.setText(productStockDto.getProduct_Id());
            description.setText(productStockDto.getDescription());
            unitPrice.setText(String.valueOf(productStockDto.getUnit_Price()));
            quantity.setText(String.valueOf(productStockDto.getQty_On_Hand()));
            category.setText(productStockDto.getCategory());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
