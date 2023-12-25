package lk.grb.ceylonPottersPaletteLayered.bo.custom;

import lk.grb.ceylonPottersPaletteLayered.bo.SuperBO;
import lk.grb.ceylonPottersPaletteLayered.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(CustomerDto dto) throws SQLException;

    CustomerDto getCustomerData(String id) throws SQLException;

    boolean updateCustomer(CustomerDto dto) throws SQLException;

    ArrayList<String> getAllCustomerId() throws SQLException;

    String getCustomerContactNo(String id) throws SQLException;
}
