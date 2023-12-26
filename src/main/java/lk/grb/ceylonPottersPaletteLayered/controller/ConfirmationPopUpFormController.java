package lk.grb.ceylonPottersPaletteLayered.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.grb.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.grb.ceylonPottersPaletteLayered.bo.custom.ConfirmationPopUpBO;
import lk.grb.ceylonPottersPaletteLayered.util.Navigation;
import lk.grb.ceylonPottersPaletteLayered.util.StyleUtil;

import java.sql.SQLException;

public class ConfirmationPopUpFormController {

    @FXML
    private Pane cancelBtnPane;

    @FXML
    private Pane closeIconPane;

    @FXML
    private Pane confirmBtnPane;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Label lblCancel;

    private static String id;

    ConfirmationPopUpBO confirmationPopUpBO =
            (ConfirmationPopUpBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.CONFIRMATION_POPUP);

    public static void setId(String id) {
        ConfirmationPopUpFormController.id = id;
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) throws SQLException {
        if (id.startsWith("E")) {
            confirmationPopUpBO.deleteEmployee(id);
            EmployeeManageFormController.getInstance().allEmployeeId();
        } else if (id.startsWith("S")) {
            confirmationPopUpBO.deleteSupplier(id);
            SupplierManageFormController.getInstance().allSupplierId();
        } else if (id.startsWith("C")) {
            confirmationPopUpBO.deleteCustomer(id);
            CustomerManageFormController.getInstance().allCustomerId();
        } else if (id.startsWith("A")) {
            confirmationPopUpBO.deleteAttendance(id);
            EmployeeAttendanceFormController.getInstance().allAttendanceId();
        } else if (id.startsWith("P")) {
            confirmationPopUpBO.deleteProductStock(id);
            ProductStockFormController.getInstance().allProductId();
        } else if (id.startsWith("I")) {
            confirmationPopUpBO.deleteItemStock(id);
            ItemStockFormController.getInstance().allItemId();
        }
        Navigation.closePane();
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

    @FXML
    void btnConfirmOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(confirmBtnPane);
    }

    @FXML
    void btnConfirmOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(confirmBtnPane);
    }
}
