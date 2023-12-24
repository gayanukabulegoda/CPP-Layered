package lk.grb.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.dao.SuperDAO;
import lk.grb.ceylonPottersPaletteLayered.dao.custom.SupplierDAO;
import lk.grb.ceylonPottersPaletteLayered.db.DbConnection;
import lk.grb.ceylonPottersPaletteLayered.dto.SupplierDto;
import lk.grb.ceylonPottersPaletteLayered.entity.Supplier;
import lk.grb.ceylonPottersPaletteLayered.util.CrudUtil;
import lk.grb.ceylonPottersPaletteLayered.util.SQLUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public boolean save(SupplierDto supplierDto) throws SQLException {
        return SQLUtil.execute("INSERT INTO supplier VALUES (?,?,?,?,?,?,?)",
                supplierDto.getSupplier_Id(),
                supplierDto.getName(),
                supplierDto.getEmail(),
                supplierDto.getContact_No(),
                supplierDto.getTime(),
                supplierDto.getDate(),
                supplierDto.getUser_Name());
    }

    @Override
    public SupplierDto getData(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier WHERE supplier_Id=?", id);

        SupplierDto supplierDTO = new SupplierDto();

        if(resultSet.next()){
            supplierDTO.setSupplier_Id(resultSet.getString(1));
            supplierDTO.setName(resultSet.getString(2));
            supplierDTO.setEmail(resultSet.getString(3));
            supplierDTO.setContact_No(resultSet.getString(4));
            supplierDTO.setTime(resultSet.getString(5));
            supplierDTO.setDate(resultSet.getString(6));
            supplierDTO.setUser_Name(resultSet.getString(7));
        }
        return supplierDTO;
    }

    @Override
    public boolean update(SupplierDto supplierDTO) throws SQLException {
        return SQLUtil.execute("UPDATE supplier SET " +
                        "name=?," +
                        "email=?," +
                        "contact_No=? " +
                        "WHERE supplier_Id=?",
                supplierDTO.getName(),
                supplierDTO.getEmail(),
                supplierDTO.getContact_No(),
                supplierDTO.getSupplier_Id()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM supplier WHERE supplier_Id=?", id);
    }

    @Override
    public ArrayList<String> getAllSupplierId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT supplier_Id FROM supplier ORDER BY LENGTH(supplier_Id),supplier_Id");
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public String getSupplierName(String id) throws SQLException {

        String sql = ("SELECT name FROM supplier WHERE supplier_Id=?");
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1,id);

        ResultSet resultSet = statement.executeQuery();
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
        ResultSet resultSet = SQLUtil.execute("SELECT contact_No FROM supplier WHERE supplier_Id=?", id);

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}
