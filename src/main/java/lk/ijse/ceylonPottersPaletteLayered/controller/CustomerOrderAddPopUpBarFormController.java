package lk.ijse.ceylonPottersPaletteLayered.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.CustomerOrderBO;
import lk.ijse.ceylonPottersPaletteLayered.util.StyleUtil;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class CustomerOrderAddPopUpBarFormController {

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

    CustomerOrderBO customerOrderBO =
            (CustomerOrderBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.CUSTOMER_ORDER);

    @FXML
    void deleteOnMouseClick(MouseEvent event) {
        String[] x ={id.getText(),qty.getText()};
        removeElement(CustomerOrderAddPopUpFormController.productList,x);

        double netTotal = CustomerOrderAddPopUpFormController.getInstance().netTotal;
        double total = customerOrderBO.getTotal(unitPrice.getText(), qty.getText());
        CustomerOrderAddPopUpFormController.getInstance().netTotal = (netTotal - total);

        CustomerOrderAddPopUpFormController.getInstance().
                lblNetTotal.setText(String.valueOf(CustomerOrderAddPopUpFormController.getInstance().netTotal));

        CustomerOrderAddPopUpFormController.getInstance().allCustomerOrderCartId();
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
            String[] descriptionAndUnitPrice = customerOrderBO.productDescAndUnitPriceGet(element[0]);

            this.id.setText(element[0]);
            description.setText(descriptionAndUnitPrice[0]);
            unitPrice.setText(descriptionAndUnitPrice[1]);
            qty.setText(element[1]);
            total.setText(String.valueOf(customerOrderBO.getTotal(unitPrice.getText(), qty.getText())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
