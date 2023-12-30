package lk.ijse.ceylonPottersPaletteLayered.dao.custom;

import lk.ijse.ceylonPottersPaletteLayered.entity.CustomerOrder;
import lk.ijse.ceylonPottersPaletteLayered.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerOrderDAO extends CrudUtil<CustomerOrder> {
    ArrayList<String> getAllCustomerOrderIdS() throws SQLException;

    ArrayList<String> getSelectedAllCustomerOrderId(String id) throws SQLException;

    ArrayList<String> getCustomerId(String id) throws SQLException;

    String getCustomerIdForOrder(String id) throws SQLException;

    String getTodaySales() throws SQLException;

    double getOrderTotal() throws SQLException;
}
