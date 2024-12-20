package lk.ijse.ceylonPottersPaletteLayered.controller;

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
import lk.ijse.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.UserBO;
import lk.ijse.ceylonPottersPaletteLayered.util.Navigation;
import lk.ijse.ceylonPottersPaletteLayered.util.DateTimeUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    private boolean supplierButtonSelected = false;

    private static GlobalFormController controller;

    public GlobalFormController() {
        controller = this;
    }

    public static GlobalFormController getInstance() {
        return controller;
    }

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        buttonUnSelected();
        customerButtonSelected = true;
        unSelectedButtons();
        selectedButton(paneBtnCustomer, lblCustomer, imgCustomer, "customerIcon2.png");

        Navigation.switchPaging(pagingPane, "customerManageForm.fxml");
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        buttonUnSelected();
        dashboardButtonSelected = true;
        unSelectedButtons();
        selectedButton(paneBtnDashboard, lblDashboard, imgDashboard, "dashboardIcon2.png");

        Navigation.switchPaging(pagingPane, "dashboardForm.fxml");
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        buttonUnSelected();
        employeeButtonSelected = true;
        unSelectedButtons();
        selectedButton(paneBtnEmployee, lblEmployee, imgEmployee, "employeeIcon2.png");

        Navigation.switchPaging(pagingPane, "employeeManageForm.fxml");
    }

    @FXML
    void btnSalesOnAction(ActionEvent event) throws IOException {
        buttonUnSelected();
        salesButtonSelected = true;
        unSelectedButtons();
        selectedButton(paneBtnSales, lblSales, imgSales, "salesIcon2.png");

        Navigation.switchPaging(pagingPane, "customerOrderManageForm.fxml");
    }

    @FXML
    void btnStockOnAction(ActionEvent event) throws IOException {
        buttonUnSelected();
        stockButtonSelected = true;
        unSelectedButtons();
        selectedButton(paneBtnStock, lblStock, imgStock, "stockIcon2.png");

        Navigation.switchPaging(pagingPane, "productStockForm.fxml");
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        buttonUnSelected();
        supplierButtonSelected = true;
        unSelectedButtons();
        selectedButton(paneBtnSupplier, lblSupplier, imgSupplier, "supplierIcon2.png");

        Navigation.switchPaging(pagingPane, "supplierManageForm.fxml");
    }

    private void buttonUnSelected() {
        dashboardButtonSelected = false;
        salesButtonSelected = false;
        stockButtonSelected = false;
        customerButtonSelected = false;
        employeeButtonSelected = false;
        supplierButtonSelected = false;
    }

    private void unSelectedButtons() {
        btnUnselected(paneBtnDashboard, lblDashboard, imgDashboard, "dashboardIcon.png");
        btnUnselected(paneBtnSales, lblSales, imgSales, "salesIcon.png");
        btnUnselected(paneBtnStock, lblStock, imgStock, "stockIcon.png");
        btnUnselected(paneBtnCustomer, lblCustomer, imgCustomer, "customerIcon.png");
        btnUnselected(paneBtnEmployee, lblEmployee, imgEmployee, "employeeIcon.png");
        btnUnselected(paneBtnSupplier, lblSupplier, imgSupplier, "supplierIcon.png");
    }

    private void selectedButton(Pane pane, Label label, ImageView imageView, String path) {
        btnSelected(pane, label, imageView, path);
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        logoutBtnSelected(paneBtnLogOut, lblLogOut, imgLogOut, "logoutIconRed.png");
        Navigation.close(event);
        Navigation.switchNavigation("loginForm.fxml", event);
    }

    @FXML
    void btnLogOutOnMouseEntered(MouseEvent event) {
        logoutBtnSelected(paneBtnLogOut, lblLogOut, imgLogOut, "logoutIconRed.png");
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
        if(!supplierButtonSelected) allBtnHoverCss(paneBtnSupplier,
                lblSupplier, imgSupplier, "supplierIcon2.png");
    }

    @FXML
    void btnSupplierOnMouseExited(MouseEvent event) {
        if(!supplierButtonSelected) btnUnselected(paneBtnSupplier,
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

    private void setTimeLine() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateClock()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void setLabelValueAndNavigate() {
        try {
            lblUser.setText(userBO.getUserRole(user));
            Navigation.switchPaging(pagingPane, "dashboardForm.fxml");
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dashboardButtonSelected = true;
        btnSelected(paneBtnDashboard, lblDashboard, imgDashboard, "dashboardIcon2.png");

        setLabelValueAndNavigate();
        setTimeLine();
        lblDate.setText(DateTimeUtil.dateNowFormatted());
    }
}
