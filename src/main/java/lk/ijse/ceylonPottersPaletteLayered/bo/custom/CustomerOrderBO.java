package lk.ijse.ceylonPottersPaletteLayered.bo.custom;

import lk.ijse.ceylonPottersPaletteLayered.bo.SuperBO;
import lk.ijse.ceylonPottersPaletteLayered.dto.CustomerDto;
import lk.ijse.ceylonPottersPaletteLayered.dto.CustomerOrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerOrderBO extends SuperBO {
    String[] productDescAndUnitPriceGet(String id) throws SQLException;

    ArrayList<String> getAllCustomerOrderId() throws SQLException;

    boolean placeCustomerOrder(CustomerOrderDto dto) throws SQLException;

    String getCustomerName(String id) throws SQLException;

    ArrayList<String> getAllCustomerId() throws SQLException;

    String getProductQtyOnHand(String id) throws SQLException;

    String getProductUnitPrice(String id) throws SQLException;

    String getProductDescription(String id) throws SQLException;

    ArrayList<String> getAllProductId() throws SQLException;

    CustomerOrderDto getCustomerOrderData(String id) throws SQLException;

    CustomerDto getCustomerData(String id) throws SQLException;

    String getCustomerIdForOrder(String id) throws SQLException;

    ArrayList<String> getCustomerId(String id) throws SQLException;

    String getCustomerContactNo(String id) throws SQLException;

    ArrayList<String> getSelectedAllCustomerOrderId(String id) throws SQLException;

    ArrayList<String[]> getCustomerOrderDetailData(String id) throws SQLException;

    double getTotal(String unitPrice, String qty);
}
