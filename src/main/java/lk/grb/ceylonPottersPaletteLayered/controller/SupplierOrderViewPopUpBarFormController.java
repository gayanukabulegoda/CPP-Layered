package lk.grb.ceylonPottersPaletteLayered.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import lk.grb.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.grb.ceylonPottersPaletteLayered.bo.custom.SupplierOrderBO;

import java.sql.SQLException;

public class SupplierOrderViewPopUpBarFormController {

    @FXML
    private Text description;

    @FXML
    private Text id;

    @FXML
    private Text qty;

    @FXML
    private Text total;

    @FXML
    private Text unitPrice;

    SupplierOrderBO supplierOrderBO =
            (SupplierOrderBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.SUPPLIER_ORDER);

    public void setData(String[] element) {
        try {
            String[] descriptionAndUnitPrice = supplierOrderBO.itemDescAndUnitPriceGet(element[0]);

            this.id.setText(element[0]);
            description.setText(descriptionAndUnitPrice[0]);
            unitPrice.setText(descriptionAndUnitPrice[1]);
            qty.setText(element[1]);
            total.setText(String.valueOf(Double.parseDouble(unitPrice.getText()) * Integer.parseInt(qty.getText())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
