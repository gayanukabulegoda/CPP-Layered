package lk.grb.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.bo.custom.ProductStockBO;
import lk.grb.ceylonPottersPaletteLayered.dao.DAOFactory;
import lk.grb.ceylonPottersPaletteLayered.dao.custom.ProductStockDAO;
import lk.grb.ceylonPottersPaletteLayered.dto.ProductStockDto;
import lk.grb.ceylonPottersPaletteLayered.entity.ProductStock;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductStockBOImpl implements ProductStockBO {

    ProductStockDAO productStockDAO =
            (ProductStockDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.PRODUCT_STOCK);

    @Override
    public ArrayList<String> getAllProductId() throws SQLException {
        return productStockDAO.getAllId();
    }

    @Override
    public boolean saveProduct(ProductStockDto dto) throws SQLException {
        return productStockDAO.save(
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
