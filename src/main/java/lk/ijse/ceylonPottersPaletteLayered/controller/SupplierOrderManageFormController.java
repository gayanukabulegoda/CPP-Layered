package lk.ijse.ceylonPottersPaletteLayered.controller;

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
import lk.ijse.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.SupplierOrderBO;
import lk.ijse.ceylonPottersPaletteLayered.util.Navigation;
import lk.ijse.ceylonPottersPaletteLayered.util.StyleUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SupplierOrderManageFormController implements Initializable {

    @FXML
    private Pane addOrderPane;

    @FXML
    private Pane btnRefreshPane;

    @FXML
    private Pane searchBarPane;

    @FXML
    private ImageView imgAdd;

    @FXML
    private ImageView imgRefresh;

    @FXML
    private Label lblAddOrder;

    @FXML
    private Label lblSearchAlert;

    @FXML
    private TextField txtSearch;

    @FXML
    private VBox vBoxSupplierOrders;

    private static SupplierOrderManageFormController controller;

    public SupplierOrderManageFormController() {
        controller = this;
    }

    public static  SupplierOrderManageFormController getInstance() {
        return controller;
    }

    SupplierOrderBO supplierOrderBO =
            (SupplierOrderBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.SUPPLIER_ORDER);

    @FXML
    void btnAddOrderOnAction(ActionEvent event) throws IOException {
        Navigation.imgPopUpBackground("supplierOrderAddPopUpForm.fxml");
    }

    @FXML
    void btnCustomerOrdersOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "customerOrderManageForm.fxml");
    }

    @FXML
    void btnAddOrderOnMouseEntered(MouseEvent event) {
        StyleUtil.addBtnSelected(addOrderPane, lblAddOrder, imgAdd);
    }

    @FXML
    void btnAddOrderOnMouseExited(MouseEvent event) {
        StyleUtil.addBtnUnselected(addOrderPane, lblAddOrder, imgAdd);
    }

    @FXML
    void btnRefreshTableOnAction(ActionEvent event) {
        allSupplierOrderId();
    }

    @FXML
    void btnRefreshTableOnMouseEntered(MouseEvent event) {
        StyleUtil.refreshBtnSelected(btnRefreshPane, imgRefresh);
    }

    @FXML
    void btnRefreshTableOnMouseExited(MouseEvent event) {
        StyleUtil.refreshBtnUnselected(btnRefreshPane, imgRefresh);
    }

    @FXML
    void txtSearchOnMouseClicked(MouseEvent event) {
        lblSearchAlert.setText(" ");
        StyleUtil.searchBarTransparent(searchBarPane);
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws IOException, SQLException {

        if (!(validateId() | validateContactNo())) {
            lblSearchAlert.setText("Invalid Contact No Or Order ID!!");
            StyleUtil.searchBarRed(searchBarPane);
            return;
        }

        ArrayList<String> allSupplierOrderId = supplierOrderBO.getAllSupplierOrderId();

        for (String supplierOrderId : allSupplierOrderId) {
            if (txtSearch.getText().equals(supplierOrderId)) {
                SupplierOrderViewPopUpFormController.supplierOrderId = txtSearch.getText();
                SupplierOrderViewPopUpFormController.supplierId = supplierOrderBO.getSupplierIdForOrder(txtSearch.getText());
                lblSearchAlert.setText(" ");
                StyleUtil.searchBarTransparent(searchBarPane);
                Navigation.imgPopUpBackground("supplierOrderViewPopUpForm.fxml");
                txtSearch.clear();
                return;
            }

            ArrayList<String> supplierIds = supplierOrderBO.getSupplierId(supplierOrderId);

            for (String supplierId : supplierIds) {
                if (txtSearch.getText().equals(supplierOrderBO.getSupplierContactNo(supplierId))) {
                    allSelectedSupplierOrderId(supplierId);
                    lblSearchAlert.setText(" ");
                    StyleUtil.searchBarTransparent(searchBarPane);
                    txtSearch.clear();
                    return;
                }
            }
        }
        lblSearchAlert.setText("Invalid Contact No Or Order ID!!");
        StyleUtil.searchBarRed(searchBarPane);
    }

    private boolean validateId() {
        return Pattern.matches("(SO-0)\\d+", txtSearch.getText());
    }

    private boolean validateContactNo() {
        return Pattern.matches("[0-9]{10}", txtSearch.getText());
    }

    public void allSelectedSupplierOrderId(String id) throws SQLException {
        vBoxSupplierOrders.getChildren().clear();
        ArrayList<String> list = supplierOrderBO.getSelectedAllSupplierOrderId(id);

        for (String supplierOrderId : list) {
            loadDataTable(supplierOrderId);
        }
    }

    public void allSupplierOrderId() {
        vBoxSupplierOrders.getChildren().clear();
        ArrayList<String> list;

        try {
            list = supplierOrderBO.getAllSupplierOrderId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (String supplierOrderId : list) {
            loadDataTable(supplierOrderId);
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(SupplierOrderManageFormController.class.getResource("/view/supplierOrderManageBarForm.fxml"));
            Parent root = loader.load();
            SupplierOrderManageBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxSupplierOrders.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allSupplierOrderId();
    }
}
