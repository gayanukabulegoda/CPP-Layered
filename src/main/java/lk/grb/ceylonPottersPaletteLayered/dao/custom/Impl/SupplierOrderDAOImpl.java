package lk.grb.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.dao.custom.SupplierOrderDAO;
import lk.grb.ceylonPottersPaletteLayered.dto.SupplierOrderDto;
import lk.grb.ceylonPottersPaletteLayered.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderDAOImpl implements SupplierOrderDAO {

    public boolean save(SupplierOrderDto supplierOrderDto) throws SQLException {
        return SQLUtil.execute("INSERT INTO supplier_Order VALUES (?,?,?,?,?)",
                supplierOrderDto.getSupplier_Order_Id(),
                supplierOrderDto.getSupplier_Id(),
                supplierOrderDto.getTotal_Price(),
                supplierOrderDto.getDate(),
                supplierOrderDto.getTime());
    }

    public SupplierOrderDto getData(String id) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT * FROM supplier_Order WHERE supplier_Order_Id=?", id);

        SupplierOrderDto supplierOrderDto = new SupplierOrderDto();

        if (set.next()) {
            supplierOrderDto.setSupplier_Order_Id(set.getString(1));
            supplierOrderDto.setSupplier_Id(set.getString(2));
            supplierOrderDto.setTotal_Price(Double.parseDouble(set.getString(3)));
            supplierOrderDto.setDate(set.getString(4));
            supplierOrderDto.setTime(set.getString(5));
        }
        return supplierOrderDto;
    }

    public ArrayList<String> getAllSupplierOrderId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT supplier_Order_Id FROM supplier_Order ORDER BY LENGTH(supplier_Order_Id),supplier_Order_Id");
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    public ArrayList<String> getSelectedAllSupplierOrderId(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT supplier_Order_Id FROM supplier_Order " +
                "WHERE supplier_Id = ? ORDER BY date desc, time desc", id);

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    public ArrayList<String> getSupplierId(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT supplier_Id FROM supplier_Order " +
                "WHERE supplier_Order_Id = ? ORDER BY date desc, time desc", id);

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    public String getSupplierIdForOrder(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT supplier_Id FROM supplier_Order " +
                "WHERE supplier_Order_Id = ?", id);

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public double getOrderTotal() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT SUM(total_Price) FROM supplier_Order");

        if (resultSet.next()) {
            return resultSet.getDouble(1);
        }
        return 0.0;
    }
}
