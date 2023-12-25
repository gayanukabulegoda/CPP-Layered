package lk.grb.ceylonPottersPaletteLayered.controller;

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
import lk.grb.ceylonPottersPaletteLayered.model.EmployeeModel;
import lk.grb.ceylonPottersPaletteLayered.util.Navigation;
import lk.grb.ceylonPottersPaletteLayered.util.StyleUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class EmployeeManageFormController implements Initializable {

    @FXML
    private Pane addEmployeePane;

    @FXML
    private Pane searchBarPane;

    @FXML
    private ImageView imgAdd;

    @FXML
    private Label lblAddEmployee;

    @FXML
    private Label lblSearchAlert;

    @FXML
    private TextField txtSearch;

    @FXML
    private VBox vBoxEmployeeManage;

    private static EmployeeManageFormController controller;

    public EmployeeManageFormController() {
        controller = this;
    }

    public static EmployeeManageFormController getInstance() {
        return controller;
    }

    @FXML
    void btnAddEmployeeOnAction(ActionEvent event) throws IOException {
        Navigation.imgPopUpBackground("employeeAddPopUpForm.fxml");
    }

    @FXML
    void btnAddEmployeeOnMouseEntered(MouseEvent event) {
        StyleUtil.addBtnSelected(addEmployeePane, lblAddEmployee, imgAdd);
    }

    @FXML
    void btnAddEmployeeOnMouseExited(MouseEvent event) {
        StyleUtil.addBtnUnselected(addEmployeePane, lblAddEmployee, imgAdd);
    }

    @FXML
    void btnEmployeeAttendanceOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "employeeAttendanceForm.fxml");
    }

    @FXML
    void btnEmployeeSalaryOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "employeeSalaryForm.fxml");
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

        EmployeeModel employeeModel = new EmployeeModel();
        ArrayList<String> allEmployeeId = employeeModel.getAllEmployeeId();

        for (int i = 0; i < allEmployeeId.size(); i++) {
            if (txtSearch.getText().equals(employeeModel.getEmployeeContactNo(allEmployeeId.get(i)))) {
                EmployeeViewPopUpFormController.employeeId = allEmployeeId.get(i);
                lblSearchAlert.setText(" ");
                StyleUtil.searchBarTransparent(searchBarPane);
                txtSearch.clear();
                Navigation.imgPopUpBackground("employeeViewPopUpForm.fxml");
                return;
            }
        }
        lblSearchAlert.setText("Invalid Contact No!!");
        StyleUtil.searchBarRed(searchBarPane);
    }

    private boolean validateId() {
        return Pattern.matches("[0-9]{10}", txtSearch.getText());
    }

    public void allEmployeeId() throws SQLException {

        vBoxEmployeeManage.getChildren().clear();
        EmployeeModel employeeModel = new EmployeeModel();
        ArrayList<String> list = employeeModel.getAllEmployeeId();

        for (int i = 0; i < list.size(); i++) {
            loadDataTable(list.get(i));
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(EmployeeManageFormController.class.getResource("/view/employeeManageBarForm.fxml"));
            Parent root = loader.load();
            EmployeeManageBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxEmployeeManage.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            allEmployeeId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
