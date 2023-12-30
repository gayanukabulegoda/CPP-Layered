package lk.ijse.ceylonPottersPaletteLayered.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.ceylonPottersPaletteLayered.bo.BOFactory;
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.CustomerOrderBO;
import lk.ijse.ceylonPottersPaletteLayered.db.DbConnection;
import lk.ijse.ceylonPottersPaletteLayered.util.Navigation;
import lk.ijse.ceylonPottersPaletteLayered.dto.CustomerDto;
import lk.ijse.ceylonPottersPaletteLayered.dto.CustomerOrderDto;
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

public class CustomerOrderManageBarFormController {

    @FXML
    private Text amount;

    @FXML
    private Text customerId;

    @FXML
    private Text date;

    @FXML
    private Text id;

    @FXML
    private Text time;

    @FXML
    private ImageView viewImg;

    @FXML
    private ImageView reportImg;

    CustomerOrderBO customerOrderBO =
            (CustomerOrderBO) BOFactory.getBoFactory().
                    getBO(BOFactory.BOTypes.CUSTOMER_ORDER);

    @FXML
    void viewDetailsOnMouseClick(MouseEvent event) throws IOException {
        CustomerOrderViewPopUpFormController.customerOrderId = id.getText();
        CustomerOrderViewPopUpFormController.customerId = customerId.getText();
        Navigation.imgPopUpBackground("customerOrderViewPopUpForm.fxml");
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
        CustomerOrderDto customerOrderDto = customerOrderBO.getCustomerOrderData(id.getText());
        CustomerDto customerDto = customerOrderBO.getCustomerData(customerOrderDto.getCustomer_Id());

        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("date",customerOrderDto.getDate());
        hashMap.put("time",customerOrderDto.getTime());
        hashMap.put("orderId",customerOrderDto.getCustomer_Order_Id());
        hashMap.put("customerId",customerOrderDto.getCustomer_Id());
        hashMap.put("customerName",customerDto.getName());
        hashMap.put("contactNo",customerDto.getContact_No());
        hashMap.put("total",String.valueOf(customerOrderDto.getTotal_Price()));

        InputStream resourceAsStream = getClass().getResourceAsStream("/report/customerOrderReport.jrxml");

        JasperDesign load = JRXmlLoader.load(resourceAsStream);

        JRDesignQuery jrDesignQuery = getJrDesignQuery();

        load.setQuery(jrDesignQuery);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint, false);
        JasperExportManager.exportReportToPdfFile(jasperPrint,"/home/gayanuka/Documents/Jasper Reports/Customer_Report_PDFs/"+id.getText()+".pdf");
    }

    private JRDesignQuery getJrDesignQuery() {
        JRDesignQuery jrDesignQuery = new JRDesignQuery();
        jrDesignQuery.setText("SELECT\n" +
                "    cod.product_Id,\n" +
                "    p.description,\n" +
                "    cod.product_Quantity,\n" +
                "    p.unit_Price,\n" +
                "    p.category\n" +
                "FROM\n" +
                "    customer_Order co\n" +
                "JOIN\n" +
                "    customer_Order_Detail cod ON co.customer_Order_Id = cod.customer_Order_Id\n" +
                "JOIN\n" +
                "    product_Stock p ON p.product_Id = cod.product_Id\n" +
                "WHERE\n" +
                "    co.customer_Order_Id = '"+ id.getText() +"'");
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
            CustomerOrderDto customerOrderDto = customerOrderBO.getCustomerOrderData(id);

            this.id.setText(customerOrderDto.getCustomer_Order_Id());
            customerId.setText(customerOrderDto.getCustomer_Id());
            amount.setText(String.valueOf(customerOrderDto.getTotal_Price()));
            date.setText(customerOrderDto.getDate());
            time.setText(customerOrderDto.getTime());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
