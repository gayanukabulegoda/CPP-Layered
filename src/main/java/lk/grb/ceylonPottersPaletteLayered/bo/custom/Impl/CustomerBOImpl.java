package lk.grb.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.bo.custom.CustomerBO;
import lk.grb.ceylonPottersPaletteLayered.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException {
        return false;
    }

    @Override
    public CustomerDto getCustomerData(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<String> getAllCustomerId() throws SQLException {
        return null;
    }

    @Override
    public String getCustomerContactNo(String id) throws SQLException {
        return null;
    }
}
