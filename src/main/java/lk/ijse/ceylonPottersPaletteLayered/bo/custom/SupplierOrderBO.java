package lk.ijse.ceylonPottersPaletteLayered.bo.custom;

import lk.ijse.ceylonPottersPaletteLayered.bo.SuperBO;
import lk.ijse.ceylonPottersPaletteLayered.dto.SupplierDto;
import lk.ijse.ceylonPottersPaletteLayered.dto.SupplierOrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierOrderBO extends SuperBO {
    String[] itemDescAndUnitPriceGet(String id) throws SQLException;

    ArrayList<String> getAllSupplierId() throws SQLException;

    boolean placeSupplierOrder(SupplierOrderDto dto) throws SQLException;

    String getItemQtyOnHand(String id) throws SQLException;

    String getItemUnitPrice(String id) throws SQLException;

    String getItemDescription(String id) throws SQLException;

    ArrayList<String> getAllItemId() throws SQLException;

    String getSupplierName(String id) throws SQLException;

    SupplierOrderDto getSupplierOrderData(String id) throws SQLException;

    SupplierDto getSupplierData(String id) throws SQLException;

    ArrayList<String> getAllSupplierOrderId() throws SQLException;

    String getSupplierIdForOrder(String id) throws SQLException;

    ArrayList<String> getSupplierId(String id) throws SQLException;

    String getSupplierContactNo(String id) throws SQLException;

    ArrayList<String> getSelectedAllSupplierOrderId(String id) throws SQLException;

    ArrayList<String[]> getSupplierOrderDataAsAnArray(String id) throws SQLException;

    double getTotal(String unitPrice, String qty);
}
