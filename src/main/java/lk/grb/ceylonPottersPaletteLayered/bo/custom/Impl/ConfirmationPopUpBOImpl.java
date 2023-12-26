package lk.grb.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.bo.custom.ConfirmationPopUpBO;
import lk.grb.ceylonPottersPaletteLayered.dao.DAOFactory;
import lk.grb.ceylonPottersPaletteLayered.dao.custom.*;

import java.sql.SQLException;

public class ConfirmationPopUpBOImpl implements ConfirmationPopUpBO {

    EmployeeDAO employeeDAO = (EmployeeDAO)
            DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    SupplierDAO supplierDAO = (SupplierDAO)
            DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    CustomerDAO customerDAO = (CustomerDAO)
            DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    EmployeeAttendanceDAO employeeAttendanceDAO = (EmployeeAttendanceDAO)
            DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE_ATTENDANCE);
    ProductStockDAO productStockDAO = (ProductStockDAO)
            DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT_STOCK);
    ItemStockDAO itemStockDAO = (ItemStockDAO)
            DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM_STOCK);

    @Override
    public boolean deleteEmployee(String id) throws SQLException {
        return employeeDAO.delete(id);
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException {
        return supplierDAO.delete(id);
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException {
        return customerDAO.delete(id);
    }

    @Override
    public boolean deleteAttendance(String id) throws SQLException {
        return employeeAttendanceDAO.delete(id);
    }

    @Override
    public boolean deleteProductStock(String id) throws SQLException {
        return productStockDAO.delete(id);
    }

    @Override
    public boolean deleteItemStock(String id) throws SQLException {
        return itemStockDAO.delete(id);
    }
}
