package lk.ijse.ceylonPottersPaletteLayered.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.ijse.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.ItemStockBO;
import lk.ijse.ceylonPottersPaletteLayered.util.Navigation;
import lk.ijse.ceylonPottersPaletteLayered.dto.ItemStockDto;
import lk.ijse.ceylonPottersPaletteLayered.util.StyleUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemViewPopUpFormController implements Initializable {

    @FXML
    private Pane btnClosePane;

    @FXML
    private Pane closeIconPane;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblItemId;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblUnitPrice;

    public static String itemId;

    ItemStockBO itemStockBO =
            (ItemStockBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.ITEM_STOCK);

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
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
    void btnCloseOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(btnClosePane);
    }

    @FXML
    void btnCloseOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(btnClosePane);
    }

    public void setData() {
        ItemStockDto itemStockDto;

        try {
            itemStockDto = itemStockBO.getItemData(itemId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        lblItemId.setText(itemStockDto.getItem_Id());
        lblDescription.setText(itemStockDto.getDescription());
        lblUnitPrice.setText(String.valueOf(itemStockDto.getUnit_Price()));
        lblQtyOnHand.setText(String.valueOf(itemStockDto.getQty_On_Hand()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }
}
