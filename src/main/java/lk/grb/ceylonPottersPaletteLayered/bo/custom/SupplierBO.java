package lk.grb.ceylonPottersPaletteLayered.bo.custom;

import lk.grb.ceylonPottersPaletteLayered.bo.SuperBO;
import lk.grb.ceylonPottersPaletteLayered.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    ArrayList<String> getAllSupplierId() throws SQLException;

    boolean saveSupplier(SupplierDto dto) throws SQLException;

    SupplierDto getSupplierData(String id) throws SQLException;

    String getSupplierContactNo(String id) throws SQLException;

    boolean updateSupplier(SupplierDto dto) throws SQLException;
}
