package lk.ijse.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.ijse.ceylonPottersPaletteLayered.dao.custom.CustomerDAO;
import lk.ijse.ceylonPottersPaletteLayered.util.SQLUtil;
import lk.ijse.ceylonPottersPaletteLayered.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO customer VALUES (?,?,?,?,?,?,?)",
                entity.getCustomer_Id(),
                entity.getName(),
                entity.getContact_No(),
                entity.getEmail(),
                entity.getDate(),
                entity.getTime(),
                entity.getUser_Name());
    }

    @Override
    public Customer getData(String id) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT * FROM customer WHERE customer_Id=?", id);

        Customer entity = new Customer();

        if (set.next()) {
            entity.setCustomer_Id(set.getString(1));
            entity.setName(set.getString(2));
            entity.setContact_No(set.getString(3));
            entity.setEmail(set.getString(4));
            entity.setDate(set.getString(5));
            entity.setTime(set.getString(6));
            entity.setUser_Name(set.getString(7));
        }
        return entity;
    }

    @Override
    public boolean update(Customer entity) throws SQLException {
        return SQLUtil.execute("UPDATE customer SET " +
                        "name=?," +
                        "email=?," +
                        "contact_No=? " +
                        "WHERE customer_Id=?",
                entity.getName(),
                entity.getEmail(),
                entity.getContact_No(),
                entity.getCustomer_Id());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM customer WHERE customer_Id=?", id);
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException {
        ResultSet resultSet = SQLUtil.
                execute("SELECT customer_Id FROM customer ORDER BY LENGTH(customer_Id),customer_Id");

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public String getCustomerName(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT name FROM customer WHERE customer_Id=?", id);

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public String getCustomerCount() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) FROM customer");

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public String getCustomerContactNo(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT contact_No FROM customer WHERE customer_Id=?", id);

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}
