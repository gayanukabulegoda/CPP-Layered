package lk.grb.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.bo.custom.SupplierOrderBO;
import lk.grb.ceylonPottersPaletteLayered.dto.SupplierDto;
import lk.grb.ceylonPottersPaletteLayered.dto.SupplierOrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderBOImpl implements SupplierOrderBO {
    @Override
    public String[] descAndUnitPriceGet(String id) throws SQLException {
        return new String[0];
    }

    @Override
    public ArrayList<String> getAllSupplierId() throws SQLException {
        return null;
    }

    @Override
    public boolean placeSupplierOrder(SupplierOrderDto dto) {
        return false;
    }

    @Override
    public String getItemQtyOnHand(String id) throws SQLException {
        return null;
    }

    @Override
    public String getItemUnitPrice(String id) throws SQLException {
        return null;
    }

    @Override
    public String getItemDescription(String id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> getAllItemId() throws SQLException {
        return null;
    }

    @Override
    public String getSupplierName(String id) throws SQLException {
        return null;
    }

    @Override
    public SupplierOrderDto getSupplierOrderData(String id) throws SQLException {
        return null;
    }

    @Override
    public SupplierDto getSupplierData(String id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> getAllSupplierOrderId() throws SQLException {
        return null;
    }

    @Override
    public String getSupplierIdForOrder(String id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> getSupplierId(String id) throws SQLException {
        return null;
    }

    @Override
    public String getSupplierContactNo(String id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> getSelectedAllSupplierOrderId(String id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String[]> getDataAsAnArray(String id) throws SQLException {
        return null;
    }
}
