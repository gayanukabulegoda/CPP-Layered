package lk.grb.ceylonPottersPaletteLayered.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.grb.ceylonPottersPalette.model.ItemStockModel;
import lk.grb.ceylonPottersPalette.util.StyleUtil;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class SupplierOrderAddPopUpBarFormController {

    @FXML
    private ImageView deleteImg;

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

    ItemStockModel itemStockModel = new ItemStockModel();

    @FXML
    void deleteOnMouseClick(MouseEvent event) {
        String[] x ={id.getText(),qty.getText()};
        removeElement(SupplierOrderAddPopUpFormController.itemList,x);

        double netTotal = SupplierOrderAddPopUpFormController.getInstance().netTotal;
        double total = Integer.parseInt(qty.getText()) * Double.parseDouble(unitPrice.getText());
        SupplierOrderAddPopUpFormController.getInstance().netTotal = (netTotal - total);

        SupplierOrderAddPopUpFormController.getInstance().lblNetTotal.setText(String.valueOf(SupplierOrderAddPopUpFormController.getInstance().netTotal));
        SupplierOrderAddPopUpFormController.getInstance().allSupplierOrderCartId();
    }

    private static boolean removeElement(List<String[]> list, String[] target) {
        return list.removeIf(element -> Arrays.equals(element, target));
    }

    @FXML
    void deleteOnMouseEntered(MouseEvent event) {
        StyleUtil.deleteImgSelected(deleteImg);
    }

    @FXML
    void deleteOnMouseExited(MouseEvent event) {
        StyleUtil.deleteImgUnselected(deleteImg);
    }

    public void setData(String[] element) {
        try {
            String[] descriptionAndUnitPrice = itemStockModel.descAndUnitPriceGet(element[0]);

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
