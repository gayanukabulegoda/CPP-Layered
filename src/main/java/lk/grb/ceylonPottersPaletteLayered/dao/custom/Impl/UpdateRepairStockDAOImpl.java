package lk.grb.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.dao.custom.UpdateRepairStockDAO;
import lk.grb.ceylonPottersPaletteLayered.db.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateRepairStockDAOImpl implements UpdateRepairStockDAO {

    ProductStockDAOImpl productStockDAOImpl = new ProductStockDAOImpl();
    RepairStockDAOImpl repairStockDAOImpl = new RepairStockDAOImpl();

    @Override
    public boolean updateRepairStock(String product_Id, String qty) throws SQLException {

        Connection connection = null;
        boolean update = false;

        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isUpdated = productStockDAOImpl.update(product_Id, qty);

            if (isUpdated) {
                boolean updated = repairStockDAOImpl.update(product_Id, qty);

                if (updated) {
                    connection.commit();
                    update = true;
                }
            }
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }
        finally {
            connection.setAutoCommit(true);
        }
        return update;
    }
}
