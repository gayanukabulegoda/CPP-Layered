package lk.ijse.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.ijse.ceylonPottersPaletteLayered.dao.custom.SupplierOrderDetailDAO;
import lk.ijse.ceylonPottersPaletteLayered.util.SQLUtil;
import lk.ijse.ceylonPottersPaletteLayered.entity.SupplierOrder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderDetailDAOImpl implements SupplierOrderDetailDAO {
    @Override
    public boolean save(SupplierOrder entity) throws SQLException {
        boolean isInserted = false;
        for (int i = 0; i < entity.getOrderList().size(); i++) {
            isInserted = SQLUtil.
                    execute("INSERT INTO supplier_Order_Detail VALUES (?,?,?)",
                            entity.getSupplier_Order_Id(),
                            entity.getOrderList().get(i)[0],
                            Integer.parseInt(entity.getOrderList().get(i)[1]));

            if (!isInserted) return false;
        }
        return isInserted;
    }

    @Override
    public SupplierOrder getData(String id) throws SQLException {
        ResultSet set = SQLUtil.
                execute("SELECT * FROM supplier_Order_Detail WHERE supplier_Order_Id=?", id);

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
        ResultSet set = SQLUtil.
                execute("SELECT * FROM supplier_Order_Detail WHERE supplier_Order_Id=?", id);

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
