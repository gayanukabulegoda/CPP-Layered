package lk.grb.ceylonPottersPaletteLayered.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lk.grb.ceylonPottersPalette.dto.CustomerOrderDto;
import lk.grb.ceylonPottersPalette.model.CustomerOrderModel;

import java.io.IOException;
import java.sql.SQLException;

public class DashboardOrderBarFormController {

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblTotalPrice;

    CustomerOrderModel customerOrderModel = new CustomerOrderModel();

    @FXML
    void btnCustomerOrdersOnAction(ActionEvent event) throws IOException {
        GlobalFormController.getInstance().btnSalesOnAction(event);
    }

    public void setData(String id) {
        try {
            CustomerOrderDto customerOrderDto = customerOrderModel.getData(id);

            this.lblOrderId.setText(customerOrderDto.getCustomer_Order_Id());
            lblTime.setText(customerOrderDto.getTime());
            lblTotalPrice.setText(String.valueOf(customerOrderDto.getTotal_Price()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
