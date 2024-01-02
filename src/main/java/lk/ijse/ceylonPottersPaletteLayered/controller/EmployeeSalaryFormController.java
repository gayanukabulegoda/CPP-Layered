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
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.SalaryBO;
import lk.ijse.ceylonPottersPaletteLayered.util.Navigation;
import lk.ijse.ceylonPottersPaletteLayered.util.RegExPatterns;
import lk.ijse.ceylonPottersPaletteLayered.util.StyleUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeSalaryFormController implements Initializable {

    @FXML
    private Pane addSalaryPane;

    @FXML
    private Pane btnRefreshPane;

    @FXML
    private Pane searchBarPane;

    @FXML
    private ImageView imgAdd;

    @FXML
    private ImageView imgRefresh;

    @FXML
    private Label lblAddSalary;

    @FXML
    private Label lblSearchAlert;

    @FXML
    private TextField txtSearch;

    @FXML
    private VBox vBoxEmployeeSalary;

    private static EmployeeSalaryFormController controller;

    public EmployeeSalaryFormController() {
        controller = this;
    }

    public static EmployeeSalaryFormController getInstance() {
        return controller;
    }

    SalaryBO salaryBO =
            (SalaryBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.SALARY);

    @FXML
    void btnAddSalaryOnAction(ActionEvent event) throws IOException {
        Navigation.imgPopUpBackground("employeeSalaryPopUpForm.fxml");
    }

    @FXML
    void btnAddSalaryOnMouseEntered(MouseEvent event) {
        StyleUtil.addBtnSelected(addSalaryPane, lblAddSalary, imgAdd);
    }

    @FXML
    void btnAddSalaryOnMouseExited(MouseEvent event) {
        StyleUtil.addBtnUnselected(addSalaryPane, lblAddSalary, imgAdd);
    }

    @FXML
    void btnEmployeeAttendanceOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "employeeAttendanceForm.fxml");
    }

    @FXML
    void btnEmployeeManageOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "employeeManageForm.fxml");
    }

    @FXML
    void btnRefreshTableOnAction(ActionEvent event) {
        allSalaryId();
    }

    @FXML
    void btnRefreshTableOnMouseEntered(MouseEvent event) {
        StyleUtil.refreshBtnSelected(btnRefreshPane, imgRefresh);
    }

    @FXML
    void btnRefreshTableOnMouseExited(MouseEvent event) {
        StyleUtil.refreshBtnUnselected(btnRefreshPane, imgRefresh);
    }

    @FXML
    void txtSearchOnMouseClicked(MouseEvent event) {
        lblSearchAlert.setText(" ");
        StyleUtil.searchBarTransparent(searchBarPane);
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws IOException, SQLException {
        if (checkValidations()) return;

        ArrayList<String> allEmployeeId = salaryBO.getAllEmployeeId();
        for (String employeeId : allEmployeeId) {
            if (txtSearch.getText().equals(salaryBO.getEmployeeContactNo(employeeId))
                || txtSearch.getText().equals(employeeId)) {

                allSelectedEmployeeSalaryId(employeeId);
                lblSearchAlert.setText(" ");
                StyleUtil.searchBarTransparent(searchBarPane);
                txtSearch.clear();
                return;
            }
        }
        lblSearchAlert.setText("Invalid ID or Contact No!!");
        StyleUtil.searchBarRed(searchBarPane);
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

    public void allSalaryId() {

        vBoxEmployeeSalary.getChildren().clear();
        ArrayList<String> list;
        try {
            list = salaryBO.getAllSalaryId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (String salaryId : list) {
            loadDataTable(salaryId);
        }
    }

    public void allSelectedEmployeeSalaryId(String id) throws SQLException {

        vBoxEmployeeSalary.getChildren().clear();
        ArrayList<String> list = salaryBO.getSelectedAllSalaryId(id);

        for (String salaryId : list) {
            loadDataTable(salaryId);
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(EmployeeSalaryFormController.class.getResource("/view/employeeSalaryBarForm.fxml"));
            Parent root = loader.load();
            EmployeeSalaryBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxEmployeeSalary.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allSalaryId();
    }
}
