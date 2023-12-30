package lk.ijse.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.ijse.ceylonPottersPaletteLayered.dao.DAOFactory;
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.DashboardBO;
import lk.ijse.ceylonPottersPaletteLayered.dao.custom.*;
import lk.ijse.ceylonPottersPaletteLayered.dto.CustomerOrderDto;
import lk.ijse.ceylonPottersPaletteLayered.entity.CustomerOrder;

import java.sql.SQLException;
import java.util.ArrayList;

public class DashboardBOImpl implements DashboardBO {

    CustomerOrderDAO customerOrderDAO =
            (CustomerOrderDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.CUSTOMER_ORDER);

    SupplierOrderDAO supplierOrderDAO =
            (SupplierOrderDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.SUPPLIER_ORDER);

    ProductStockDAO productStockDAO =
            (ProductStockDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.PRODUCT_STOCK);

    EmployeeAttendanceDAO employeeAttendanceDAO =
            (EmployeeAttendanceDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.EMPLOYEE_ATTENDANCE);

    ItemStockDAO itemStockDAO =
            (ItemStockDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.ITEM_STOCK);

    CustomerDAO customerDAO =
            (CustomerDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.CUSTOMER);

    SupplierDAO supplierDAO =
            (SupplierDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.SUPPLIER);

    EmployeeSalaryDAO employeeSalaryDAO =
            (EmployeeSalaryDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.EMPLOYEE_SALARY);

    @Override
    public ArrayList<String> getAllCustomerOrderIdS() throws SQLException {
        return customerOrderDAO.getAllCustomerOrderIdS();
    }

    @Override
    public double getCustomerOrderTotal() throws SQLException {
        return customerOrderDAO.getOrderTotal();
    }

    @Override
    public double getSupplierOrderTotal() throws SQLException {
        return supplierOrderDAO.getOrderTotal();
    }

    @Override
    public double getSalaryTotal() throws SQLException {
        return employeeSalaryDAO.getSalaryTotal();
    }

    @Override
    public ArrayList<String> getAllProductId() throws SQLException {
        return productStockDAO.getAllId();
    }

    @Override
    public String getProductQtyTotal(String id) throws SQLException {
        return productStockDAO.getQtyTotal(id);
    }

    @Override
    public String getTodayAttendance() throws SQLException {
        return employeeAttendanceDAO.getTodayAttendance();
    }

    @Override
    public String getAvailableClayStock() throws SQLException {
        return itemStockDAO.getAvailableClayStock();
    }

    @Override
    public String getTodaySales() throws SQLException {
        return customerOrderDAO.getTodaySales();
    }

    @Override
    public String getCustomerCount() throws SQLException {
        return customerDAO.getCustomerCount();
    }

    @Override
    public String getSupplierCount() throws SQLException {
        return supplierDAO.getSupplierCount();
    }

    @Override
    public CustomerOrderDto getCustomerOrderData(String id) throws SQLException {
        CustomerOrder customerOrder = customerOrderDAO.getData(id);
        return new CustomerOrderDto(
                customerOrder.getCustomer_Order_Id(),
                customerOrder.getCustomer_Id(),
                customerOrder.getTotal_Price(),
                customerOrder.getDate(),
                customerOrder.getTime(),
                customerOrder.getOrderList()
        );
    }
}
