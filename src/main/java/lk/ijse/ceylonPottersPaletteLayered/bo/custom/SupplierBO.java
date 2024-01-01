package lk.ijse.ceylonPottersPaletteLayered.bo.custom;

import lk.ijse.ceylonPottersPaletteLayered.bo.SuperBO;
import lk.ijse.ceylonPottersPaletteLayered.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    ArrayList<String> getAllSupplierId() throws SQLException;

    boolean saveSupplier(SupplierDto dto) throws SQLException;

    SupplierDto getSupplierData(String id) throws SQLException;

    String getSupplierContactNo(String id) throws SQLException;

    boolean updateSupplier(SupplierDto dto) throws SQLException;

    ArrayList<String> getAllSupplierContactNumbers() throws SQLException;

    ArrayList<String> getAllSupplierEmails() throws SQLException;
}
