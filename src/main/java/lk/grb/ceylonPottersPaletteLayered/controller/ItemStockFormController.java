package lk.grb.ceylonPottersPaletteLayered.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lk.grb.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.grb.ceylonPottersPaletteLayered.bo.custom.ItemStockBO;
import lk.grb.ceylonPottersPaletteLayered.util.Navigation;
import lk.grb.ceylonPottersPaletteLayered.util.StyleUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ItemStockFormController implements Initializable {

    @FXML
    private Pane addItemPane;

    @FXML
    private Pane searchBarPane;

    @FXML
    private ImageView imgAdd;

    @FXML
    private Label lblSearchAlert;

    @FXML
    private Label lblAddItem;

    @FXML
    private TextField txtSearch;

    @FXML
    private VBox vBoxItemStock;

    private static ItemStockFormController controller;

    public ItemStockFormController() {
        controller = this;
    }

    public static ItemStockFormController getInstance() {
        return controller;
    }

    ItemStockBO itemStockBO =
            (ItemStockBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.ITEM_STOCK);

    @FXML
    void btnAddItemOnAction(ActionEvent event) throws IOException {
        Navigation.imgPopUpBackground("itemAddPopUpForm.fxml");
    }

    @FXML
    void btnAddItemOnMouseEntered(MouseEvent event) {
        StyleUtil.addBtnSelected(addItemPane,lblAddItem, imgAdd);
    }

    @FXML
    void btnAddItemOnMouseExited(MouseEvent event) {
        StyleUtil.addBtnUnselected(addItemPane, lblAddItem, imgAdd);
    }

    @FXML
    void btnProductStockOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "productStockForm.fxml");
    }

    @FXML
    void btnRepairStockOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "repairedStockForm.fxml");
    }

    @FXML
    void txtSearchOnMouseClicked(MouseEvent event) {
        lblSearchAlert.setText(" ");
        StyleUtil.searchBarTransparent(searchBarPane);
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws IOException, SQLException {

        if (!validateName()) {
            lblSearchAlert.setText("Wrong Name! Try again!!");
            StyleUtil.searchBarRed(searchBarPane);
            return;
        }

        ArrayList<String> allItemId = itemStockBO.getAllItemId();

        for (String itemId : allItemId) {
            if (txtSearch.getText().equals(itemStockBO.getItemDescription(itemId))) {
                ItemViewPopUpFormController.itemId = itemId;
                lblSearchAlert.setText(" ");
                StyleUtil.searchBarTransparent(searchBarPane);
                txtSearch.clear();
                Navigation.imgPopUpBackground("itemViewPopUpForm.fxml");
                return;
            }
        }
        lblSearchAlert.setText("Wrong Name! Try again!!");
        StyleUtil.searchBarRed(searchBarPane);
    }

    private boolean validateName() {
        return Pattern.matches("[A-Za-z\\s]{3,}", txtSearch.getText());
    }

    public void allItemId() {
        vBoxItemStock.getChildren().clear();
        ArrayList<String> list;
        try {
            list = itemStockBO.getAllItemId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (String itemId : list) {
            loadDataTable(itemId);
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(ItemStockFormController.class.getResource("/view/itemStockBarForm.fxml"));
            Parent root = loader.load();
            ItemStockBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxItemStock.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allItemId();
    }
}
