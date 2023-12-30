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
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.CustomerOrderBO;
import lk.ijse.ceylonPottersPaletteLayered.util.Navigation;
import lk.ijse.ceylonPottersPaletteLayered.util.StyleUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class CustomerOrderManageFormController implements Initializable {

    @FXML
    private Pane btnAddOrderPane;

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
    private VBox vBoxCustomerOrders;

    private static CustomerOrderManageFormController controller;

    public CustomerOrderManageFormController() {
        controller = this;
    }

    public static  CustomerOrderManageFormController getInstance() {
        return controller;
    }

    CustomerOrderBO customerOrderBO =
            (CustomerOrderBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.CUSTOMER_ORDER);

    @FXML
    void btnAddOrderOnAction(ActionEvent event) throws IOException {
        Navigation.imgPopUpBackground("customerOrderAddPopUpForm.fxml");
    }

    @FXML
    void btnAddOrderOnMouseEntered(MouseEvent event) {
        StyleUtil.addBtnSelected(btnAddOrderPane, lblAddOrder, imgAdd);
    }

    @FXML
    void btnAddOrderOnMouseExited(MouseEvent event) {
        StyleUtil.addBtnUnselected(btnAddOrderPane, lblAddOrder, imgAdd);
    }

    @FXML
    void btnRefreshTableOnAction(ActionEvent event) {
        allCustomerOrderId();
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
    void btnSupplierOrdersOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "supplierOrderManageForm.fxml");
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

        ArrayList<String> allCustomerOrderId = customerOrderBO.getAllCustomerOrderId();

        for (String customerOrderId : allCustomerOrderId) {
            if (txtSearch.getText().equals(customerOrderId)) {
                CustomerOrderViewPopUpFormController.customerOrderId = txtSearch.getText();
                CustomerOrderViewPopUpFormController.customerId = customerOrderBO.getCustomerIdForOrder(txtSearch.getText());
                Navigation.imgPopUpBackground("customerOrderViewPopUpForm.fxml");
                lblSearchAlert.setText(" ");
                StyleUtil.searchBarTransparent(searchBarPane);
                txtSearch.clear();
                return;
            }

            ArrayList<String> customerIds = customerOrderBO.getCustomerId(customerOrderId);

            for (String customerId : customerIds) {
                if (txtSearch.getText().equals(customerOrderBO.getCustomerContactNo(customerId))) {
                    allSelectedCustomerOrderId(customerId);
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
        return Pattern.matches("(CO-0)\\d+", txtSearch.getText());
    }

    private boolean validateContactNo() {
        return Pattern.matches("[0-9]{10}", txtSearch.getText());
    }

    public void allSelectedCustomerOrderId(String id) throws SQLException {

        vBoxCustomerOrders.getChildren().clear();
        ArrayList<String> list = customerOrderBO.getSelectedAllCustomerOrderId(id);

        for (int i = 0; i < list.size(); i++) {
            loadDataTable(list.get(i));
        }
    }

    public void allCustomerOrderId() {

        vBoxCustomerOrders.getChildren().clear();
        ArrayList<String> list;
        try {
            list = customerOrderBO.getAllCustomerOrderId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (String customerOrderId : list) {
            loadDataTable(customerOrderId);
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(CustomerOrderManageFormController.class.getResource("/view/customerOrderManageBarForm.fxml"));
            Parent root = loader.load();
            CustomerOrderManageBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxCustomerOrders.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allCustomerOrderId();
    }
}
