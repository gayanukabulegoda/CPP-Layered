package lk.grb.ceylonPottersPaletteLayered.bo.custom;

import lk.grb.ceylonPottersPaletteLayered.bo.SuperBO;
import lk.grb.ceylonPottersPaletteLayered.dto.CustomerOrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerOrderBO extends SuperBO {
    boolean saveCustomerOrder(CustomerOrderDto dto) throws SQLException;

    CustomerOrderDto getCustomerOrderData(String id) throws SQLException;

    boolean updateCustomerOrder(CustomerOrderDto dto) throws SQLException;

    boolean deleteCustomerOrder(String id) throws SQLException;

    ArrayList<String> getAllCustomerOrderId() throws SQLException;

    ArrayList<String> getAllCustomerOrderIdS() throws SQLException;

    ArrayList<String> getSelectedAllCustomerOrderId(String id) throws SQLException;

    ArrayList<String> getCustomerId(String id) throws SQLException;

    String getCustomerIdForOrder(String id) throws SQLException;

    String getTodaySales() throws SQLException;

    double getOrderTotal() throws SQLException;
}
