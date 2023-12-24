package lk.grb.ceylonPottersPaletteLayered.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.grb.ceylonPottersPalette.dto.ProductStockDto;
import lk.grb.ceylonPottersPalette.dto.RepairStockDto;
import lk.grb.ceylonPottersPalette.model.ProductStockModel;
import lk.grb.ceylonPottersPalette.model.RepairStockModel;
import lk.grb.ceylonPottersPalette.util.Navigation;
import lk.grb.ceylonPottersPalette.util.StyleUtil;

import java.io.IOException;
import java.sql.SQLException;

public class RepairedStockBarFormController {

    RepairStockModel repairStockModel = new RepairStockModel();
    ProductStockModel productStockModel = new ProductStockModel();

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
            RepairStockDto repairStockDto = repairStockModel.getData(id);
            ProductStockDto productStockDto = productStockModel.getData(id);

            this.id.setText(productStockDto.getProduct_Id());
            description.setText(productStockDto.getDescription());
            qtyToRepair.setText(repairStockDto.getQty_To_Repair());
            category.setText(productStockDto.getCategory());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
