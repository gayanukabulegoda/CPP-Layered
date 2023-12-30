package lk.grb.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.dao.custom.SupplierOrderDAO;
import lk.grb.ceylonPottersPaletteLayered.entity.SupplierOrder;
import lk.grb.ceylonPottersPaletteLayered.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderDAOImpl implements SupplierOrderDAO {
    @Override
    public boolean save(SupplierOrder entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO supplier_Order VALUES (?,?,?,?,?)",
                entity.getSupplier_Order_Id(),
                entity.getSupplier_Id(),
                entity.getTotal_Price(),
                entity.getDate(),
                entity.getTime());
    }

    @Override
    public SupplierOrder getData(String id) throws SQLException {
        ResultSet set = SQLUtil.
                execute("SELECT * FROM supplier_Order WHERE supplier_Order_Id=?", id);

        SupplierOrder entity = new SupplierOrder();

        if (set.next()) {
            entity.setSupplier_Order_Id(set.getString(1));
            entity.setSupplier_Id(set.getString(2));
            entity.setTotal_Price(Double.parseDouble(set.getString(3)));
            entity.setDate(set.getString(4));
            entity.setTime(set.getString(5));
        }
        return entity;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException {
        ResultSet resultSet = SQLUtil.
                execute("SELECT supplier_Order_Id FROM supplier_Order " +
                        "ORDER BY date desc, time desc");

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public ArrayList<String> getSelectedAllSupplierOrderId(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.
                execute("SELECT supplier_Order_Id FROM supplier_Order " +
                        "WHERE supplier_Id = ? ORDER BY date desc, time desc", id);

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public ArrayList<String> getSupplierId(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.
                execute("SELECT supplier_Id FROM supplier_Order " +
                        "WHERE supplier_Order_Id = ? ORDER BY date desc, time desc", id);

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public String getSupplierIdForOrder(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.
                execute("SELECT supplier_Id FROM supplier_Order " +
                        "WHERE supplier_Order_Id = ?", id);

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public double getOrderTotal() throws SQLException {
        ResultSet resultSet = SQLUtil.
                execute("SELECT SUM(total_Price) FROM supplier_Order");

        if (resultSet.next()) {
            return resultSet.getDouble(1);
        }
        return 0.0;
    }

    @Override
    public boolean update(SupplierOrder entity) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }
}
