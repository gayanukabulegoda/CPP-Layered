package lk.grb.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.bo.custom.CustomerBO;
import lk.grb.ceylonPottersPaletteLayered.dao.DAOFactory;
import lk.grb.ceylonPottersPaletteLayered.dao.custom.CustomerDAO;
import lk.grb.ceylonPottersPaletteLayered.dto.CustomerDto;
import lk.grb.ceylonPottersPaletteLayered.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO =
            (CustomerDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException {
        return customerDAO.save(
                new Customer(
                        dto.getCustomer_Id(),
                        dto.getName(),
                        dto.getContact_No(),
                        dto.getEmail(),
                        dto.getDate(),
                        dto.getTime(),
                        dto.getUser_Name()
                )
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
    public boolean updateCustomer(CustomerDto dto) throws SQLException {
        return customerDAO.update(
                new Customer(
                        dto.getCustomer_Id(),
                        dto.getName(),
                        dto.getContact_No(),
                        dto.getEmail(),
                        dto.getDate(),
                        dto.getTime(),
                        dto.getUser_Name()
                )
        );
    }

    @Override
    public ArrayList<String> getAllCustomerId() throws SQLException {
        return customerDAO.getAllId();
    }

    @Override
    public String getCustomerContactNo(String id) throws SQLException {
        return customerDAO.getCustomerContactNo(id);
    }
}
