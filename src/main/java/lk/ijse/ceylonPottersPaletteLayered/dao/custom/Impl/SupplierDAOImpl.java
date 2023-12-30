package lk.ijse.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.ijse.ceylonPottersPaletteLayered.dao.custom.SupplierDAO;
import lk.ijse.ceylonPottersPaletteLayered.util.SQLUtil;
import lk.ijse.ceylonPottersPaletteLayered.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public boolean save(Supplier entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO supplier VALUES (?,?,?,?,?,?,?)",
                entity.getSupplier_Id(),
                entity.getName(),
                entity.getEmail(),
                entity.getContact_No(),
                entity.getTime(),
                entity.getDate(),
                entity.getUser_Name());
    }

    @Override
    public Supplier getData(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.
                execute("SELECT * FROM supplier WHERE supplier_Id=?", id);

        Supplier entity = new Supplier();

        if(resultSet.next()){
            entity.setSupplier_Id(resultSet.getString(1));
            entity.setName(resultSet.getString(2));
            entity.setEmail(resultSet.getString(3));
            entity.setContact_No(resultSet.getString(4));
            entity.setTime(resultSet.getString(5));
            entity.setDate(resultSet.getString(6));
            entity.setUser_Name(resultSet.getString(7));
        }
        return entity;
    }

    @Override
    public boolean update(Supplier entity) throws SQLException {
        return SQLUtil.execute("UPDATE supplier SET " +
                        "name=?," +
                        "email=?," +
                        "contact_No=? " +
                        "WHERE supplier_Id=?",
                entity.getName(),
                entity.getEmail(),
                entity.getContact_No(),
                entity.getSupplier_Id());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM supplier WHERE supplier_Id=?", id);
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException {
        ResultSet resultSet = SQLUtil.
                execute("SELECT supplier_Id FROM supplier ORDER BY LENGTH(supplier_Id),supplier_Id");

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public String getSupplierName(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.
                execute("SELECT name FROM supplier WHERE supplier_Id=?", id);

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public String getSupplierCount() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) FROM supplier");

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public String getSupplierContactNo(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.
                execute("SELECT contact_No FROM supplier WHERE supplier_Id=?", id);

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}
