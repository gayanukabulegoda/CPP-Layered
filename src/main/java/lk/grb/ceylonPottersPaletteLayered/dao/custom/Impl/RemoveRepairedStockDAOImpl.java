package lk.grb.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.dao.custom.RemoveRepairedStockDAO;
import lk.grb.ceylonPottersPaletteLayered.db.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class RemoveRepairedStockDAOImpl implements RemoveRepairedStockDAO {

    ProductStockDAOImpl productStockDAOImpl = new ProductStockDAOImpl();
    RepairStockDAOImpl repairStockDAOImpl = new RepairStockDAOImpl();

    @Override
    public boolean removeRepairStock(String product_Id, String qty) throws SQLException {

        Connection connection = null;
        boolean update = false;

        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isUpdated = productStockDAOImpl.updateIncrement(product_Id, qty);
            System.out.println(isUpdated+ " product");

            if (isUpdated) {
                boolean updated = repairStockDAOImpl.updateDecrement(product_Id, qty);
                System.out.println(isUpdated+ " repair");

                if (updated) {
                    connection.commit();
                    update = true;
                }
            }
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
        return update;
    }
}
