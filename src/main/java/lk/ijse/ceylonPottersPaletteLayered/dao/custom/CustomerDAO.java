package lk.ijse.ceylonPottersPaletteLayered.dao.custom;

import lk.ijse.ceylonPottersPaletteLayered.entity.Customer;
import lk.ijse.ceylonPottersPaletteLayered.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudUtil<Customer> {
    String getCustomerName(String id) throws SQLException;

    String getCustomerCount() throws SQLException;

    String getCustomerContactNo(String id) throws SQLException;

    ArrayList<String> getAllContactNumbers() throws SQLException;

    ArrayList<String> getAllEmails() throws SQLException;
}
