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
import lk.grb.ceylonPottersPaletteLayered.bo.custom.ProductStockBO;
import lk.grb.ceylonPottersPaletteLayered.util.Navigation;
import lk.grb.ceylonPottersPaletteLayered.util.StyleUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ProductStockFormController implements Initializable {

    @FXML
    private Pane addProductPane;

    @FXML
    private Pane searchBarPane;

    @FXML
    private ImageView imgAdd;

    @FXML
    private Label lblSearchAlert;

    @FXML
    private Label lblAddProduct;

    @FXML
    private TextField txtSearch;

    @FXML
    private VBox vBoxProductStock;

    private static ProductStockFormController controller;

    public ProductStockFormController() {
        controller = this;
    }

    public static ProductStockFormController getInstance() {
        return controller;
    }

    ProductStockBO productStockBO =
            (ProductStockBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.PRODUCT_STOCK);

    @FXML
    void btnAddProductOnAction(ActionEvent event) throws IOException {
        Navigation.imgPopUpBackground("productAddPopUpForm.fxml");
    }

    @FXML
    void btnAddProductOnMouseEntered(MouseEvent event) {
        StyleUtil.addBtnSelected(addProductPane, lblAddProduct, imgAdd);
    }

    @FXML
    void btnAddProductOnMouseExited(MouseEvent event) {
        StyleUtil.addBtnUnselected(addProductPane, lblAddProduct, imgAdd);
    }

    @FXML
    void btnItemStockOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "itemStockForm.fxml");
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

        ArrayList<String> allProductId = productStockBO.getAllProductId();

        for (String productId : allProductId) {
            if (txtSearch.getText().equals(productStockBO.getProductDescription(productId))) {
                ProductViewPopUpFormController.productId = productId;
                txtSearch.clear();
                lblSearchAlert.setText(" ");
                StyleUtil.searchBarTransparent(searchBarPane);
                Navigation.imgPopUpBackground("productViewPopUpForm.fxml");
                return;
            }
        }
        lblSearchAlert.setText("Wrong Name! Try again!!");
        StyleUtil.searchBarRed(searchBarPane);
    }

    private boolean validateName() {
        return Pattern.matches("[A-Za-z\\s]{3,}", txtSearch.getText());
    }

    public void allProductId() {
        vBoxProductStock.getChildren().clear();
        ArrayList<String> list;

        try {
            list = productStockBO.getAllProductId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (String productId : list) {
            loadDataTable(productId);
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(ProductStockFormController.class.getResource("/view/productStockBarForm.fxml"));
            Parent root = loader.load();
            ProductStockBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxProductStock.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allProductId();
    }
}
