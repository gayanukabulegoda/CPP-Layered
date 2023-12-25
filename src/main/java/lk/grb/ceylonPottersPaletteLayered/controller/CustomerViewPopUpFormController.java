package lk.grb.ceylonPottersPaletteLayered.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPaletteLayered.dto.CustomerDto;
import lk.grb.ceylonPottersPaletteLayered.model.CustomerModel;
import lk.grb.ceylonPottersPaletteLayered.util.Navigation;
import lk.grb.ceylonPottersPaletteLayered.util.StyleUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerViewPopUpFormController implements Initializable {

    @FXML
    private Pane btnClosePane;

    @FXML
    private Pane closeIconPane;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblContactNo;

    @FXML
    private Label lblCustomerId;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblRegisteredDate;

    @FXML
    private Label lblRegisteredTime;

    @FXML
    private Label lblUser;

    public static String customerId;

    CustomerModel customerModel = new CustomerModel();

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseIconOnMouseEntered(MouseEvent event) {
        StyleUtil.closeIconBtnSelected(closeIconPane, imgCloseIcon);
    }

    @FXML
    void btnCloseIconOnMouseExited(MouseEvent event) {
        StyleUtil.closeIconBtnUnselected(closeIconPane, imgCloseIcon);
    }

    @FXML
    void btnCloseOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(btnClosePane);
    }

    @FXML
    void btnCloseOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(btnClosePane);
    }

    public void setData() throws SQLException {

        CustomerDto customerDto = customerModel.getData(customerId);

        lblCustomerId.setText(customerDto.getCustomer_Id());
        lblCustomerName.setText(customerDto.getName());
        lblContactNo.setText(customerDto.getContact_No());
        lblRegisteredDate.setText(customerDto.getDate());
        lblRegisteredTime.setText(customerDto.getTime());
        lblUser.setText(customerDto.getUser_Name());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
