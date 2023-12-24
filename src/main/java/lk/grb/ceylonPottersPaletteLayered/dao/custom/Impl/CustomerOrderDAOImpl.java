package lk.grb.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.dao.custom.CustomerOrderDAO;
import lk.grb.ceylonPottersPaletteLayered.dto.CustomerOrderDto;
import lk.grb.ceylonPottersPaletteLayered.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerOrderDAOImpl implements CustomerOrderDAO {
    @Override
    public boolean save(CustomerOrderDto customerOrderDto) throws SQLException {
        return SQLUtil.execute("INSERT INTO customer_Order VALUES (?,?,?,?,?)",
                customerOrderDto.getCustomer_Order_Id(),
                customerOrderDto.getCustomer_Id(),
                customerOrderDto.getTotal_Price(),
                customerOrderDto.getDate(),
                customerOrderDto.getTime());
    }

    @Override
    public CustomerOrderDto getData(String id) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT * FROM customer_Order WHERE customer_Order_Id=?", id);

        CustomerOrderDto customerOrderDto = new CustomerOrderDto();

        if (set.next()) {
            customerOrderDto.setCustomer_Order_Id(set.getString(1));
            customerOrderDto.setCustomer_Id(set.getString(2));
            customerOrderDto.setTotal_Price(Double.parseDouble(set.getString(3)));
            customerOrderDto.setDate(set.getString(4));
            customerOrderDto.setTime(set.getString(5));
        }
        return customerOrderDto;
    }

    @Override
    public ArrayList<String> getAllCustomerOrderId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT customer_Order_Id FROM customer_Order ORDER BY LENGTH(customer_Order_Id),customer_Order_Id");
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public ArrayList<String> getAllCustomerOrderIdS() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT customer_Order_Id FROM customer_Order ORDER BY CAST(Substring(customer_Order_Id, 3) AS UNSIGNED)");
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public ArrayList<String> getSelectedAllCustomerOrderId(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT customer_Order_Id FROM customer_Order " +
                "WHERE customer_Id = ? ORDER BY date desc, time desc", id);

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public ArrayList<String> getCustomerId(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT customer_Id FROM customer_Order " +
                "WHERE customer_Order_Id = ? ORDER BY date desc, time desc", id);

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public String getCustomerIdForOrder(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT customer_Id FROM customer_Order " +
                "WHERE customer_Order_Id = ?", id);

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public String getTodaySales() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) FROM customer_Order WHERE DATE(date) = CURDATE()");

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public double getOrderTotal() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT SUM(total_Price) FROM customer_Order");

        if (resultSet.next()) {
            return resultSet.getDouble(1);
        }
        return 0.0;
    }
}
