package lk.grb.ceylonPottersPaletteLayered.dao.custom;

import lk.grb.ceylonPottersPaletteLayered.db.DbConnection;
import lk.grb.ceylonPottersPaletteLayered.dto.CustomerDto;
import lk.grb.ceylonPottersPaletteLayered.entity.Customer;
import lk.grb.ceylonPottersPaletteLayered.util.CrudUtil;
import lk.grb.ceylonPottersPaletteLayered.util.SQLUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudUtil<Customer> {
    boolean save(CustomerDto customerDTO) throws SQLException;

    CustomerDto getData(String id) throws SQLException;

    boolean update(CustomerDto customerDTO) throws SQLException;

    boolean delete(String id) throws SQLException;

    ArrayList<String> getAllCustomerId() throws SQLException;

    String getCustomerName(String id) throws SQLException;

    String getCustomerCount() throws SQLException;

    String getCustomerContactNo(String id) throws SQLException;
}
