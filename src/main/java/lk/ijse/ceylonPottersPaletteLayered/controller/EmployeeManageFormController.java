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
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.EmployeeBO;
import lk.ijse.ceylonPottersPaletteLayered.util.Navigation;
import lk.ijse.ceylonPottersPaletteLayered.util.RegExPatterns;
import lk.ijse.ceylonPottersPaletteLayered.util.StyleUtil;

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

    EmployeeBO employeeBO =
            (EmployeeBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.EMPLOYEE);

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
        if (checkValidations()) return;

        ArrayList<String> allEmployeeId = employeeBO.getAllEmployeeId();
        for (String employeeId : allEmployeeId) {
            if (txtSearch.getText().equals(employeeBO.getEmployeeContactNo(employeeId))
               || txtSearch.getText().equals(employeeId)) {
                loadViewPopUpForm(employeeId);
                return;
            }
        }
        lblSearchAlert.setText("Invalid ID or Contact No!!");
        StyleUtil.searchBarRed(searchBarPane);
    }

    private void loadViewPopUpForm(String employeeId) throws IOException {
        EmployeeViewPopUpFormController.employeeId = employeeId;
        lblSearchAlert.setText(" ");
        StyleUtil.searchBarTransparent(searchBarPane);
        txtSearch.clear();
        Navigation.imgPopUpBackground("employeeViewPopUpForm.fxml");
    }

    private boolean checkValidations() {
        if (validateId() & validateContactNo()) {
            lblSearchAlert.setText("Invalid ID or Contact No!!");
            StyleUtil.searchBarRed(searchBarPane);
            return true;
        }
        return false;
    }

    private boolean validateId() {
        return RegExPatterns.employeeIdPattern(txtSearch.getText());
    }

    private boolean validateContactNo() {
        return RegExPatterns.contactNoPattern(txtSearch.getText());
    }

    public void allEmployeeId() {
        vBoxEmployeeManage.getChildren().clear();
        ArrayList<String> list;
        try {
            list = employeeBO.getAllEmployeeId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (String employeeId : list) {
            loadDataTable(employeeId);
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
        allEmployeeId();
    }
}
