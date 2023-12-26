package lk.grb.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.bo.custom.SupplierBO;
import lk.grb.ceylonPottersPaletteLayered.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    @Override
    public ArrayList<String> getAllSupplierId() throws SQLException {
        return null;
    }

    @Override
    public boolean saveSupplier(SupplierDto dto) throws SQLException {
        return false;
    }

    @Override
    public SupplierDto getSupplierData(String id) throws SQLException {
        return null;
    }

    @Override
    public String getSupplierContactNo(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean updateSupplier(SupplierDto dto) throws SQLException {
        return false;
    }
}
