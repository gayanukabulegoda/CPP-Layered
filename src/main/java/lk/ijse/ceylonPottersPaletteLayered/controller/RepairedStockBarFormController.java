package lk.ijse.ceylonPottersPaletteLayered.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.RepairStockBO;
import lk.ijse.ceylonPottersPaletteLayered.util.Navigation;
import lk.ijse.ceylonPottersPaletteLayered.dto.ProductStockDto;
import lk.ijse.ceylonPottersPaletteLayered.dto.RepairStockDto;
import lk.ijse.ceylonPottersPaletteLayered.util.StyleUtil;

import java.io.IOException;
import java.sql.SQLException;

public class RepairedStockBarFormController {

    @FXML
    private Text category;

    @FXML
    private Text description;

    @FXML
    private Text id;

    @FXML
    private ImageView imgRepaired;

    @FXML
    private ImageView imgUpdate;

    @FXML
    public Text qtyToRepair;

    public static String productId;

    RepairStockBO repairStockBO =
            (RepairStockBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.REPAIR_STOCK);

    @FXML
    void imgRepairedOnMouseClicked(MouseEvent event) throws IOException {
        productId = id.getText();
        Navigation.imgPopUpBackground("repairedStockBarRepairedBtnPopUpForm.fxml");
    }

    @FXML
    void imgRepairedOnMouseEntered(MouseEvent event) {
        StyleUtil.repairedBtnImgSelected(imgRepaired);
    }

    @FXML
    void imgRepairedOnMouseExited(MouseEvent event) {
        StyleUtil.repairedBtnImgImgUnselected(imgRepaired);
    }

    @FXML
    void imgUpdateOnMouseClicked(MouseEvent event) throws IOException {
        productId = id.getText();
        Navigation.imgPopUpBackground("repairedStockBarUpdateBtnPopUpForm.fxml");
    }

    @FXML
    void imgUpdateOnMouseEntered(MouseEvent event) {
        StyleUtil.repairedUpdateBtnImgSelected(imgUpdate);
    }

    @FXML
    void imgUpdateOnMouseExited(MouseEvent event) {
        StyleUtil.repairedUpdateBtnImgUnselected(imgUpdate);
    }

    public void setData(String id) {
        try {
            RepairStockDto repairStockDto = repairStockBO.getRepairStockData(id);
            ProductStockDto productStockDto = repairStockBO.getProductStockData(id);

            this.id.setText(productStockDto.getProduct_Id());
            description.setText(productStockDto.getDescription());
            qtyToRepair.setText(repairStockDto.getQty_To_Repair());
            category.setText(productStockDto.getCategory());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
