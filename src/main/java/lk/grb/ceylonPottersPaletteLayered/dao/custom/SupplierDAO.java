package lk.grb.ceylonPottersPaletteLayered.dao.custom;

import lk.grb.ceylonPottersPaletteLayered.db.DbConnection;
import lk.grb.ceylonPottersPaletteLayered.dto.SupplierDto;
import lk.grb.ceylonPottersPaletteLayered.entity.Supplier;
import lk.grb.ceylonPottersPaletteLayered.util.CrudUtil;
import lk.grb.ceylonPottersPaletteLayered.util.SQLUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDAO extends CrudUtil<Supplier> {
    boolean save(SupplierDto supplierDto) throws SQLException;

    SupplierDto getData(String id) throws SQLException;

    boolean update(SupplierDto supplierDTO) throws SQLException;

    boolean delete(String id) throws SQLException;

    ArrayList<String> getAllSupplierId() throws SQLException;

    String getSupplierName(String id) throws SQLException;

    String getSupplierCount() throws SQLException;

    String getSupplierContactNo(String id) throws SQLException;
}
