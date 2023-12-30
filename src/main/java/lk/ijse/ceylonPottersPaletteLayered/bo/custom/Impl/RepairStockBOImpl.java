package lk.ijse.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.ijse.ceylonPottersPaletteLayered.dao.DAOFactory;
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.RepairStockBO;
import lk.ijse.ceylonPottersPaletteLayered.dao.custom.ProductStockDAO;
import lk.ijse.ceylonPottersPaletteLayered.dao.custom.RepairStockDAO;
import lk.ijse.ceylonPottersPaletteLayered.dto.ProductStockDto;
import lk.ijse.ceylonPottersPaletteLayered.dto.RepairStockDto;
import lk.ijse.ceylonPottersPaletteLayered.entity.ProductStock;
import lk.ijse.ceylonPottersPaletteLayered.entity.RepairStock;
import lk.ijse.ceylonPottersPaletteLayered.util.TransactionConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepairStockBOImpl implements RepairStockBO {

    RepairStockDAO repairStockDAO =
            (RepairStockDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.REPAIR_STOCK);

    ProductStockDAO productStockDAO =
            (ProductStockDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.PRODUCT_STOCK);

    @Override
    public RepairStockDto getRepairStockData(String id) throws SQLException {
        RepairStock repairStock = repairStockDAO.getData(id);
        return new RepairStockDto(
                repairStock.getProduct_Id(),
                repairStock.getQty_To_Repair()
        );
    }

    @Override
    public ProductStockDto getProductStockData(String id) throws SQLException {
        ProductStock productStock = productStockDAO.getData(id);
        return new ProductStockDto(
                productStock.getProduct_Id(),
                productStock.getDescription(),
                productStock.getQty_On_Hand(),
                productStock.getUnit_Price(),
                productStock.getCategory(),
                productStock.getQty()
        );
    }

    @Override
    public boolean removeRepairStock(String product_Id, String qty) throws SQLException {
        boolean isUpdated = false;

        Connection connection = TransactionConnection.getDbConnection();

        if (productStockDAO.updateIncrement(product_Id, qty)) {

            if (repairStockDAO.updateDecrement(product_Id, qty)) {
                TransactionConnection.commit(connection);
                isUpdated = true;
            }
            else TransactionConnection.rollBack(connection);
        }
        else TransactionConnection.rollBack(connection);

        return isUpdated;
    }

    @Override
    public boolean updateRepairStock(String product_Id, String qty) throws SQLException {
        boolean isUpdated = false;

        Connection connection = TransactionConnection.getDbConnection();

        if (productStockDAO.update(product_Id, qty)) {

            if (repairStockDAO.update(product_Id, qty)) {
                TransactionConnection.commit(connection);
                isUpdated = true;
            }
            else TransactionConnection.rollBack(connection);
        }
        else TransactionConnection.rollBack(connection);

        return isUpdated;
    }

    @Override
    public ArrayList<String> getAllProductId() throws SQLException {
        return productStockDAO.getAllId();
    }

    @Override
    public String getProductDescription(String id) throws SQLException {
        return productStockDAO.getDescription(id);
    }
}
