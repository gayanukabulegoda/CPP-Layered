package lk.ijse.ceylonPottersPaletteLayered.bo.custom;

import lk.ijse.ceylonPottersPaletteLayered.bo.SuperBO;
import lk.ijse.ceylonPottersPaletteLayered.dto.ItemStockDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemStockBO extends SuperBO {
    ArrayList<String> getAllItemId() throws SQLException;

    boolean saveItem(ItemStockDto dto) throws SQLException;

    ItemStockDto getItemData(String id) throws SQLException;

    String getItemDescription(String id) throws SQLException;

    boolean updateItemFromPopUp(ItemStockDto dto) throws SQLException;
}
