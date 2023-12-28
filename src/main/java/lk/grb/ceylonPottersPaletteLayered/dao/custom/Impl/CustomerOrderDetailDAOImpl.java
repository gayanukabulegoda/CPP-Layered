package lk.grb.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.dao.custom.CustomerOrderDetailDAO;
import lk.grb.ceylonPottersPaletteLayered.entity.CustomerOrder;
import lk.grb.ceylonPottersPaletteLayered.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerOrderDetailDAOImpl implements CustomerOrderDetailDAO {
    @Override
    public boolean save(CustomerOrder entity) throws SQLException {
        boolean isInserted = false;
        for (int i = 0; i < entity.getOrderList().size(); i++) {
            isInserted = SQLUtil.
                    execute("INSERT INTO customer_Order_Detail VALUES (?,?,?)",
                            entity.getCustomer_Order_Id(),
                            entity.getOrderList().get(i)[0],
                            Integer.parseInt(entity.getOrderList().get(i)[1]));

            if (!isInserted) return false;
        }
        return isInserted;
    }

    @Override
    public ArrayList<String[]> getData(String id) throws SQLException {
        ResultSet set = SQLUtil.
                execute("SELECT * FROM customer_Order_Detail WHERE customer_Order_Id=?", id);

        ArrayList<String[]> products = new ArrayList<>();

        while (set.next()) {

            String[] productIdAndQty = new String[2];
            productIdAndQty[0] = set.getString(2);
            productIdAndQty[1] = set.getString(3);

            products.add(productIdAndQty);
        }
        return products;
    }
}
