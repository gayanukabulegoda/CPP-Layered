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
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.CustomerBO;
import lk.ijse.ceylonPottersPaletteLayered.util.Navigation;
import lk.ijse.ceylonPottersPaletteLayered.util.StyleUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class CustomerManageFormController implements Initializable {

    @FXML
    private Pane addCustomerPane;

    @FXML
    private ImageView imgAdd;

    @FXML
    private Label lblSearchAlert;

    @FXML
    private Label lblAddCustomer;

    @FXML
    private Pane searchBarPane;

    @FXML
    private TextField txtSearch;

    @FXML
    public VBox vBoxCustomerManage;

    private static CustomerManageFormController controller;

    CustomerBO customerBO =
            (CustomerBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.CUSTOMER);

    public CustomerManageFormController() {
        controller = this;
    }

    public static  CustomerManageFormController getInstance() {
        return controller;
    }

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) throws IOException {
        Navigation.imgPopUpBackground("customerAddPopUpForm.fxml");
    }

    @FXML
    void btnAddCustomerOnMouseEntered(MouseEvent event) {
        StyleUtil.addBtnSelected(addCustomerPane, lblAddCustomer, imgAdd);
    }

    @FXML
    void btnAddCustomerOnMouseExited(MouseEvent event) {
        StyleUtil.addBtnUnselected(addCustomerPane, lblAddCustomer, imgAdd);
    }

    @FXML
    void txtSearchOnMouseClicked(MouseEvent event) {
        lblSearchAlert.setText(" ");
        StyleUtil.searchBarTransparent(searchBarPane);
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws IOException, SQLException {

        if (!validateId()) {
            lblSearchAlert.setText("Invalid Contact No!!");
            StyleUtil.searchBarRed(searchBarPane);
            return;
        }

        ArrayList<String> allCustomerId = customerBO.getAllCustomerId();

        for (String customerId : allCustomerId) {
            if (txtSearch.getText().equals(customerBO.getCustomerContactNo(customerId))) {
                CustomerViewPopUpFormController.customerId = customerId;
                Navigation.imgPopUpBackground("customerViewPopUpForm.fxml");
                lblSearchAlert.setText(" ");
                StyleUtil.searchBarTransparent(searchBarPane);
                txtSearch.clear();
                return;
            }
        }
        lblSearchAlert.setText("Invalid Contact No!!");
        StyleUtil.searchBarRed(searchBarPane);
    }

    private boolean validateId() {
        return Pattern.matches("[0-9]{10}", txtSearch.getText());
    }

    public void allCustomerId() {

        vBoxCustomerManage.getChildren().clear();
        ArrayList<String> list;
        try {
            list = customerBO.getAllCustomerId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (String customerId : list) {
            loadDataTable(customerId);
        }
    }

    public void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(CustomerManageFormController.class.getResource("/view/customerManageBarForm.fxml"));
            Parent root = loader.load();
            CustomerManageBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxCustomerManage.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allCustomerId();
    }
}
