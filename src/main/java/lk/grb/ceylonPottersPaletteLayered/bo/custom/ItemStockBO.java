package lk.grb.ceylonPottersPaletteLayered.bo.custom;

import lk.grb.ceylonPottersPaletteLayered.bo.SuperBO;
import lk.grb.ceylonPottersPaletteLayered.dto.ItemStockDto;
import lk.grb.ceylonPottersPaletteLayered.entity.ItemStock;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemStockBO extends SuperBO {
    ArrayList<String> getAllItemId() throws SQLException;

    boolean saveItem(ItemStockDto dto) throws SQLException;

    ItemStockDto getItemData(String id) throws SQLException;

    String getItemDescription(String id) throws SQLException;

    boolean updateItemFromPopUp(ItemStockDto dto) throws SQLException;
}
