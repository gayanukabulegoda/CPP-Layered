package lk.grb.ceylonPottersPaletteLayered.dao.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.dao.custom.ProductStockTransactionDAO;
import lk.grb.ceylonPottersPaletteLayered.db.DbConnection;
import lk.grb.ceylonPottersPaletteLayered.dto.ProductStockDto;

import java.sql.Connection;
import java.sql.SQLException;

public class ProductStockTransactionDAOImpl implements ProductStockTransactionDAO {

    ProductStockDAOImpl productStockDAOImpl = new ProductStockDAOImpl();
    RepairStockDAOImpl repairStockDAOImpl = new RepairStockDAOImpl();

    @Override
    public boolean saveProduct(ProductStockDto productStockDto) throws SQLException {
        Connection connection = null;
        boolean update = false;

        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isSaved = productStockDAOImpl.save(productStockDto);

            if (isSaved) {
                boolean saved = repairStockDAOImpl.save(productStockDto.getProduct_Id(), 0);
                if (saved) {
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
