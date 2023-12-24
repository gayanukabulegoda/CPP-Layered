package lk.grb.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.dao.custom.PlaceCustomerOrderDAO;
import lk.grb.ceylonPottersPaletteLayered.db.DbConnection;
import lk.grb.ceylonPottersPaletteLayered.dto.CustomerOrderDto;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceCustomerOrderDAOImpl implements PlaceCustomerOrderDAO {

    CustomerOrderDAOImpl customerOrderDAOImpl = new CustomerOrderDAOImpl();
    ProductStockDAOImpl productStockDAOImpl = new ProductStockDAOImpl();
    CustomerOrderDetailDAOImpl customerOrderDetailDAOImpl = new CustomerOrderDetailDAOImpl();

    @Override
    public boolean placeCustomerOrder(CustomerOrderDto customerOrderDto) {

        boolean isSaved = false;
        Connection connection = null;

        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean save = customerOrderDAOImpl.save(customerOrderDto);

            if (save) {
                boolean update = productStockDAOImpl.update(customerOrderDto.getOrderList());

                if (update) {
                    boolean saveCusOrder = customerOrderDetailDAOImpl.save(customerOrderDto);

                    if (saveCusOrder) {
                        connection.commit();
                        isSaved = true;
                    }
                }
            }
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return isSaved;
    }
}
