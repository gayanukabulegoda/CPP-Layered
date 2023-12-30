package lk.ijse.ceylonPottersPaletteLayered.controller;

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
import lk.ijse.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.DashboardBO;
import lk.ijse.ceylonPottersPaletteLayered.util.Navigation;
import lk.ijse.ceylonPottersPaletteLayered.util.StyleUtil;

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

    public void allOrderId() {
        vBoxOrders.getChildren().clear();
        ArrayList<String> list;

        try {
            list = dashboardBO.getAllCustomerOrderIdS();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (String customerOrderId : list) {
            loadDataTable(customerOrderId);
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

    private void start() {
        setRotateTransition(imgFlower1, 360);
        setRotateTransition(imgFlower2, -360);
        setRotateTransition(imgFlower3, -360);
    }

    /** Create a RotateTransition for continuous rotation */
    private void setRotateTransition(ImageView img, int rotateAngle) {
        img.setCache(true);
        img.setCacheHint(CacheHint.QUALITY);

        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(8), img);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setByAngle(rotateAngle); // Rotate by 360 degrees
        rotateTransition.setCycleCount(RotateTransition.INDEFINITE); // Repeat indefinitely
        rotateTransition.play();
    }

    private void setPiChart() {
        ObservableList<PieChart.Data> pieChartData;

        try {
            pieChartData = addPieChartData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        pieChart = new PieChart(pieChartData); // Create a pie chart with the data

        pieChart.getData().get(0).getNode().setStyle("-fx-pie-color: #C56E33;");
        pieChart.getData().get(1).getNode().setStyle("-fx-pie-color: #973F04;");
        pieChart.setLabelLineLength(0);
        pieChart.setLabelsVisible(false);

        piChartPane.getChildren().add(pieChart);
    }

    /** Add data for the Pie Chart */
    private ObservableList<PieChart.Data> addPieChartData() throws SQLException {
        return FXCollections.observableArrayList(
                new PieChart.Data("Total Income", dashboardBO.getCustomerOrderTotal()),
                new PieChart.Data("Total Expenses", (dashboardBO.getSupplierOrderTotal() + dashboardBO.getSalaryTotal()))
        );
    }

    private void setLabelValues() {
        int qtyTotal = 0;

        try {
            ArrayList<String> allProductId = dashboardBO.getAllProductId();

            for (String productId : allProductId) {
                qtyTotal += Integer.parseInt(dashboardBO.getProductQtyTotal(productId));
            }
            lblProductTotal.setText(String.valueOf(qtyTotal));
            lblAttendance.setText("0" + dashboardBO.getTodayAttendance());
            lblClayStock.setText(dashboardBO.getAvailableClayStock() + "kg");
            lblTodaySales.setText("0" + dashboardBO.getTodaySales());
            lblCustomerNo.setText("00" + dashboardBO.getCustomerCount());
            lblSupplierNo.setText("00" + dashboardBO.getSupplierCount());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLabelValues();
        allOrderId();
        start();
        setPiChart();
    }
}
