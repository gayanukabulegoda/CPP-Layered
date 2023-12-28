package lk.grb.ceylonPottersPaletteLayered.dao.custom;

import lk.grb.ceylonPottersPaletteLayered.entity.Customer;
import lk.grb.ceylonPottersPaletteLayered.util.CrudUtil;

import java.sql.SQLException;

public interface CustomerDAO extends CrudUtil<Customer> {
    String getCustomerName(String id) throws SQLException;

    String getCustomerCount() throws SQLException;

    String getCustomerContactNo(String id) throws SQLException;
}
