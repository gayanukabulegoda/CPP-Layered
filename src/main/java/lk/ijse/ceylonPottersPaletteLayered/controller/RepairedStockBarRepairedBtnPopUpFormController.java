package lk.ijse.ceylonPottersPaletteLayered.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.ijse.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.RepairStockBO;
import lk.ijse.ceylonPottersPaletteLayered.util.Navigation;
import lk.ijse.ceylonPottersPaletteLayered.util.StyleUtil;

import java.sql.SQLException;

public class RepairedStockBarRepairedBtnPopUpFormController {

    @FXML
    private Pane closeIconPane;

    @FXML
    private ImageView imgCloseIcon;

    @FXML
    private Pane repairedBtnPane;

    @FXML
    private TextField txtEnterQuantity;

    RepairStockBO repairStockBO =
            (RepairStockBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.REPAIR_STOCK);

    @FXML
    void btnCloseIconOnAction(ActionEvent event) {
        Navigation.closePane();
    }

    @FXML
    void btnRepairedOnAction(ActionEvent event) {

        try {
            boolean isUpdated = repairStockBO.
                    removeRepairStock(RepairedStockBarFormController.productId, txtEnterQuantity.getText());

            if (isUpdated) {
                Navigation.closePane();
                RepairedStockFormController.getInstance().allRepairedProductId();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtEnterQuantityOnAction(ActionEvent event) {
        btnRepairedOnAction(event);
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
    void btnRepairedOnMouseEntered(MouseEvent event) {
        StyleUtil.confirmORSaveBtnSelected(repairedBtnPane);
    }

    @FXML
    void btnRepairedOnMouseExited(MouseEvent event) {
        StyleUtil.confirmORSaveBtnUnselected(repairedBtnPane);
    }
}
