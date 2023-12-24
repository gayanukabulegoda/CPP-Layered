package lk.grb.ceylonPottersPaletteLayered.dao.custom;

import lk.grb.ceylonPottersPaletteLayered.dto.CustomerOrderDto;
import lk.grb.ceylonPottersPaletteLayered.entity.CustomerOrder;
import lk.grb.ceylonPottersPaletteLayered.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerOrderDAO extends CrudUtil<CustomerOrder> {
    boolean save(CustomerOrderDto customerOrderDto) throws SQLException;

    CustomerOrderDto getData(String id) throws SQLException;

    ArrayList<String> getAllCustomerOrderId() throws SQLException;

    ArrayList<String> getAllCustomerOrderIdS() throws SQLException;

    ArrayList<String> getSelectedAllCustomerOrderId(String id) throws SQLException;

    ArrayList<String> getCustomerId(String id) throws SQLException;

    String getCustomerIdForOrder(String id) throws SQLException;

    String getTodaySales() throws SQLException;

    double getOrderTotal() throws SQLException;
}
