package lk.ijse.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.ijse.ceylonPottersPaletteLayered.bo.custom.SupplierBO;
import lk.ijse.ceylonPottersPaletteLayered.dao.DAOFactory;
import lk.ijse.ceylonPottersPaletteLayered.dao.custom.SupplierDAO;
import lk.ijse.ceylonPottersPaletteLayered.dto.SupplierDto;
import lk.ijse.ceylonPottersPaletteLayered.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO =
            (SupplierDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public ArrayList<String> getAllSupplierId() throws SQLException {
        return supplierDAO.getAllId();
    }

    @Override
    public boolean saveSupplier(SupplierDto dto) throws SQLException {
        return supplierDAO.save(
                new Supplier(
                        dto.getSupplier_Id(),
                        dto.getName(),
                        dto.getEmail(),
                        dto.getContact_No(),
                        dto.getTime(),
                        dto.getDate(),
                        dto.getUser_Name()
                )
        );
    }

    @Override
    public SupplierDto getSupplierData(String id) throws SQLException {
        Supplier supplier = supplierDAO.getData(id);
        return new SupplierDto(
                supplier.getSupplier_Id(),
                supplier.getName(),
                supplier.getEmail(),
                supplier.getContact_No(),
                supplier.getTime(),
                supplier.getDate(),
                supplier.getUser_Name()
        );
    }

    @Override
    public String getSupplierContactNo(String id) throws SQLException {
        return supplierDAO.getSupplierContactNo(id);
    }

    @Override
    public boolean updateSupplier(SupplierDto dto) throws SQLException {
        return supplierDAO.update(
                new Supplier(
                        dto.getSupplier_Id(),
                        dto.getName(),
                        dto.getEmail(),
                        dto.getContact_No(),
                        dto.getTime(),
                        dto.getDate(),
                        dto.getUser_Name()
                )
        );
    }
}
