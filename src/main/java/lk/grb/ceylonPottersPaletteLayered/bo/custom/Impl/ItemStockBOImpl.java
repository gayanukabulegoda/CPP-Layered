package lk.grb.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.bo.custom.ItemStockBO;
import lk.grb.ceylonPottersPaletteLayered.dto.ItemStockDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemStockBOImpl implements ItemStockBO {
    @Override
    public ArrayList<String> getAllItemId() throws SQLException {
        return null;
    }

    @Override
    public boolean saveItem(ItemStockDto dto) throws SQLException {
        return false;
    }

    @Override
    public ItemStockDto getItemData(String id) throws SQLException {
        return null;
    }

    @Override
    public String getItemDescription(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean updateItemFromPopUp(ItemStockDto dto) throws SQLException {
        return false;
    }
}
