package lk.grb.ceylonPottersPaletteLayered.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lk.grb.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.grb.ceylonPottersPaletteLayered.bo.custom.CustomerOrderBO;
import lk.grb.ceylonPottersPaletteLayered.dto.CustomerOrderDto;
import lk.grb.ceylonPottersPaletteLayered.util.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerOrderAddPopUpFormController implements Initializable {

    @FXML
    private Pane AddToCartBtnPane;

    @FXML
    private ImageView btnAddNewCustomerIconImg;

    @FXML
    private Pane btnAddNewCustomerPane;

    @FXML
    private Pane cancelBtnPane;

    @FXML
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    private JFXComboBox<String> cmbProductId;

    @FXML
    private Label lblCancel;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDescription;

    @FXML
    public Label lblNetTotal;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Pane placeOrderPanel;

    @FXML
    private TextField txtProductQty;

    @FXML
    private VBox vBoxCustomerOrder;

    @FXML
    private Label lblCmbCustomerIdAlert;

    @FXML
    private Label lblCmbProductIdAlert;

    @FXML
    private Label lblQtyAlert;

    CustomerOrderBO customerOrderBO =
            (CustomerOrderBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.CUSTOMER_ORDER);

    public static ArrayList<String[]> productList;
    ArrayList<String> idList;

    {
        try {
            idList = customerOrderBO.getAllCustomerOrderId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    double netTotal = 0;

    private static CustomerOrderAddPopUpFormController controller;

    public CustomerOrderAddPopUpFormController() {
        controller = this;
        productList = new ArrayList<>();
    }

    public static  CustomerOrderAddPopUpFormController getInstance() {
        return controller;
    }

    @FXML
    void btnAddNewCustomerOnAction(ActionEvent event) throws IOException {
        Navigation.closeOrderPopUpPane();
        Navigation.imgPopUpBackground("customerAddPopUpForm.fxml");
    }

    @FXML
    void btnAddToCartOnAction() {

        if (validateCustomerOrder()) {

            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i)[0].equals(String.valueOf(cmbProductId.getSelectionModel().getSelectedItem()))) {

                    int qty = Integer.parseInt(productList.get(i)[1]);
                    productList.get(i)[1] = String.valueOf(qty + Integer.parseInt(txtProductQty.getText()));

                    netTotal += customerOrderBO.getTotal(lblUnitPrice.getText(), txtProductQty.getText());
                    lblNetTotal.setText(String.valueOf(netTotal));

                    allCustomerOrderCartId();
                    txtProductQty.clear();
                    return;
                }
            }

            String[] products = {String.valueOf(cmbProductId.getSelectionModel().getSelectedItem()), txtProductQty.getText()};

            productList.add(products);

            allCustomerOrderCartId();

            netTotal += customerOrderBO.getTotal(lblUnitPrice.getText(), txtProductQty.getText());
            lblNetTotal.setText(String.valueOf(netTotal));

            txtProductQty.clear();
        }
    }

    private boolean validateCustomerOrder() {
        boolean result = true;

        if ((cmbCustomerId.getSelectionModel().getSelectedItem()) == null) {
            lblCmbCustomerIdAlert.setText("Please Select a Customer!!");
            result = false;
        }

        if ((cmbProductId.getSelectionModel().getSelectedItem()) == null) {
            lblCmbProductIdAlert.setText("Please Select a Product!!");
            result = false;
        }

        if (RegExPatterns.qtyOrUnitPricePattern(txtProductQty.getText())) {
            lblQtyAlert.setText("Enter the Product Quantity!!");
            result = false;
        }
        return result;
    }

    @FXML
    void txtQuantityOnKeyPressed(KeyEvent event) {
        lblQtyAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.qtyOrUnitPricePattern(txtProductQty.getText())) {
                lblQtyAlert.setText("Enter the Product Quantity!!");
                event.consume();
            } else {
                btnAddToCartOnAction();
            }
        }
    }

    @FXML
    void cmbCustomerIdOnKeyPressed(KeyEvent event) {
        lblCmbCustomerIdAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if ((cmbCustomerId.getSelectionModel().getSelectedItem()) == null) {
                lblCmbCustomerIdAlert.setText("Please Select a Customer!!");
                event.consume();
            } else {
                cmbProductId.requestFocus();
            }
        }
    }

    @FXML
    void cmbCmbProductIdOnKeyPressed(KeyEvent event) {
        lblCmbProductIdAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if ((cmbProductId.getSelectionModel().getSelectedItem()) == null) {
                lblCmbProductIdAlert.setText("Please Select a Product!!");
                event.consume();
            } else {
                txtProductQty.requestFocus();
            }
        }
    }

    @FXML
    void cmbProductIdOnMouseClick(MouseEvent event) {
        lblCmbProductIdAlert.setText(" ");
    }

    @FXML
    void txtQtyOnMouseClick(MouseEvent event) {
        lblQtyAlert.setText(" ");
    }

    @FXML
    void cmbCustomerIdOnMouseClick(MouseEvent event) {
        lblCmbCustomerIdAlert.setText(" ");
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closeOrderPopUpPane();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws SQLException {

        CustomerOrderDto customerOrderDto = new CustomerOrderDto();

        customerOrderDto.setCustomer_Order_Id(lblOrderId.getText());
        customerOrderDto.setCustomer_Id(cmbCustomerId.getSelectionModel().getSelectedItem());
        customerOrderDto.setTotal_Price(Double.parseDouble(lblNetTotal.getText()));
        customerOrderDto.setDate(DateTimeUtil.dateNow());
        customerOrderDto.setTime(DateTimeUtil.timeNow());
        customerOrderDto.setOrderList(productList);

        boolean isSaved = customerOrderBO.placeCustomerOrder(customerOrderDto);

        if (isSaved) {
            Navigation.closeOrderPopUpPane();
            CustomerOrderManageFormController.getInstance().allCustomerOrderId();
        }
        else {
            new Alert(Alert.AlertType.ERROR, "Unable to Save the ORDER!!!").show();
        }
    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) throws SQLException {
        lblCustomerName.setText(customerOrderBO.getCustomerName(cmbCustomerId.getSelectionModel().getSelectedItem()));
    }

    public void setCustomerDataInComboBox() throws SQLException {
        ArrayList<String> roles = customerOrderBO.getAllCustomerId();
        cmbCustomerId.getItems().addAll(roles);
    }

    @FXML
    void cmbProductIdOnAction(ActionEvent event) throws SQLException {
        for (int i = 0; i < productList.size(); i++) {
            if(cmbProductId.getSelectionModel().getSelectedItem().equals(productList.get(i)[0])){
                int qty = Integer.parseInt(customerOrderBO.getProductQtyOnHand(productList.get(i)[0]));
                int orderedQty = Integer.parseInt(productList.get(i)[1]);
                lblQtyOnHand.setText(String.valueOf(qty - orderedQty));
                lblUnitPrice.setText(customerOrderBO.getProductUnitPrice(productList.get(i)[0]));
                lblDescription.setText(customerOrderBO.getProductDescription(productList.get(i)[0]));
                return;
            }
        }
        lblDescription.setText(customerOrderBO.getProductDescription(cmbProductId.getSelectionModel().getSelectedItem()));
        lblUnitPrice.setText(customerOrderBO.getProductUnitPrice(cmbProductId.getSelectionModel().getSelectedItem()));
        lblQtyOnHand.setText(customerOrderBO.getProductQtyOnHand(cmbProductId.getSelectionModel().getSelectedItem()));
    }

    @FXML
    void btnPlaceOrderOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(placeOrderPanel);
    }

    @FXML
    void btnPlaceOrderOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(placeOrderPanel);
    }

    @FXML
    void btnCancelOnMouseEntered(MouseEvent event) {
        StyleUtil.cancelBtnSelected(cancelBtnPane, lblCancel);
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        StyleUtil.cancelBtnUnselected(cancelBtnPane, lblCancel);
    }

    @FXML
    void btnAddToCartOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(AddToCartBtnPane);
    }

    @FXML
    void btnAddToCartOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(AddToCartBtnPane);
    }

    @FXML
    void btnAddNewCustomerOnMouseEntered(MouseEvent event) {
        StyleUtil.addNewCustomerORSupplierBtnSelected(btnAddNewCustomerPane, btnAddNewCustomerIconImg);
    }

    @FXML
    void btnAddNewCustomerOnMouseExited(MouseEvent event) {
        StyleUtil.addNewCustomerORSupplierBtnUnselected(btnAddNewCustomerPane, btnAddNewCustomerIconImg);
    }

    public void setProductDataInComboBox() throws SQLException {
        ArrayList<String> roles = customerOrderBO.getAllProductId();
        cmbProductId.getItems().addAll(roles);
    }

    public void allCustomerOrderCartId() {

        vBoxCustomerOrder.getChildren().clear();

        for (int i = 0; i < productList.size(); i++) {
            loadDataTable(productList.get(i));
        }
    }

    private void loadDataTable(String[] id) {
        try {
            FXMLLoader loader = new FXMLLoader(CustomerOrderAddPopUpFormController.class.getResource("/view/customerOrderAddPopUpBarForm.fxml"));
            Parent root = loader.load();
            CustomerOrderAddPopUpBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxCustomerOrder.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblOrderId.setText(NewId.newId(idList, NewId.GetType.CUSTOMER_ORDER));
        lblOrderDate.setText(DateTimeUtil.dateNow());

        try {
            setCustomerDataInComboBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            setProductDataInComboBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
