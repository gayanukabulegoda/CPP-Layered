package lk.grb.ceylonPottersPaletteLayered.util;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class StyleUtil {

    public static void closeIconBtnSelected(Pane pane, ImageView imageView) {
        pane.setStyle(
                "-fx-background-color: #FFE1E1;" +
                        "-fx-background-radius: 12px;");
        imageView.setImage(new Image("assests/icon/closeIcon2.png"));
    }

    public static void closeIconBtnUnselected(Pane pane, ImageView imageView) {
        pane.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 12px;");
        imageView.setImage(new Image("assests/icon/closeIcon.png"));
    }

    public static void addNewCustomerORSupplierBtnSelected(Pane pane, ImageView imageView) {
        pane.setStyle(
                "-fx-background-color: #FFDDC5;" +
                        "-fx-background-radius: 12px;");
        imageView.setImage(new Image("assests/icon/addIcon2.png"));
    }

    public static void addNewCustomerORSupplierBtnUnselected(Pane pane, ImageView imageView) {
        pane.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 12px;");
        imageView.setImage(new Image("assests/icon/addIcon.png"));
    }

    public static void cancelBtnSelected(Pane pane, Label label) {
        pane.setStyle(
                "-fx-background-color: #FFDDC5;" +
                        "-fx-background-radius: 12px;");
        label.setStyle("-fx-text-fill: #9E4B14;");
    }

    public static void cancelBtnUnselected(Pane pane, Label label) {
        pane.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 12px;");
        label.setStyle("-fx-text-fill: #c56e33;");
    }

    public static void confirmORSaveBtnSelected(Pane pane) {
        pane.setStyle(
                "-fx-background-color: #885D40;" +
                        "-fx-background-radius: 12px;");
    }

    public static void confirmORSaveBtnUnselected(Pane pane) {
        pane.setStyle(
                "-fx-background-color: #C56E33;" +
                        "-fx-background-radius: 12px;");
    }

    public static void updateImgSelected(ImageView imageView) {
        imageView.setImage(new Image("assests/icon/editIcon2.png"));
        imageView.setScaleX(1.3);
        imageView.setScaleY(1.3);
    }

    public static void updateImgUnselected(ImageView imageView) {
        imageView.setImage(new Image("assests/icon/editIcon.png"));
        imageView.setScaleX(1.0);
        imageView.setScaleY(1.0);
    }

    public static void deleteImgSelected(ImageView imageView) {
        imageView.setImage(new Image("assests/icon/deleteIcon2.png"));
        imageView.setScaleX(1.3);
        imageView.setScaleY(1.3);
    }

    public static void deleteImgUnselected(ImageView imageView) {
        imageView.setImage(new Image("assests/icon/deleteIcon.png"));
        imageView.setScaleX(1.0);
        imageView.setScaleY(1.0);
    }

    public static void viewImgSelected(ImageView imageView) {
        imageView.setImage(new Image("assests/icon/viewIcon2.png"));
        imageView.setScaleX(1.2);
        imageView.setScaleY(1.2);
    }

    public static void viewImgUnselected(ImageView imageView) {
        imageView.setImage(new Image("assests/icon/viewIcon.png"));
        imageView.setScaleX(1.0);
        imageView.setScaleY(1.0);
    }

    public static void viewReportImgSelected(ImageView imageView) {
        imageView.setImage(new Image("assests/icon/reportIcon2.png"));
        imageView.setScaleX(1.3);
        imageView.setScaleY(1.3);
    }

    public static void viewReportImgUnselected(ImageView imageView) {
        imageView.setImage(new Image("assests/icon/reportIcon.png"));
        imageView.setScaleX(1.0);
        imageView.setScaleY(1.0);
    }

    public static void repairedBtnImgSelected(ImageView imageView) {
        imageView.setImage(new Image("assests/img/btnRepairedProducts2.png"));
        imageView.setScaleX(1.2);
        imageView.setScaleY(1.2);
    }

    public static void repairedBtnImgImgUnselected(ImageView imageView) {
        imageView.setImage(new Image("assests/img/btnRepairedProducts.png"));
        imageView.setScaleX(1.0);
        imageView.setScaleY(1.0);
    }

    public static void repairedUpdateBtnImgSelected(ImageView imageView) {
        imageView.setImage(new Image("assests/img/btnRepairedUpdate2.png"));
        imageView.setScaleX(1.2);
        imageView.setScaleY(1.2);
    }

    public static void repairedUpdateBtnImgUnselected(ImageView imageView) {
        imageView.setImage(new Image("assests/img/btnRepairedUpdate.png"));
        imageView.setScaleX(1.0);
        imageView.setScaleY(1.0);
    }

    public static void signUpOrLogInBtnSelected(JFXButton button) {
        button.setStyle(
                "-fx-background-color: #885D40;" +
                        "-fx-background-radius: 12px;");
    }

    public static void signUpOrLogInBtnUnselected(JFXButton button) {
        button.setStyle(
                "-fx-background-color: #C56E33;" +
                        "-fx-background-radius: 12px;");
    }

    public static void otpBackBtnSelected(Pane pane, ImageView imageView) {
        pane.setStyle(
                "-fx-background-color: #C56E33;" +
                        "-fx-background-radius: 12px;" +
                        "-fx-border-color: #C56E33;" +
                        "-fx-border-radius: 12px;" +
                        "-fx-border-width: 2px;");

        imageView.setImage(new Image("assests/icon/logoutIcon3.png"));
    }

    public static void otpBackBtnUnselected(Pane pane, ImageView imageView) {
        pane.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 12px;" +
                        "-fx-border-color: #C56E33;" +
                        "-fx-border-radius: 12px;" +
                        "-fx-border-width: 2px;");

        imageView.setImage(new Image("assests/icon/logoutIcon.png"));
    }

    public static void hyperLinkSelected(Hyperlink hyperlink) {
        hyperlink.setStyle(
                "-fx-text-fill: #1351CB;");

        hyperlink.setScaleX(1.1);
        hyperlink.setScaleY(1.1);
    }

    public static void hyperLinkUnselected(Hyperlink hyperlink) {
        hyperlink.setStyle(
                "-fx-text-fill: #151619;");

        hyperlink.setScaleX(1.0);
        hyperlink.setScaleY(1.0);
    }

    public static void hyperLinkForgotPwUnselected(Hyperlink hyperlink) {
        hyperlink.setStyle(
                "-fx-text-fill: #6a6b6b;");

        hyperlink.setScaleX(1.0);
        hyperlink.setScaleY(1.0);
    }

    public static void powerOffOrBackBtnSelected(ImageView imageView) {
        imageView.setScaleX(1.1);
        imageView.setScaleY(1.1);
    }

    public static void powerOffOrBackBtnUnselected(ImageView imageView) {
        imageView.setScaleX(1.0);
        imageView.setScaleY(1.0);
    }

    public static void dashboardCustomerAndSupplierBtnUnselected(Pane pane) {
        pane.setStyle("-fx-border-color: #E8E8E8;" +
                "-fx-background-color: white;" +
                "-fx-border-radius: 20px;" +
                "-fx-background-radius: 20px");
    }

    public static void credentialOrEmployeeManageBtnSelected(Pane pane) {
        pane.setStyle("-fx-border-color: #cccccc;" +
                            "-fx-background-color: white;" +
                            "-fx-border-radius: 20px;" +
                            "-fx-background-radius: 20px");
    }

    public static void credentialOrEmployeeManageUnselected(Pane pane) {
        pane.setStyle("-fx-border-color: white;" +
                            "-fx-background-color: white;" +
                            "-fx-border-radius: 20px;" +
                            "-fx-background-radius: 20px");
    }

    public static void addBtnSelected(Pane pane, Label label, ImageView imageView) {
        pane.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-border-color: #C56E33;" +
                        "-fx-border-radius: 12px;" +
                        "-fx-background-radius: 12px;");

        label.setStyle("-fx-text-fill: #885D40");

        imageView.setImage(new Image("assests/icon/addIcon2.png"));
    }

    public static void addBtnUnselected(Pane pane, Label label, ImageView imageView) {
        pane.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 12px;");

        label.setStyle("-fx-text-fill: #3D3E3E");

        imageView.setImage(new Image("assests/icon/addIcon.png"));
    }

    public static void refreshBtnSelected(Pane pane, ImageView imageView) {
        pane.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-border-color: #C56E33;" +
                        "-fx-border-radius: 12px;" +
                        "-fx-background-radius: 12px;");

        imageView.setImage(new Image("assests/icon/refreshTableIcon2.png"));
    }

    public static void refreshBtnUnselected(Pane pane, ImageView imageView) {
        pane.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 12px;");

        imageView.setImage(new Image("assests/icon/refreshTableIcon.png"));
    }

    public static void qrBtnSelected(Pane pane, ImageView imageView) {
        pane.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-border-color: #C56E33;" +
                        "-fx-border-radius: 12px;" +
                        "-fx-background-radius: 12px;");

        imageView.setImage(new Image("assests/icon/qrScanIcon2.png"));
    }

    public static void qrBtnUnselected(Pane pane, ImageView imageView) {
        pane.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 12px;");

        imageView.setImage(new Image("assests/icon/qrScanIcon.png"));
    }

    public static void searchBarRed(Pane pane) {
        pane.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-border-color: red;" +
                        "-fx-border-radius: 12px;" +
                        "-fx-background-radius: 12px;");
    }

    public static void searchBarTransparent(Pane pane) {
        pane.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-border-color: transparent;" +
                        "-fx-border-radius: 12px;" +
                        "-fx-background-radius: 12px;");
    }
}
