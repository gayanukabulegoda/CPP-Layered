package lk.grb.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.dao.custom.PlaceSupplierOrderDAO;
import lk.grb.ceylonPottersPaletteLayered.db.DbConnection;
import lk.grb.ceylonPottersPaletteLayered.dto.SupplierOrderDto;
import lk.grb.ceylonPottersPaletteLayered.entity.SupplierOrder;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceSupplierOrderDAOImpl implements PlaceSupplierOrderDAO {

    SupplierOrderDAOImpl supplierOrderDAOImpl = new SupplierOrderDAOImpl();
    ItemStockDAOImpl itemStockDAOImpl = new ItemStockDAOImpl();
    SupplierOrderDetailDAOImpl supplierOrderDetailDAOImpl = new SupplierOrderDetailDAOImpl();

    @Override
    public boolean placeSupplierOrder(SupplierOrder entity) {

        boolean isSaved = false;
        Connection connection = null;

        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean save = supplierOrderDAOImpl.save(entity);

            if (save) {
                boolean update = itemStockDAOImpl.update(entity.getOrderList());

                if (update) {
                    boolean saveSupOrder = supplierOrderDetailDAOImpl.save(entity);

                    if (saveSupOrder) {
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
