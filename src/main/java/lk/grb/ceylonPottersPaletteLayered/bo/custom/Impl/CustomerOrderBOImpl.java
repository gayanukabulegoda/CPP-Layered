package lk.grb.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.bo.custom.CustomerOrderBO;
import lk.grb.ceylonPottersPaletteLayered.dao.DAOFactory;
import lk.grb.ceylonPottersPaletteLayered.dao.custom.*;
import lk.grb.ceylonPottersPaletteLayered.dto.CustomerDto;
import lk.grb.ceylonPottersPaletteLayered.dto.CustomerOrderDto;
import lk.grb.ceylonPottersPaletteLayered.entity.Customer;
import lk.grb.ceylonPottersPaletteLayered.entity.CustomerOrder;
import lk.grb.ceylonPottersPaletteLayered.util.TransactionConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerOrderBOImpl implements CustomerOrderBO {

    CustomerOrderDAO customerOrderDAO =
            (CustomerOrderDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.CUSTOMER_ORDER);

    CustomerDAO customerDAO =
            (CustomerDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.CUSTOMER);

    CustomerOrderDetailDAO customerOrderDetailDAO =
            (CustomerOrderDetailDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.CUSTOMER_ORDER_DETAIL);

    ProductStockDAO productStockDAO =
            (ProductStockDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.PRODUCT_STOCK);

    @Override
    public String[] productDescAndUnitPriceGet(String id) throws SQLException {
        return productStockDAO.descAndUnitPriceGet(id);
    }

    @Override
    public ArrayList<String> getAllCustomerOrderId() throws SQLException {
        return customerOrderDAO.getAllId();
    }

    @Override
    public boolean placeCustomerOrder(CustomerOrderDto dto) throws SQLException {
        boolean isSaved = false;

        CustomerOrder entity = new CustomerOrder(
                dto.getCustomer_Order_Id(),
                dto.getCustomer_Id(),
                dto.getTotal_Price(),
                dto.getDate(),
                dto.getTime(),
                dto.getOrderList()
        );

        Connection connection = TransactionConnection.getDbConnection();

        if (customerOrderDAO.save(entity)) {

            if (productStockDAO.update(entity.getOrderList())) {

                if (customerOrderDetailDAO.save(entity)) {
                    TransactionConnection.commit(connection);
                    isSaved = true;
                }
                else TransactionConnection.rollBack(connection);
            }
            else TransactionConnection.rollBack(connection);
        }
        else TransactionConnection.rollBack(connection);

        return isSaved;
    }

    @Override
    public String getCustomerName(String id) throws SQLException {
        return customerDAO.getCustomerName(id);
    }

    @Override
    public ArrayList<String> getAllCustomerId() throws SQLException {
        return customerDAO.getAllId();
    }

    @Override
    public String getProductQtyOnHand(String id) throws SQLException {
        return productStockDAO.getQtyOnHand(id);
    }

    @Override
    public String getProductUnitPrice(String id) throws SQLException {
        return productStockDAO.getUnitPrice(id);
    }

    @Override
    public String getProductDescription(String id) throws SQLException {
        return productStockDAO.getDescription(id);
    }

    @Override
    public ArrayList<String> getAllProductId() throws SQLException {
        return productStockDAO.getAllId();
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

    @Override
    public CustomerDto getCustomerData(String id) throws SQLException {
        Customer customer = customerDAO.getData(id);
        return new CustomerDto(
                customer.getCustomer_Id(),
                customer.getName(),
                customer.getContact_No(),
                customer.getEmail(),
                customer.getDate(),
                customer.getTime(),
                customer.getUser_Name()
        );
    }

    @Override
    public String getCustomerIdForOrder(String id) throws SQLException {
        return customerOrderDAO.getCustomerIdForOrder(id);
    }

    @Override
    public ArrayList<String> getCustomerId(String id) throws SQLException {
        return customerOrderDAO.getCustomerId(id);
    }

    @Override
    public String getCustomerContactNo(String id) throws SQLException {
        return customerDAO.getCustomerContactNo(id);
    }

    @Override
    public ArrayList<String> getSelectedAllCustomerOrderId(String id) throws SQLException {
        return customerOrderDAO.getSelectedAllCustomerOrderId(id);
    }

    @Override
    public ArrayList<String[]> getCustomerOrderDetailData(String id) throws SQLException {
        return customerOrderDetailDAO.getData(id);
    }

    @Override
    public double getTotal(String unitPrice, String qty) {
        return Double.parseDouble(unitPrice) * Integer.parseInt(qty);
    }
}
