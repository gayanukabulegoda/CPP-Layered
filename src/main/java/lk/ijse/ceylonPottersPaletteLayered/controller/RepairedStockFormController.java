package lk.ijse.ceylonPottersPaletteLayered.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lk.ijse.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.RepairStockBO;
import lk.ijse.ceylonPottersPaletteLayered.util.Navigation;
import lk.ijse.ceylonPottersPaletteLayered.util.StyleUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RepairedStockFormController implements Initializable {

    @FXML
    private Pane searchBarPane;

    @FXML
    private Label lblSearchAlert;

    @FXML
    private TextField txtSearch;

    @FXML
    private VBox vBoxRepairStock;

    private static RepairedStockFormController controller;

    public RepairedStockFormController() {
        controller = this;
    }

    public static RepairedStockFormController getInstance() {
        return controller;
    }

    RepairStockBO repairStockBO =
            (RepairStockBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.REPAIR_STOCK);

    @FXML
    void btnItemStockOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "itemStockForm.fxml");
    }

    @FXML
    void btnProductStockOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "productStockForm.fxml");
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

        ArrayList<String> allProductId = repairStockBO.getAllProductId();

        for (String productId : allProductId) {
            if (txtSearch.getText().equals(repairStockBO.getProductDescription(productId))) {
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

    public void allRepairedProductId() {
        vBoxRepairStock.getChildren().clear();
        ArrayList<String> list;

        try {
            list = repairStockBO.getAllProductId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (String productId : list) {
            loadDataTable(productId);
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(RepairedStockFormController.class.getResource("/view/repairedStockBarForm.fxml"));
            Parent root = loader.load();
            RepairedStockBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxRepairStock.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allRepairedProductId();
    }
}
