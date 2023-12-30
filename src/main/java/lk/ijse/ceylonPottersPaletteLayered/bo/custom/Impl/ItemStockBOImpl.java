package lk.ijse.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.ijse.ceylonPottersPaletteLayered.dao.DAOFactory;
import lk.ijse.ceylonPottersPaletteLayered.bo.custom.ItemStockBO;
import lk.ijse.ceylonPottersPaletteLayered.dao.custom.ItemStockDAO;
import lk.ijse.ceylonPottersPaletteLayered.dto.ItemStockDto;
import lk.ijse.ceylonPottersPaletteLayered.entity.ItemStock;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemStockBOImpl implements ItemStockBO {

    ItemStockDAO itemStockDAO =
            (ItemStockDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.ITEM_STOCK);

    @Override
    public ArrayList<String> getAllItemId() throws SQLException {
        return itemStockDAO.getAllId();
    }

    @Override
    public boolean saveItem(ItemStockDto dto) throws SQLException {
        return itemStockDAO.save(
                new ItemStock(
                        dto.getItem_Id(),
                        dto.getDescription(),
                        dto.getUnit_Price(),
                        dto.getQty_On_Hand()
                )
        );
    }

    @Override
    public ItemStockDto getItemData(String id) throws SQLException {
        ItemStock itemStock = itemStockDAO.getData(id);
        return new ItemStockDto(
                itemStock.getItem_Id(),
                itemStock.getDescription(),
                itemStock.getUnit_Price(),
                itemStock.getQty_On_Hand()
        );
    }

    @Override
    public String getItemDescription(String id) throws SQLException {
        return itemStockDAO.getDescription(id);
    }

    @Override
    public boolean updateItemFromPopUp(ItemStockDto dto) throws SQLException {
        return itemStockDAO.updateFromPopUp(
                new ItemStock(
                        dto.getItem_Id(),
                        dto.getDescription(),
                        dto.getUnit_Price(),
                        dto.getQty_On_Hand()
                )
        );
    }
}
