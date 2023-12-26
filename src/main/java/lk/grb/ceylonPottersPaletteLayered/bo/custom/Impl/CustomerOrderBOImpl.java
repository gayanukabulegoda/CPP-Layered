package lk.grb.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.bo.custom.CustomerOrderBO;
import lk.grb.ceylonPottersPaletteLayered.dto.CustomerDto;
import lk.grb.ceylonPottersPaletteLayered.dto.CustomerOrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerOrderBOImpl implements CustomerOrderBO {
    @Override
    public String[] descAndUnitPriceGet(String id) throws SQLException {
        return new String[0];
    }

    @Override
    public ArrayList<String> getAllCustomerOrderId() throws SQLException {
        return null;
    }

    @Override
    public boolean placeCustomerOrder(CustomerOrderDto dto) {
        return false;
    }

    @Override
    public String getCustomerName(String id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> getAllCustomerId() throws SQLException {
        return null;
    }

    @Override
    public String getProductQtyOnHand(String id) throws SQLException {
        return null;
    }

    @Override
    public String getProductUnitPrice(String id) throws SQLException {
        return null;
    }

    @Override
    public String getProductDescription(String id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> getAllProductId() throws SQLException {
        return null;
    }

    @Override
    public CustomerOrderDto getCustomerOrderData(String id) throws SQLException {
        return null;
    }

    @Override
    public CustomerDto getCustomerData(String id) throws SQLException {
        return null;
    }

    @Override
    public String getCustomerIdForOrder(String id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> getCustomerId(String id) throws SQLException {
        return null;
    }

    @Override
    public String getCustomerContactNo(String id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> getSelectedAllCustomerOrderId(String id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String[]> getCustomerOrderDetailData(String id) throws SQLException {
        return null;
    }
}
