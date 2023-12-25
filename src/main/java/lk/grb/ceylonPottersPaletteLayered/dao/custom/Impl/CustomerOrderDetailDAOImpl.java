package lk.grb.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.dao.custom.CustomerOrderDetailDAO;
import lk.grb.ceylonPottersPaletteLayered.db.DbConnection;
import lk.grb.ceylonPottersPaletteLayered.dto.CustomerOrderDto;
import lk.grb.ceylonPottersPaletteLayered.entity.CustomerOrder;
import lk.grb.ceylonPottersPaletteLayered.util.SQLUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerOrderDetailDAOImpl implements CustomerOrderDetailDAO {
    @Override
    public boolean save(CustomerOrder entity) throws SQLException {
        String sql = "insert into customer_Order_Detail VALUES (?,?,?)";
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        for (int i = 0; i < entity.getOrderList().size(); i++) {
            statement.setString(1, entity.getCustomer_Order_Id());
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
    public ArrayList<String[]> getData(String id) throws SQLException {
        ResultSet set = SQLUtil.execute("SELECT * FROM customer_Order_Detail WHERE customer_Order_Id=?", id);

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
