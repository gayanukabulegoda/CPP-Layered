package lk.grb.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.dao.custom.SupplierOrderDetailDAO;
import lk.grb.ceylonPottersPaletteLayered.db.DbConnection;
import lk.grb.ceylonPottersPaletteLayered.dto.SupplierOrderDto;
import lk.grb.ceylonPottersPaletteLayered.entity.SupplierOrder;
import lk.grb.ceylonPottersPaletteLayered.util.SQLUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderDetailDAOImpl implements SupplierOrderDetailDAO {
    @Override
    public boolean save(SupplierOrder entity) throws SQLException {
        String sql = "INSERT INTO supplier_Order_Detail VALUES (?,?,?)";
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        for (int i = 0; i < entity.getOrderList().size(); i++) {
            statement.setString(1, entity.getSupplier_Order_Id());
            statement.setString(2,entity.getOrderList().get(i)[0]);
            statement.setInt(3, Integer.parseInt(entity.getOrderList().get(i)[1]));

            int values = statement.executeUpdate();
            if (values == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public SupplierOrder getData(String id) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT * FROM supplier_Order_Detail WHERE supplier_Order_Id=?", id);

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
    public ArrayList<String[]> getDataAsAnArray(String id) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT * FROM supplier_Order_Detail WHERE supplier_Order_Id=?", id);

        ArrayList<String[]> items = new ArrayList<>();

        while (set.next()) {

            String[] itemIdAndQuantity = new String[2];
            itemIdAndQuantity[0] = set.getString(2);
            itemIdAndQuantity[1] = set.getString(3);

            items.add(itemIdAndQuantity);
        }
        return items;
    }
}
