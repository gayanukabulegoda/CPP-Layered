package lk.grb.ceylonPottersPaletteLayered.controller;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import lk.grb.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.grb.ceylonPottersPaletteLayered.bo.custom.SupplierOrderBO;
import lk.grb.ceylonPottersPaletteLayered.dto.SupplierOrderDto;
import lk.grb.ceylonPottersPaletteLayered.util.Navigation;
import lk.grb.ceylonPottersPaletteLayered.util.StyleUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupplierOrderViewPopUpFormController implements Initializable {

    @FXML
    private Pane btnClosePane;

    @FXML
    private ImageView imgFlower1;

    @FXML
    private ImageView imgFlower2;

    @FXML
    private ImageView imgFlower3;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblOrderTime;

    @FXML
    private Label lblSupplierId;

    @FXML
    private Label lblSupplierName;

    @FXML
    private VBox vBoxSupplierOrder;

    public static String supplierOrderId;
    public static String supplierId;

    SupplierOrderBO supplierOrderBO =
            (SupplierOrderBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.SUPPLIER_ORDER);

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Navigation.closeOrderPopUpPane();
    }

    @FXML
    void btnCloseOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(btnClosePane);
    }

    @FXML
    void btnCloseOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(btnClosePane);
    }

    private void start() {
        setRotateTransition(imgFlower1, 360);
        setRotateTransition(imgFlower2, -360);
        setRotateTransition(imgFlower3, -360);
    }

    /** Create a RotateTransition for continuous rotation */
    private void setRotateTransition(ImageView img, int rotateAngle) {
        img.setCache(true);
        img.setCacheHint(CacheHint.QUALITY);

        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(8), img);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setByAngle(rotateAngle); // Rotate by 360 degrees
        rotateTransition.setCycleCount(RotateTransition.INDEFINITE); // Repeat indefinitely
        rotateTransition.play();
    }

    public void allSupplierOrderCartId(ArrayList<String[]> itemList) {
        vBoxSupplierOrder.getChildren().clear();

        for (String[] item : itemList) {
            loadDataTable(item);
        }
    }

    private void loadDataTable(String[] id) {
        try {
            FXMLLoader loader = new FXMLLoader(SupplierOrderViewPopUpFormController.class.getResource("/view/supplierOrderViewPopUpBarForm.fxml"));
            Parent root = loader.load();
            SupplierOrderViewPopUpBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxSupplierOrder.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setData() {
        SupplierOrderDto supplierOrderDto;
        String supplierName;
        ArrayList<String[]> list;

        try {
            supplierOrderDto = supplierOrderBO.getSupplierOrderData(supplierOrderId);
            supplierName = supplierOrderBO.getSupplierName(supplierId);
            list = supplierOrderBO.getSupplierOrderDataAsAnArray(supplierOrderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        lblOrderId.setText(supplierOrderDto.getSupplier_Order_Id());
        lblOrderDate.setText(supplierOrderDto.getDate());
        lblOrderTime.setText(supplierOrderDto.getTime());
        lblSupplierId.setText(supplierOrderDto.getSupplier_Id());
        lblSupplierName.setText(supplierName);
        lblNetTotal.setText(String.valueOf(supplierOrderDto.getTotal_Price()));

        allSupplierOrderCartId(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        start();
        setData();
    }
}
