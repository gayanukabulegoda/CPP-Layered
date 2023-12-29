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
import lk.grb.ceylonPottersPaletteLayered.bo.custom.SupplierOrderBO;
import lk.grb.ceylonPottersPaletteLayered.dto.SupplierOrderDto;
import lk.grb.ceylonPottersPaletteLayered.util.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupplierOrderAddPopUpFormController implements Initializable {

    @FXML
    private Pane AddToCartBtnPane;

    @FXML
    private ImageView btnAddNewSupplierIconImg;

    @FXML
    private Pane btnAddNewSupplierPane;

    @FXML
    private Pane cancelBtnPane;

    @FXML
    private JFXComboBox<String> cmbItemId;

    @FXML
    private JFXComboBox<String> cmbSupplierId;

    @FXML
    private Label lblCancel;

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
    private Label lblSupplierName;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Pane placeOrderPanel;

    @FXML
    private TextField txtItemQty;

    @FXML
    private VBox vBoxCustomerOrder;

    @FXML
    private Label lblQtyAlert;

    @FXML
    private Label lblItemIdAlert;

    @FXML
    private Label lblCmbSupplierAlert;

    public static ArrayList<String[]> itemList;

    SupplierOrderBO supplierOrderBO =
            (SupplierOrderBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.SUPPLIER_ORDER);

    ArrayList<String> idList;

    {
        try {
            idList = supplierOrderBO.getAllSupplierOrderId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    double netTotal = 0;

    private static SupplierOrderAddPopUpFormController controller;

    public SupplierOrderAddPopUpFormController() {
        controller = this;
        itemList = new ArrayList<>();
    }

    public static  SupplierOrderAddPopUpFormController getInstance() {
        return controller;
    }

    @FXML
    void btnAddNewSupplierOnAction(ActionEvent event) throws IOException {
        Navigation.closeOrderPopUpPane();
        Navigation.imgPopUpBackground("supplierAddPopUpForm.fxml");
    }

    @FXML
    void btnAddToCartOnAction() {

        if (validateSupplierOrder()) {

            for (int i = 0; i < itemList.size(); i++) {
                if (itemList.get(i)[0].equals(String.valueOf(cmbItemId.getSelectionModel().getSelectedItem()))) {

                    int qty = Integer.parseInt(itemList.get(i)[1]);
                    itemList.get(i)[1] = String.valueOf(qty + Integer.parseInt(txtItemQty.getText()));

                    netTotal += supplierOrderBO.getTotal(lblUnitPrice.getText(), txtItemQty.getText());
                    lblNetTotal.setText(String.valueOf(netTotal));

                    allSupplierOrderCartId();
                    txtItemQty.clear();
                    return;
                }
            }

            String[] items = {String.valueOf(cmbItemId.getSelectionModel().getSelectedItem()), txtItemQty.getText()};

            itemList.add(items);

            allSupplierOrderCartId();

            netTotal += supplierOrderBO.getTotal(lblUnitPrice.getText(), txtItemQty.getText());
            lblNetTotal.setText(String.valueOf(netTotal));

            txtItemQty.clear();
        }
    }

    private boolean validateSupplierOrder() {
        boolean result = true;

        if ((cmbSupplierId.getSelectionModel().getSelectedItem()) == null) {
            lblCmbSupplierAlert.setText("Please Select a Supplier!!");
            result = false;
        }

        if ((cmbItemId.getSelectionModel().getSelectedItem()) == null) {
            lblItemIdAlert.setText("Please Select an Item!!");
            result = false;
        }

        if (RegExPatterns.qtyOrUnitPricePattern(txtItemQty.getText())) {
            lblQtyAlert.setText("Enter the Item Quantity!!");
            result = false;
        }
        return result;
    }

    @FXML
    void txtQuantityOnKeyPressed(KeyEvent event) {
        lblQtyAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.qtyOrUnitPricePattern(txtItemQty.getText())) {
                lblQtyAlert.setText("Invalid Quantity!!");
                event.consume();
            } else {
                btnAddToCartOnAction();
            }
        }
    }

    @FXML
    void cmbSupplierIdOnKeyPressed(KeyEvent event) {
        lblCmbSupplierAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if ((cmbSupplierId.getSelectionModel().getSelectedItem()) == null) {
                lblCmbSupplierAlert.setText("Please Select a Supplier!!");
                event.consume();
            } else {
                cmbItemId.requestFocus();
            }
        }
    }

    @FXML
    void cmbCmbItemIdOnKeyPressed(KeyEvent event) {
        lblItemIdAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if ((cmbItemId.getSelectionModel().getSelectedItem()) == null) {
                lblItemIdAlert.setText("Please Select an Item!!");
                event.consume();
            } else {
                txtItemQty.requestFocus();
            }
        }
    }

    @FXML
    void cmbSupplierIdOnMouseClicked(MouseEvent event) {
        lblCmbSupplierAlert.setText(" ");
    }

    @FXML
    void txtItemQtyOnMouseClicked(MouseEvent event) {
        lblQtyAlert.setText(" ");
    }

    @FXML
    void cmbItemIdOnMouseClicked(MouseEvent event) {
        lblItemIdAlert.setText(" ");
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closeOrderPopUpPane();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws SQLException {

        SupplierOrderDto supplierOrderDto = new SupplierOrderDto();

        supplierOrderDto.setSupplier_Order_Id(lblOrderId.getText());
        supplierOrderDto.setSupplier_Id(cmbSupplierId.getSelectionModel().getSelectedItem());
        supplierOrderDto.setTotal_Price(Double.parseDouble(lblNetTotal.getText()));
        supplierOrderDto.setDate(DateTimeUtil.dateNow());
        supplierOrderDto.setTime(DateTimeUtil.timeNow());
        supplierOrderDto.setOrderList(itemList);

        boolean isSaved = supplierOrderBO.placeSupplierOrder(supplierOrderDto);

        if (isSaved) {
            Navigation.closeOrderPopUpPane();
            SupplierOrderManageFormController.getInstance().allSupplierOrderId();
        }
        else {
            new Alert(Alert.AlertType.ERROR, "Unable to Save the ORDER!!!").show();
        }
    }

    @FXML
    void cmbItemIdOnAction(ActionEvent event) throws SQLException {
        for (int i = 0; i < itemList.size(); i++) {
            if(cmbItemId.getSelectionModel().getSelectedItem().equals(itemList.get(i)[0])){
                int qty = Integer.parseInt(supplierOrderBO.getItemQtyOnHand(itemList.get(i)[0]));
                int orderedQty = Integer.parseInt(itemList.get(i)[1]);
                lblQtyOnHand.setText(String.valueOf(qty - orderedQty));
                lblUnitPrice.setText(supplierOrderBO.getItemUnitPrice(itemList.get(i)[0]));
                lblDescription.setText(supplierOrderBO.getItemDescription(itemList.get(i)[0]));
                return;
            }
        }
        lblDescription.setText(supplierOrderBO.getItemDescription(cmbItemId.getSelectionModel().getSelectedItem()));
        lblUnitPrice.setText(supplierOrderBO.getItemUnitPrice(cmbItemId.getSelectionModel().getSelectedItem()));
        lblQtyOnHand.setText(supplierOrderBO.getItemQtyOnHand(cmbItemId.getSelectionModel().getSelectedItem()));
    }

    public void setItemDataInComboBox() throws SQLException {
        ArrayList<String> roles = supplierOrderBO.getAllItemId();
        cmbItemId.getItems().addAll(roles);
    }

    @FXML
    void cmbSupplierIdOnAction(ActionEvent event) throws SQLException {
        lblSupplierName.setText(supplierOrderBO.getSupplierName(cmbSupplierId.getSelectionModel().getSelectedItem()));
    }

    public void setSupplierDataInComboBox() throws SQLException {
        ArrayList<String> roles = supplierOrderBO.getAllSupplierId();
        cmbSupplierId.getItems().addAll(roles);
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
    void btnAddNewSupplierOnMouseEntered(MouseEvent event) {
        StyleUtil.addNewCustomerORSupplierBtnSelected(btnAddNewSupplierPane, btnAddNewSupplierIconImg);
    }

    @FXML
    void btnAddNewSupplierOnMouseExited(MouseEvent event) {
        StyleUtil.addNewCustomerORSupplierBtnUnselected(btnAddNewSupplierPane, btnAddNewSupplierIconImg);
    }

    public void allSupplierOrderCartId() {

        vBoxCustomerOrder.getChildren().clear();

        for (int i = 0; i < itemList.size(); i++) {
            loadDataTable(itemList.get(i));
        }
    }

    private void loadDataTable(String[] id) {
        try {
            FXMLLoader loader = new FXMLLoader(SupplierOrderAddPopUpFormController.class.getResource("/view/supplierOrderAddPopUpBarForm.fxml"));
            Parent root = loader.load();
            SupplierOrderAddPopUpBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxCustomerOrder.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblOrderId.setText(NewId.newId(idList, NewId.GetType.SUPPLIER_ORDER));
        lblOrderDate.setText(DateTimeUtil.dateNow());

        try {
            setItemDataInComboBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            setSupplierDataInComboBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
