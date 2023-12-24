package lk.grb.ceylonPottersPaletteLayered.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import lk.grb.ceylonPottersPalette.model.UserModel;
import lk.grb.ceylonPottersPalette.util.DateTimeUtil;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GlobalFormController implements Initializable {

    @FXML
    private ImageView imgCustomer;

    @FXML
    private ImageView imgDashboard;

    @FXML
    private ImageView imgEmployee;

    @FXML
    private ImageView imgLogOut;

    @FXML
    public ImageView imgPopUpBackground;

    @FXML
    private ImageView imgSales;

    @FXML
    private ImageView imgStock;

    @FXML
    private ImageView imgSupplier;

    @FXML
    private Label lblCustomer;

    @FXML
    private Label lblDashboard;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblEmployee;

    @FXML
    private Label lblLogOut;

    @FXML
    private Label lblSales;

    @FXML
    private Label lblStock;

    @FXML
    private Label lblSupplier;

    @FXML
    public Label lblTime;

    @FXML
    private Label lblUser;

    @FXML
    public Pane pagingPane;

    @FXML
    private Pane paneBtnCustomer;

    @FXML
    private Pane paneBtnDashboard;

    @FXML
    private Pane paneBtnEmployee;

    @FXML
    private Pane paneBtnLogOut;

    @FXML
    private Pane paneBtnSales;

    @FXML
    private Pane paneBtnStock;

    @FXML
    private Pane paneBtnSupplier;

    @FXML
    public Pane popUpPane;

    @FXML
    public Pane orderPopUpPane;

    public static String user;
    private boolean dashboardButtonSelected = false;
    private boolean salesButtonSelected = false;
    private boolean stockButtonSelected = false;
    private boolean customerButtonSelected = false;
    private boolean employeeButtonSelected = false;
    private boolean supplierBttonSelected = false;
    private static GlobalFormController controller;

    public GlobalFormController() {
        controller = this;
    }

    public static GlobalFormController getInstance() {
        return controller;
    }

    private void buttonUnSelected() {
        dashboardButtonSelected = false;
        salesButtonSelected = false;
        stockButtonSelected = false;
        customerButtonSelected = false;
        employeeButtonSelected = false;
        supplierBttonSelected = false;
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        buttonUnSelected();
        customerButtonSelected = true;

        btnUnselected(paneBtnDashboard, lblDashboard, imgDashboard, "dashboardIcon.png");
        btnUnselected(paneBtnSales, lblSales, imgSales, "salesIcon.png");
        btnUnselected(paneBtnStock, lblStock, imgStock, "stockIcon.png");
        btnSelected(paneBtnCustomer, lblCustomer, imgCustomer, "customerIcon2.png");
        btnUnselected(paneBtnEmployee, lblEmployee, imgEmployee, "employeeIcon.png");
        btnUnselected(paneBtnSupplier, lblSupplier, imgSupplier, "supplierIcon.png");

        Navigation.switchPaging(pagingPane, "customerManageForm.fxml");
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        buttonUnSelected();
        dashboardButtonSelected = true;

        btnSelected(paneBtnDashboard, lblDashboard, imgDashboard, "dashboardIcon2.png");
        btnUnselected(paneBtnSales, lblSales, imgSales, "salesIcon.png");
        btnUnselected(paneBtnStock, lblStock, imgStock, "stockIcon.png");
        btnUnselected(paneBtnCustomer, lblCustomer, imgCustomer, "customerIcon.png");
        btnUnselected(paneBtnEmployee, lblEmployee, imgEmployee, "employeeIcon.png");
        btnUnselected(paneBtnSupplier, lblSupplier, imgSupplier, "supplierIcon.png");

        Navigation.switchPaging(pagingPane, "dashboardForm.fxml");
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        buttonUnSelected();
        employeeButtonSelected = true;

        btnUnselected(paneBtnDashboard, lblDashboard, imgDashboard, "dashboardIcon.png");
        btnUnselected(paneBtnSales, lblSales, imgSales, "salesIcon.png");
        btnUnselected(paneBtnStock, lblStock, imgStock, "stockIcon.png");
        btnUnselected(paneBtnCustomer, lblCustomer, imgCustomer, "customerIcon.png");
        btnSelected(paneBtnEmployee, lblEmployee, imgEmployee, "employeeIcon2.png");
        btnUnselected(paneBtnSupplier, lblSupplier, imgSupplier, "supplierIcon.png");

        Navigation.switchPaging(pagingPane, "employeeManageForm.fxml");
    }

    @FXML
    void btnSalesOnAction(ActionEvent event) throws IOException {
        buttonUnSelected();
        salesButtonSelected = true;

        btnUnselected(paneBtnDashboard, lblDashboard, imgDashboard, "dashboardIcon.png");
        btnSelected(paneBtnSales, lblSales, imgSales, "salesIcon2.png");
        btnUnselected(paneBtnStock, lblStock, imgStock, "stockIcon.png");
        btnUnselected(paneBtnCustomer, lblCustomer, imgCustomer, "customerIcon.png");
        btnUnselected(paneBtnEmployee, lblEmployee, imgEmployee, "employeeIcon.png");
        btnUnselected(paneBtnSupplier, lblSupplier, imgSupplier, "supplierIcon.png");

        Navigation.switchPaging(pagingPane, "customerOrderManageForm.fxml");
    }

    @FXML
    void btnStockOnAction(ActionEvent event) throws IOException {
        buttonUnSelected();
        stockButtonSelected = true;

        btnUnselected(paneBtnDashboard, lblDashboard, imgDashboard, "dashboardIcon.png");
        btnUnselected(paneBtnSales, lblSales, imgSales, "salesIcon.png");
        btnSelected(paneBtnStock, lblStock, imgStock, "stockIcon2.png");
        btnUnselected(paneBtnCustomer, lblCustomer, imgCustomer, "customerIcon.png");
        btnUnselected(paneBtnEmployee, lblEmployee, imgEmployee, "employeeIcon.png");
        btnUnselected(paneBtnSupplier, lblSupplier, imgSupplier, "supplierIcon.png");

        Navigation.switchPaging(pagingPane, "productStockForm.fxml");
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        buttonUnSelected();
        supplierBttonSelected = true;

        btnUnselected(paneBtnDashboard, lblDashboard, imgDashboard, "dashboardIcon.png");
        btnUnselected(paneBtnSales, lblSales, imgSales, "salesIcon.png");
        btnUnselected(paneBtnStock, lblStock, imgStock, "stockIcon.png");
        btnUnselected(paneBtnCustomer, lblCustomer, imgCustomer, "customerIcon.png");
        btnUnselected(paneBtnEmployee, lblEmployee, imgEmployee, "employeeIcon.png");
        btnSelected(paneBtnSupplier, lblSupplier, imgSupplier, "supplierIcon2.png");

        Navigation.switchPaging(pagingPane, "supplierManageForm.fxml");
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        logoutBtnSelected(paneBtnLogOut, lblLogOut, imgLogOut, "logoutIcon.png");
        Navigation.close(event);
        Navigation.switchNavigation("loginForm.fxml", event);
    }

    @FXML
    void btnLogOutOnMouseEntered(MouseEvent event) {
        logoutBtnSelected(paneBtnLogOut, lblLogOut, imgLogOut, "logoutIcon.png");
    }

    @FXML
    void btnLogOutOnMouseExited(MouseEvent event) {
        btnUnselected(paneBtnLogOut, lblLogOut, imgLogOut, "logoutIcon2.png");
    }

    @FXML
    void btnDashboardOnMouseEntered(MouseEvent event) {
        if(!dashboardButtonSelected) allBtnHoverCss(paneBtnDashboard,
                lblDashboard, imgDashboard, "dashboardIcon2.png");
    }

    @FXML
    void btnDashboardOnMouseExited(MouseEvent event) {
        if(!dashboardButtonSelected) btnUnselected(paneBtnDashboard,
                lblDashboard, imgDashboard, "dashboardIcon.png");
    }

    @FXML
    void btnSalesOnMouseEntered(MouseEvent event) {
        if(!salesButtonSelected) allBtnHoverCss(paneBtnSales,
                lblSales, imgSales, "salesIcon2.png");
    }

    @FXML
    void btnSalesOnMouseExited(MouseEvent event) {
        if(!salesButtonSelected) btnUnselected(paneBtnSales,
                lblSales, imgSales, "salesIcon.png");
    }

    @FXML
    void btnStockOnMouseEntered(MouseEvent event) {
        if(!stockButtonSelected) allBtnHoverCss(paneBtnStock,
                lblStock, imgStock, "stockIcon2.png");
    }

    @FXML
    void btnStockOnMouseExited(MouseEvent event) {
        if(!stockButtonSelected) btnUnselected(paneBtnStock,
                lblStock, imgStock, "stockIcon.png");
    }

    @FXML
    void btnCustomerOnMouseEntered(MouseEvent event) {
        if(!customerButtonSelected) allBtnHoverCss(paneBtnCustomer,
                lblCustomer, imgCustomer, "customerIcon2.png");
    }

    @FXML
    void btnCustomerOnMouseExited(MouseEvent event) {
        if(!customerButtonSelected) btnUnselected(paneBtnCustomer,
                lblCustomer, imgCustomer, "customerIcon.png");
    }

    @FXML
    void btnEmployeeOnMouseEntered(MouseEvent event) {
        if(!employeeButtonSelected) allBtnHoverCss(paneBtnEmployee,
                lblEmployee, imgEmployee, "employeeIcon2.png");
    }

    @FXML
    void btnEmployeeOnMouseExited(MouseEvent event) {
        if(!employeeButtonSelected) btnUnselected(paneBtnEmployee,
                lblEmployee, imgEmployee, "employeeIcon.png");
    }

    @FXML
    void btnSupplierOnMouseEntered(MouseEvent event) {
        if(!supplierBttonSelected) allBtnHoverCss(paneBtnSupplier,
                lblSupplier, imgSupplier, "supplierIcon2.png");
    }

    @FXML
    void btnSupplierOnMouseExited(MouseEvent event) {
        if(!supplierBttonSelected) btnUnselected(paneBtnSupplier,
                lblSupplier, imgSupplier, "supplierIcon.png");
    }

    void btnSelected(Pane pane, Label label, ImageView imageView, String path) {
        pane.setStyle(
                "-fx-background-color: #FFDDC5;" +
                        "-fx-background-radius: 12px;");
        label.setStyle("-fx-text-fill: #885D40;" +
                "-fx-font-size: 16px");
        imageView.setImage(new Image("assests/icon/" + path));
    }

    void btnUnselected(Pane pane, Label label, ImageView imageView, String path) {
        pane.setStyle(
                "-fx-background-radius: 12px;");
        label.setStyle("-fx-font-weight: 500;" +
                "-fx-font-size: 16px;" +
                "-fx-text-fill: #A3A3A3;");
        imageView.setImage(new Image("assests/icon/" + path));
    }

    void logoutBtnSelected(Pane pane, Label label, ImageView imageView, String path) {
        pane.setStyle(
                "-fx-background-color: #FFE1E1;" +
                        "-fx-background-radius: 12px;");
        label.setStyle("-fx-text-fill: #FF2626;" +
                "-fx-font-size: 16px");
        imageView.setImage(new Image("assests/icon/" + path));
    }

    void allBtnHoverCss(Pane pane, Label label, ImageView imageView, String path){
        pane.setStyle(
                "-fx-background-color: #E8E8E8;" +
                        "-fx-background-radius: 12px;");
        label.setStyle("-fx-text-fill: #885D40;" +
                "-fx-font-size: 16px");
        imageView.setImage(new Image("assests/icon/" + path));
    }

    private void updateClock() {
        lblTime.setText(DateTimeUtil.timeNow());
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dashboardButtonSelected = true;
        btnSelected(paneBtnDashboard, lblDashboard, imgDashboard, "dashboardIcon2.png");

        lblUser.setText(UserModel.getRole(user));

        try {
            Navigation.switchPaging(pagingPane, "dashboardForm.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateClock()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        lblDate.setText(DateTimeUtil.dateNowFormatted());
    }
}
