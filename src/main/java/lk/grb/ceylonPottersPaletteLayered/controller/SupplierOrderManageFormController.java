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
import lk.grb.ceylonPottersPalette.model.SupplierModel;
import lk.grb.ceylonPottersPalette.model.SupplierOrderModel;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.StyleUtil;

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
        try {
            allSupplierOrderId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

        SupplierOrderModel supplierOrderModel = new SupplierOrderModel();
        ArrayList<String> allSupplierOrderId = supplierOrderModel.getAllSupplierOrderId();
        SupplierModel supplierModel = new SupplierModel();

        for (int i = 0; i < allSupplierOrderId.size(); i++) {
            if (txtSearch.getText().equals(allSupplierOrderId.get(i))) {
                SupplierOrderViewPopUpFormController.supplierOrderId = txtSearch.getText();
                SupplierOrderViewPopUpFormController.supplierId = supplierOrderModel.getSupplierIdForOrder(txtSearch.getText());
                lblSearchAlert.setText(" ");
                StyleUtil.searchBarTransparent(searchBarPane);
                Navigation.imgPopUpBackground("supplierOrderViewPopUpForm.fxml");
                txtSearch.clear();
                return;
            }

            ArrayList<String> supplierIds = supplierOrderModel.getSupplierId(allSupplierOrderId.get(i));

            for (int j = 0; j < supplierIds.size(); j++) {
                if (txtSearch.getText().equals(supplierModel.getSupplierContactNo(supplierIds.get(j)))) {
                    allSelectedSupplierOrderId(supplierIds.get(j));
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
        SupplierOrderModel supplierOrderModel = new SupplierOrderModel();
        ArrayList<String> list = supplierOrderModel.getSelectedAllSupplierOrderId(id);

        for (int i = 0; i < list.size(); i++) {
            loadDataTable(list.get(i));
        }
    }

    public void allSupplierOrderId() throws SQLException {

        vBoxSupplierOrders.getChildren().clear();
        SupplierOrderModel supplierOrderModel = new SupplierOrderModel();
        ArrayList<String> list = supplierOrderModel.getAllSupplierOrderId();

        for (int i = 0; i < list.size(); i++) {
            loadDataTable(list.get(i));
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
        try {
            allSupplierOrderId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
