package lk.grb.ceylonPottersPaletteLayered.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.grb.ceylonPottersPaletteLayered.bo.custom.EmployeeBO;
import lk.grb.ceylonPottersPaletteLayered.dto.EmployeeDto;
import lk.grb.ceylonPottersPaletteLayered.util.Navigation;
import lk.grb.ceylonPottersPaletteLayered.util.RegExPatterns;
import lk.grb.ceylonPottersPaletteLayered.util.StyleUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeUpdatePopUpFormController implements Initializable {

    @FXML
    private Pane cancelBtnPane;

    @FXML
    private Pane closeIconPane;

    @FXML
    private JFXComboBox<String> cmbRole;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblCancel;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtContactNo;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtHouseNo;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtStreet;

    @FXML
    private Pane updateBtnPane;

    @FXML
    private Label lblCityAlert;

    @FXML
    private Label lblCmbRoleAlert;

    @FXML
    private Label lblContactNoAlert;

    @FXML
    private Label lblEmailAlert;

    @FXML
    private Label lblFirstNameAlert;

    @FXML
    private Label lblHouseNoAlert;

    @FXML
    private Label lblLastNameAlert;

    @FXML
    private Label lblNicAlert;

    @FXML
    private Label lblStreetAlert;

    public static String employeeId;

    public static EmployeeUpdatePopUpFormController controller;

    public EmployeeUpdatePopUpFormController() {
        controller = this;
    }

    public static EmployeeUpdatePopUpFormController getInstance() {
        return controller;
    }

    EmployeeBO employeeBO =
            (EmployeeBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.EMPLOYEE);

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnUpdateOnAction() throws SQLException {

        if (validateEmployee()) {
            EmployeeDto employeeDto = new EmployeeDto();

            employeeDto.setEmployee_Id(EmployeeUpdatePopUpFormController.employeeId);
            employeeDto.setFirst_Name(txtFirstName.getText());
            employeeDto.setLast_Name(txtLastName.getText());
            employeeDto.setNic(txtNic.getText());
            employeeDto.setHouse_No(txtHouseNo.getText());
            employeeDto.setStreet(txtStreet.getText());
            employeeDto.setCity(txtCity.getText());
            employeeDto.setContact_No(txtContactNo.getText());
            employeeDto.setEmail(txtEmail.getText());
            employeeDto.setRole(getRole());

            boolean updated = employeeBO.updateEmployee(employeeDto);

            if (updated) {
                Navigation.closePane();
                EmployeeManageFormController.getInstance().allEmployeeId();
            }
        }
    }

    private boolean validateEmployee() {
        boolean result = true;

        if (RegExPatterns.firstLastNamePattern(txtFirstName.getText())) {
            lblFirstNameAlert.setText("Invalid First Name!!");
            result = false;
        }

        if (RegExPatterns.firstLastNamePattern(txtLastName.getText())) {
            lblLastNameAlert.setText("Invalid Last Name!!");
            result = false;
        }

        if (RegExPatterns.nicPattern(txtNic.getText())) {
            lblNicAlert.setText("Invalid NIC!!");
            result = false;
        }

        if ((cmbRole.getSelectionModel().getSelectedItem()) == null) {
            lblCmbRoleAlert.setText("Select a Role!!");
            result = false;
        }

        if (RegExPatterns.contactNoPattern(txtContactNo.getText())) {
            lblContactNoAlert.setText("Invalid Contact Number!!");
            result = false;
        }

        if (RegExPatterns.emailPattern(txtEmail.getText())) {
            lblEmailAlert.setText("Invalid Email!!");
            result = false;
        }

        if (RegExPatterns.houseNoPattern(txtHouseNo.getText())) {
            lblHouseNoAlert.setText("Invalid House No!!");
            result = false;
        }

        if (RegExPatterns.namePattern(txtStreet.getText())) {
            lblStreetAlert.setText("Invalid Street Name!!");
            result = false;
        }

        if (RegExPatterns.namePattern(txtCity.getText())) {
            lblCityAlert.setText("Invalid City Name!!");
            result = false;
        }
        return result;
    }

    @FXML
    void txtCityOnKeyPressed(KeyEvent event) throws SQLException {
        lblCityAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.namePattern(txtCity.getText())) {
                lblCityAlert.setText("Invalid City Name!!");
                event.consume();
            } else {
                btnUpdateOnAction();
            }
        }
    }

    @FXML
    void txtContactNoOnKeyPressed(KeyEvent event) {
        lblContactNoAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.contactNoPattern(txtContactNo.getText())) {
                lblContactNoAlert.setText("Invalid Contact Number!!");
                event.consume();
            } else {
                txtEmail.requestFocus();
            }
        }
    }

    @FXML
    void txtEmailOnKeyPressed(KeyEvent event) {
        lblEmailAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.emailPattern(txtEmail.getText())) {
                lblEmailAlert.setText("Invalid Email!!");
                event.consume();
            } else {
                txtNic.requestFocus();
            }
        }
    }

    @FXML
    void txtFirstNameOnKeyPressed(KeyEvent event) {
        lblFirstNameAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.firstLastNamePattern(txtFirstName.getText())) {
                lblFirstNameAlert.setText("Invalid First Name!!");
                event.consume();
            } else {
                txtLastName.requestFocus();
            }
        }
    }

    @FXML
    void txtHouseNoOnKeyPressed(KeyEvent event) {
        lblHouseNoAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.houseNoPattern(txtHouseNo.getText())) {
                lblHouseNoAlert.setText("Invalid House No!!");
                event.consume();
            } else {
                txtStreet.requestFocus();
            }
        }
    }

    @FXML
    void txtLastNameOnKeyPressed(KeyEvent event) {
        lblLastNameAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.firstLastNamePattern(txtLastName.getText())) {
                lblLastNameAlert.setText("Invalid Last Name!!");
                event.consume();
            } else {
                txtContactNo.requestFocus();
            }
        }
    }

    @FXML
    void txtNicOnKeyPressed(KeyEvent event) {
        lblNicAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.nicPattern(txtNic.getText())) {
                lblNicAlert.setText("Invalid NIC!!");
                event.consume();
            } else {
                cmbRole.requestFocus();
            }
        }
    }

    @FXML
    void txtStreetOnKeyPressed(KeyEvent event) {
        lblStreetAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if (RegExPatterns.namePattern(txtStreet.getText())) {
                lblStreetAlert.setText("Invalid Street Name!!");
                event.consume();
            } else {
                txtCity.requestFocus();
            }
        }
    }

    @FXML
    void cmbRoleOnKeyPressed(KeyEvent event) {
        lblCmbRoleAlert.setText(" ");

        if (event.getCode() == KeyCode.ENTER) {
            if ((cmbRole.getSelectionModel().getSelectedItem()) == null) {
                lblCmbRoleAlert.setText("Select a Role!!");
                event.consume();
            } else {
                txtHouseNo.requestFocus();
            }
        }
    }

    @FXML
    void cityOnMouseClick(MouseEvent event) {
        lblCityAlert.setText(" ");
    }

    @FXML
    void cmbRoleOnMouseClick(MouseEvent event) {
        lblCmbRoleAlert.setText(" ");
    }

    @FXML
    void contactNoOnMouseClick(MouseEvent event) {
        lblContactNoAlert.setText(" ");
    }

    @FXML
    void emailOnMouseClick(MouseEvent event) {
        lblEmailAlert.setText(" ");
    }

    @FXML
    void firstNameOnMouseClick(MouseEvent event) {
        lblFirstNameAlert.setText(" ");
    }

    @FXML
    void houseNoOnMouseClick(MouseEvent event) {
        lblHouseNoAlert.setText(" ");
    }

    @FXML
    void lastNameOnMouseClick(MouseEvent event) {
        lblLastNameAlert.setText(" ");
    }

    @FXML
    void nicOnMouseClick(MouseEvent event) {
        lblNicAlert.setText(" ");
    }

    @FXML
    void streetOnMouseClick(MouseEvent event) {
        lblStreetAlert.setText(" ");
    }

    @FXML
    void btnUpdateOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(updateBtnPane);
    }

    @FXML
    void btnUpdateOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(updateBtnPane);
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
    void btnCancelOnMouseEntered(MouseEvent event) {
        StyleUtil.cancelBtnSelected(cancelBtnPane, lblCancel);
    }

    @FXML
    void btnCancelOnMouseExited(MouseEvent event) {
        StyleUtil.cancelBtnUnselected(cancelBtnPane, lblCancel);
    }

    private void setDataInComboBox() {
        ArrayList<String> roles = new ArrayList<>();
        roles.add("Manager");
        roles.add("Manufacturing Staff");
        roles.add("Sales Staff");
        roles.add("Minor Staff");
        roles.add("other");

        cmbRole.getItems().addAll(roles);
    }

    public String getRole() {
        return String.valueOf(cmbRole.getSelectionModel().getSelectedItem());
    }

    public void setData() {
        try {
            EmployeeDto employeeDto = employeeBO.getEmployeeData(employeeId);

            txtFirstName.setText(employeeDto.getFirst_Name());
            txtLastName.setText(employeeDto.getLast_Name());
            txtNic.setText(employeeDto.getNic());
            txtContactNo.setText(employeeDto.getContact_No());
            txtHouseNo.setText(employeeDto.getHouse_No());
            txtCity.setText(employeeDto.getCity());
            txtStreet.setText(employeeDto.getStreet());
            txtEmail.setText(employeeDto.getEmail());
            cmbRole.setValue(employeeDto.getRole());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
        setDataInComboBox();
    }
}
