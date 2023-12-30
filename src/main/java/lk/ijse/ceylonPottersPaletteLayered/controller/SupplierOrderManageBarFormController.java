package lk.ijse.ceylonPottersPaletteLayered.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.SupplierOrderBO;
import lk.ijse.ceylonPottersPaletteLayered.db.DbConnection;
import lk.ijse.ceylonPottersPaletteLayered.util.Navigation;
import lk.ijse.ceylonPottersPaletteLayered.dto.SupplierDto;
import lk.ijse.ceylonPottersPaletteLayered.dto.SupplierOrderDto;
import lk.ijse.ceylonPottersPaletteLayered.util.StyleUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;

public class SupplierOrderManageBarFormController {

    @FXML
    private Text amount;

    @FXML
    private Text date;

    @FXML
    private Text id;

    @FXML
    private Text supplierId;

    @FXML
    private Text time;

    @FXML
    private ImageView viewImg;

    @FXML
    private ImageView reportImg;

    SupplierOrderBO supplierOrderBO =
            (SupplierOrderBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.SUPPLIER_ORDER);

    @FXML
    void viewDetailsOnMouseClick(MouseEvent event) throws IOException {
        SupplierOrderViewPopUpFormController.supplierOrderId = id.getText();
        SupplierOrderViewPopUpFormController.supplierId = supplierId.getText();
        Navigation.imgPopUpBackground("supplierOrderViewPopUpForm.fxml");
    }

    @FXML
    void viewOnMouseEntered(MouseEvent event) {
        StyleUtil.viewImgSelected(viewImg);
    }

    @FXML
    void viewOnMouseExited(MouseEvent event) {
        StyleUtil.viewImgUnselected(viewImg);
    }

    @FXML
    void reportOnMouseClick(MouseEvent event) throws SQLException, JRException {
        SupplierOrderDto supplierOrderDto = supplierOrderBO.getSupplierOrderData(id.getText());
        SupplierDto supplierDto = supplierOrderBO.getSupplierData(supplierOrderDto.getSupplier_Id());

        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("date", supplierOrderDto.getDate());
        hashMap.put("time", supplierOrderDto.getTime());
        hashMap.put("orderId", supplierOrderDto.getSupplier_Order_Id());
        hashMap.put("supplierId", supplierOrderDto.getSupplier_Id());
        hashMap.put("supplierName", supplierDto.getName());
        hashMap.put("contactNo", supplierDto.getContact_No());
        hashMap.put("total", String.valueOf(supplierOrderDto.getTotal_Price()));

        InputStream resourceAsStream = getClass().getResourceAsStream("/report/supplierOrderReport.jrxml");

        JasperDesign load = JRXmlLoader.load(resourceAsStream);

        JRDesignQuery jrDesignQuery = getJrDesignQuery();

        load.setQuery(jrDesignQuery);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
        JasperExportManager.exportReportToPdfFile(jasperPrint,"/home/gayanuka/Documents/Jasper Reports/Supplier_Report_PDFs/"+id.getText()+".pdf");
    }

    private JRDesignQuery getJrDesignQuery() {
        JRDesignQuery jrDesignQuery = new JRDesignQuery();
        jrDesignQuery.setText("SELECT\n" +
                "    sod.item_Id,\n" +
                "    i.description,\n" +
                "    sod.item_Qty,\n" +
                "    i.unit_Price\n" +
                "FROM\n" +
                "    supplier_Order so\n" +
                "JOIN\n" +
                "    supplier_Order_Detail sod ON so.supplier_Order_Id = sod.supplier_Order_Id\n" +
                "JOIN\n" +
                "    item_Stock i ON i.item_Id = sod.item_Id\n" +
                "WHERE\n" +
                "    so.supplier_Order_Id = '"+ id.getText() +"'");
        return jrDesignQuery;
    }

    @FXML
    void reportOnMouseEntered(MouseEvent event) {
        StyleUtil.viewReportImgSelected(reportImg);
    }

    @FXML
    void reportOnMouseExited(MouseEvent event) {
        StyleUtil.viewReportImgUnselected(reportImg);
    }

    public void setData(String id) {
        try {
            SupplierOrderDto supplierOrderDto = supplierOrderBO.getSupplierOrderData(id);

            this.id.setText(supplierOrderDto.getSupplier_Order_Id());
            supplierId.setText(supplierOrderDto.getSupplier_Id());
            amount.setText(String.valueOf(supplierOrderDto.getTotal_Price()));
            date.setText(supplierOrderDto.getDate());
            time.setText(supplierOrderDto.getTime());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
