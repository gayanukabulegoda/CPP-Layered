package lk.grb.ceylonPottersPaletteLayered.controller;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import lk.grb.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.grb.ceylonPottersPaletteLayered.bo.custom.DashboardBO;
import lk.grb.ceylonPottersPaletteLayered.util.Navigation;
import lk.grb.ceylonPottersPaletteLayered.util.StyleUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {

    @FXML
    private Pane changeCredentialsPane;

    @FXML
    private Pane employeePaymentBtnPane;

    @FXML
    private Pane paneBtnCustomers;

    @FXML
    private Pane paneBtnSuppliers;

    @FXML
    private Label lblAttendance;

    @FXML
    private Label lblClayStock;

    @FXML
    private Label lblProductTotal;

    @FXML
    private Label lblTodaySales;

    @FXML
    private VBox vBoxOrders;

    @FXML
    private ImageView imgFlower1;

    @FXML
    private ImageView imgFlower2;

    @FXML
    private ImageView imgFlower3;

    @FXML
    private Label lblCustomerNo;

    @FXML
    private Label lblSupplierNo;

    @FXML
    private Pane piChartPane;

    @FXML
    private Pane deleteUserTextPane;

    @FXML
    private Pane deleteUserBtnInnerPane;

    @FXML
    private PieChart pieChart;

    DashboardBO dashboardBO =
            (DashboardBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.DASHBOARD);

    @FXML
    void btnChangeCredentialsOnAction(ActionEvent event) throws IOException {
        Navigation.imgPopUpBackground("credentialChangePopUpForm.fxml");
    }

    @FXML
    void btnEmployeePaymentOnAction(ActionEvent event) throws IOException {
        GlobalFormController.getInstance().btnEmployeeOnAction(event);
        Navigation.switchPaging(GlobalFormController.getInstance().pagingPane, "employeeSalaryForm.fxml");
    }

    @FXML
    void btnCustomersOnAction(ActionEvent event) throws IOException {
        GlobalFormController.getInstance().btnCustomerOnAction(event);
    }

    @FXML
    void btnSuppliersOnAction(ActionEvent event) throws IOException {
        GlobalFormController.getInstance().btnSupplierOnAction(event);
    }

    @FXML
    void btnDeleteUserOnAction(ActionEvent event) throws IOException {
        Navigation.imgPopUpBackground("deleteUserForm.fxml");
    }

    @FXML
    void btnDeleteUserOnMouseEntered(MouseEvent event) {
        deleteUserBtnInnerPane.setStyle("-fx-background-color: #E8E8E8;" +
                                                "-fx-background-radius: 20px");
        deleteUserTextPane.setVisible(true);
        // Scale-in effect using ScaleTransition
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.3), deleteUserTextPane);
        scaleTransition.setFromX(0);
        scaleTransition.setToX(1);
        scaleTransition.setFromY(0);
        scaleTransition.setToY(1);

        // Slide-in effect using TranslateTransition
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.2), deleteUserTextPane);
        translateTransition.setFromX(-deleteUserTextPane.getWidth()); // Start from the left of the pane
        translateTransition.setToX(0);

        scaleTransition.play();
        translateTransition.play();
    }

    @FXML
    void btnDeleteUserOnMouseExited(MouseEvent event) {
        deleteUserBtnInnerPane.setStyle("-fx-border-color: #E8E8E8;" +
                "-fx-border-radius: 20px");

        deleteUserTextPane.setVisible(false);
    }

    @FXML
    void btnEmployeePaymentOnMouseEntered(MouseEvent event) {
        StyleUtil.credentialOrEmployeeManageBtnSelected(employeePaymentBtnPane);
    }

    @FXML
    void btnEmployeePaymentOnMouseExited(MouseEvent event) {
        StyleUtil.credentialOrEmployeeManageUnselected(employeePaymentBtnPane);
    }

    @FXML
    void btnChangeCredentialsOnMouseEntered(MouseEvent event) {
        StyleUtil.credentialOrEmployeeManageBtnSelected(changeCredentialsPane);
    }

    @FXML
    void btnChangeCredentialsOnMouseExited(MouseEvent event) {
        StyleUtil.credentialOrEmployeeManageUnselected(changeCredentialsPane);
    }

    @FXML
    void btnSuppliersOnMouseEntered(MouseEvent event) {
        StyleUtil.credentialOrEmployeeManageBtnSelected(paneBtnSuppliers);
    }

    @FXML
    void btnSuppliersOnMouseExited(MouseEvent event) {
        StyleUtil.dashboardCustomerAndSupplierBtnUnselected(paneBtnSuppliers);
    }

    @FXML
    void btnCustomersOnMouseEntered(MouseEvent event) {
        StyleUtil.credentialOrEmployeeManageBtnSelected(paneBtnCustomers);
    }

    @FXML
    void btnCustomersOnMouseExited(MouseEvent event) {
        StyleUtil.dashboardCustomerAndSupplierBtnUnselected(paneBtnCustomers);
    }

    public void allOrderId() throws SQLException {

        vBoxOrders.getChildren().clear();
        ArrayList<String> list = dashboardBO.getAllCustomerOrderIdS();

        for (int i = 0; i < list.size(); i++) {
            loadDataTable(list.get(i));
        }
    }

    private void loadDataTable(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(DashboardFormController.class.getResource("/view/dashboardOrderBarForm.fxml"));
            Parent root = loader.load();
            DashboardOrderBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxOrders.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        imgFlower1.setCache(true);
        imgFlower1.setCacheHint(CacheHint.SPEED);

        imgFlower2.setCache(true);
        imgFlower2.setCacheHint(CacheHint.SPEED);

        imgFlower3.setCache(true);
        imgFlower3.setCacheHint(CacheHint.SPEED);

        // Create a RotateTransition for continuous rotation
        RotateTransition rotateTransition1 = new RotateTransition(Duration.seconds(8), imgFlower1);
        rotateTransition1.setInterpolator(Interpolator.LINEAR);
        rotateTransition1.setByAngle(360); // Rotate by 360 degrees
        rotateTransition1.setCycleCount(RotateTransition.INDEFINITE); // Repeat indefinitely
        rotateTransition1.play();

        RotateTransition rotateTransition2 = new RotateTransition(Duration.seconds(8), imgFlower2);
        rotateTransition2.setInterpolator(Interpolator.LINEAR);
        rotateTransition2.setByAngle(-360); // Rotate by 360 degrees
        rotateTransition2.setCycleCount(RotateTransition.INDEFINITE); // Repeat indefinitely
        rotateTransition2.play();

        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(8), imgFlower3);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setByAngle(-360); // Rotate by 360 degrees
        rotateTransition.setCycleCount(RotateTransition.INDEFINITE); // Repeat indefinitely
        rotateTransition.play();
    }

    public void setPiChart() throws SQLException {

        // Create sample data for the pie chart
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Total Income", dashboardBO.getCustomerOrderTotal()),
                new PieChart.Data("Total Expenses", (dashboardBO.getSupplierOrderTotal() + dashboardBO.getSalaryTotal()))
        );

        // Create a pie chart with the data
        pieChart = new PieChart(pieChartData);

        pieChart.getData().get(0).getNode().setStyle("-fx-pie-color: #C56E33;");
        pieChart.getData().get(1).getNode().setStyle("-fx-pie-color: #973F04;");

        pieChart.setLabelLineLength(0);
        pieChart.setLabelsVisible(false);

        piChartPane.getChildren().add(pieChart);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int qtyTotal = 0;

        try {
            ArrayList<String> allProductId = dashboardBO.getAllProductId();

            for (int i = 0; i < allProductId.size(); i++) {
                qtyTotal += Integer.parseInt(dashboardBO.getProductQtyTotal(allProductId.get(i)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        lblProductTotal.setText(String.valueOf(qtyTotal));

        try {
            lblAttendance.setText("0" + dashboardBO.getTodayAttendance());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            lblClayStock.setText(dashboardBO.getAvailableClayStock() + "kg");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            lblTodaySales.setText("0" + dashboardBO.getTodaySales());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            allOrderId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            lblCustomerNo.setText("00" + dashboardBO.getCustomerCount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            lblSupplierNo.setText("00" + dashboardBO.getSupplierCount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        start();

        try {
            setPiChart();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
