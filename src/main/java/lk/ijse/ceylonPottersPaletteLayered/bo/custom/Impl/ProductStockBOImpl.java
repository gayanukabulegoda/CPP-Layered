package lk.ijse.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.ijse.ceylonPottersPaletteLayered.dao.DAOFactory;
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.ProductStockBO;
import lk.ijse.ceylonPottersPaletteLayered.dao.custom.ProductStockDAO;
import lk.ijse.ceylonPottersPaletteLayered.dao.custom.RepairStockDAO;
import lk.ijse.ceylonPottersPaletteLayered.dto.ProductStockDto;
import lk.ijse.ceylonPottersPaletteLayered.entity.ProductStock;
import lk.ijse.ceylonPottersPaletteLayered.util.TransactionConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductStockBOImpl implements ProductStockBO {

    ProductStockDAO productStockDAO =
            (ProductStockDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.PRODUCT_STOCK);

    RepairStockDAO repairStockDAO =
            (RepairStockDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.REPAIR_STOCK);

    @Override
    public ArrayList<String> getAllProductId() throws SQLException {
        return productStockDAO.getAllId();
    }

    @Override
    public boolean saveProduct(ProductStockDto dto) throws SQLException {
        boolean isUpdated = false;

        ProductStock entity = new ProductStock(
                dto.getProduct_Id(),
                dto.getDescription(),
                dto.getQty_On_Hand(),
                dto.getUnit_Price(),
                dto.getCategory(),
                dto.getQty()
        );

        Connection connection = TransactionConnection.getDbConnection();

        if (productStockDAO.save(entity)) {

            if (repairStockDAO.save(entity.getProduct_Id(), 0)) {
                TransactionConnection.commit(connection);
                isUpdated = true;
            }
            else TransactionConnection.rollBack(connection);
        }
        else TransactionConnection.rollBack(connection);

        return isUpdated;
    }

    @Override
    public ProductStockDto getProductData(String id) throws SQLException {
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
    public String getProductDescription(String id) throws SQLException {
        return productStockDAO.getDescription(id);
    }

    @Override
    public boolean updateFromPopUp(ProductStockDto dto) throws SQLException {
        return productStockDAO.updateFromPopUp(
                new ProductStock(
                        dto.getProduct_Id(),
                        dto.getDescription(),
                        dto.getQty_On_Hand(),
                        dto.getUnit_Price(),
                        dto.getCategory(),
                        dto.getQty()
                )
        );
    }
}
