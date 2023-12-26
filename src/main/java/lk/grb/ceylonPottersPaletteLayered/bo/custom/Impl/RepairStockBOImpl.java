package lk.grb.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.bo.custom.RepairStockBO;
import lk.grb.ceylonPottersPaletteLayered.dao.DAOFactory;
import lk.grb.ceylonPottersPaletteLayered.dao.custom.ProductStockDAO;
import lk.grb.ceylonPottersPaletteLayered.dao.custom.RemoveRepairedStockDAO;
import lk.grb.ceylonPottersPaletteLayered.dao.custom.RepairStockDAO;
import lk.grb.ceylonPottersPaletteLayered.dao.custom.UpdateRepairStockDAO;
import lk.grb.ceylonPottersPaletteLayered.dto.ProductStockDto;
import lk.grb.ceylonPottersPaletteLayered.dto.RepairStockDto;
import lk.grb.ceylonPottersPaletteLayered.entity.ProductStock;
import lk.grb.ceylonPottersPaletteLayered.entity.RepairStock;

import java.sql.SQLException;
import java.util.ArrayList;

public class RepairStockBOImpl implements RepairStockBO {

    RepairStockDAO repairStockDAO =
            (RepairStockDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.REPAIR_STOCK);

    ProductStockDAO productStockDAO =
            (ProductStockDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.PRODUCT_STOCK);

    RemoveRepairedStockDAO removeRepairedStockDAO =
            (RemoveRepairedStockDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.REMOVE_REPAIRED_STOCK);

    UpdateRepairStockDAO updateRepairStockDAO =
            (UpdateRepairStockDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.UPDATE_REPAIR_STOCK);

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
        return removeRepairedStockDAO.removeRepairStock(product_Id, qty);
    }

    @Override
    public boolean updateRepairStock(String product_Id, String qty) throws SQLException {
        return updateRepairStockDAO.updateRepairStock(product_Id, qty);
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
