package lk.grb.ceylonPottersPaletteLayered.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.grb.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.grb.ceylonPottersPaletteLayered.bo.custom.SupplierBO;
import lk.grb.ceylonPottersPaletteLayered.dto.SupplierDto;
import lk.grb.ceylonPottersPaletteLayered.util.Navigation;
import lk.grb.ceylonPottersPaletteLayered.util.StyleUtil;

import java.io.IOException;
import java.sql.SQLException;

public class SupplierManageBarFormController {

    @FXML
    private Text contact_No;

    @FXML
    private ImageView deleteImg;

    @FXML
    private Text email;

    @FXML
    private Text id;

    @FXML
    private Text name;

    @FXML
    private ImageView updateImg;

    @FXML
    private ImageView viewImg;

    SupplierBO supplierBO =
            (SupplierBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.SUPPLIER);

    @FXML
    void deleteOnMouseClick(MouseEvent event) throws IOException {
        ConfirmationPopUpFormController.setId(id.getText());
        Navigation.imgPopUpBackground("confirmationPopUpForm.fxml");
    }

    @FXML
    void deleteOnMouseEntered(MouseEvent event) {
        StyleUtil.deleteImgSelected(deleteImg);
    }

    @FXML
    void deleteOnMouseExited(MouseEvent event) {
        StyleUtil.deleteImgUnselected(deleteImg);
    }

    @FXML
    void updateOnMouseClick(MouseEvent event) throws IOException {
        SupplierUpdatePopUpFormController.supplierId = id.getText();
        Navigation.imgPopUpBackground("supplierUpdatePopUpForm.fxml");
    }

    @FXML
    void updateOnMouseEntered(MouseEvent event) {
        StyleUtil.updateImgSelected(updateImg);
    }

    @FXML
    void updateOnMouseExited(MouseEvent event) {
        StyleUtil.updateImgUnselected(updateImg);
    }

    @FXML
    void viewDetailsOnMouseClick(MouseEvent event) throws IOException {
        SupplierViewPopUpFormController.supplierId = id.getText();
        Navigation.imgPopUpBackground("supplierViewPopUpForm.fxml");
    }

    @FXML
    void viewOnMouseEntered(MouseEvent event) {
        StyleUtil.viewImgSelected(viewImg);
    }

    @FXML
    void viewOnMouseExited(MouseEvent event) {
        StyleUtil.viewImgUnselected(viewImg);
    }

    public void setData(String id) {
        try {
            SupplierDto supplierDto = supplierBO.getSupplierData(id);

            this.id.setText(supplierDto.getSupplier_Id());
            name.setText(supplierDto.getName());
            email.setText(supplierDto.getEmail());
            contact_No.setText(supplierDto.getContact_No());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
