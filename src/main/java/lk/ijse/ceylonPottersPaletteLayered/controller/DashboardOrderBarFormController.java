package lk.ijse.ceylonPottersPaletteLayered.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lk.ijse.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.DashboardBO;
import lk.ijse.ceylonPottersPaletteLayered.dto.CustomerOrderDto;
import lk.ijse.ceylonPottersPaletteLayered.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class DashboardOrderBarFormController {

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblTotalPrice;

    DashboardBO dashboardBO =
            (DashboardBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.DASHBOARD);

    @FXML
    void btnCustomerOrdersOnAction(ActionEvent event) throws IOException, SQLException {
        CustomerOrderViewPopUpFormController.customerOrderId = lblOrderId.getText();
        CustomerOrderViewPopUpFormController.customerId = setCustomerNameInPopUp();
        Navigation.imgPopUpBackground("customerOrderViewPopUpForm.fxml");
    }

    private String setCustomerNameInPopUp() throws SQLException {
        return dashboardBO.getCustomerIdForOrder(lblOrderId.getText());
    }

    public void setData(String id) {
        try {
            CustomerOrderDto customerOrderDto = dashboardBO.getCustomerOrderData(id);

            this.lblOrderId.setText(customerOrderDto.getCustomer_Order_Id());
            lblTime.setText(customerOrderDto.getTime());
            lblTotalPrice.setText(String.valueOf(customerOrderDto.getTotal_Price()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
