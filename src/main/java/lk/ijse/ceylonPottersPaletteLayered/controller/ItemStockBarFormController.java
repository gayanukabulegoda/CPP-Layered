package lk.ijse.ceylonPottersPaletteLayered.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.ItemStockBO;
import lk.ijse.ceylonPottersPaletteLayered.util.Navigation;
import lk.ijse.ceylonPottersPaletteLayered.dto.ItemStockDto;
import lk.ijse.ceylonPottersPaletteLayered.util.StyleUtil;

import java.io.IOException;
import java.sql.SQLException;

public class ItemStockBarFormController {

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

    ItemStockBO itemStockBO =
            (ItemStockBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.ITEM_STOCK);

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
        ItemUpdatePopUpFormController.itemId = id.getText();
        Navigation.imgPopUpBackground("itemUpdatePopUpForm.fxml");
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
        ItemViewPopUpFormController.itemId = id.getText();
        Navigation.imgPopUpBackground("itemViewPopUpForm.fxml");
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
            ItemStockDto itemStockDto = itemStockBO.getItemData(id);

            this.id.setText(itemStockDto.getItem_Id());
            description.setText(itemStockDto.getDescription());
            unitPrice.setText(String.valueOf(itemStockDto.getUnit_Price()));
            quantity.setText(String.valueOf(itemStockDto.getQty_On_Hand()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
