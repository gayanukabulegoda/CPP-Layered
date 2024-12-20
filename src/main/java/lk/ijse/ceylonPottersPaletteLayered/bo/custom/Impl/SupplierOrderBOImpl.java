package lk.ijse.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.ijse.ceylonPottersPaletteLayered.bo.custom.SupplierOrderBO;
import lk.ijse.ceylonPottersPaletteLayered.dao.DAOFactory;
import lk.ijse.ceylonPottersPaletteLayered.dao.custom.ItemStockDAO;
import lk.ijse.ceylonPottersPaletteLayered.dao.custom.SupplierDAO;
import lk.ijse.ceylonPottersPaletteLayered.dao.custom.SupplierOrderDAO;
import lk.ijse.ceylonPottersPaletteLayered.dao.custom.SupplierOrderDetailDAO;
import lk.ijse.ceylonPottersPaletteLayered.dto.SupplierDto;
import lk.ijse.ceylonPottersPaletteLayered.dto.SupplierOrderDto;
import lk.ijse.ceylonPottersPaletteLayered.entity.Supplier;
import lk.ijse.ceylonPottersPaletteLayered.entity.SupplierOrder;
import lk.ijse.ceylonPottersPaletteLayered.util.TransactionConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderBOImpl implements SupplierOrderBO {

    SupplierOrderDAO supplierOrderDAO =
            (SupplierOrderDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.SUPPLIER_ORDER);

    ItemStockDAO itemStockDAO =
            (ItemStockDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.ITEM_STOCK);

    SupplierDAO supplierDAO =
            (SupplierDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.SUPPLIER);

    SupplierOrderDetailDAO supplierOrderDetailDAO =
            (SupplierOrderDetailDAO) DAOFactory.getDaoFactory().
                    getDAO(DAOFactory.DAOTypes.SUPPLIER_ORDER_DETAIL);

    @Override
    public String[] itemDescAndUnitPriceGet(String id) throws SQLException {
        return itemStockDAO.descAndUnitPriceGet(id);
    }

    @Override
    public ArrayList<String> getAllSupplierId() throws SQLException {
        return supplierDAO.getAllId();
    }

    @Override
    public boolean placeSupplierOrder(SupplierOrderDto dto) throws SQLException {
        boolean isSaved = false;

        SupplierOrder entity = new SupplierOrder(
                dto.getSupplier_Order_Id(),
                dto.getSupplier_Id(),
                dto.getTotal_Price(),
                dto.getDate(),
                dto.getTime(),
                dto.getOrderList()
        );

        Connection connection = TransactionConnection.getDbConnection();

        if (supplierOrderDAO.save(entity)) {

            if (itemStockDAO.update(entity.getOrderList())) {

                if (supplierOrderDetailDAO.save(entity)) {
                    TransactionConnection.commit(connection);
                    isSaved = true;
                }
                else TransactionConnection.rollBack(connection);
            }
            else TransactionConnection.rollBack(connection);
        }
        else TransactionConnection.rollBack(connection);

        return isSaved;
    }

    @Override
    public String getItemQtyOnHand(String id) throws SQLException {
        return itemStockDAO.getQtyOnHand(id);
    }

    @Override
    public String getItemUnitPrice(String id) throws SQLException {
        return itemStockDAO.getUnitPrice(id);
    }

    @Override
    public String getItemDescription(String id) throws SQLException {
        return itemStockDAO.getDescription(id);
    }

    @Override
    public ArrayList<String> getAllItemId() throws SQLException {
        return itemStockDAO.getAllId();
    }

    @Override
    public String getSupplierName(String id) throws SQLException {
        return supplierDAO.getSupplierName(id);
    }

    @Override
    public SupplierOrderDto getSupplierOrderData(String id) throws SQLException {
        SupplierOrder supplierOrder = supplierOrderDAO.getData(id);
        return new SupplierOrderDto(
                supplierOrder.getSupplier_Order_Id(),
                supplierOrder.getSupplier_Id(),
                supplierOrder.getTotal_Price(),
                supplierOrder.getDate(),
                supplierOrder.getTime(),
                supplierOrder.getOrderList()
        );
    }

    @Override
    public SupplierDto getSupplierData(String id) throws SQLException {
        Supplier supplier = supplierDAO.getData(id);
        return new SupplierDto(
                supplier.getSupplier_Id(),
                supplier.getName(),
                supplier.getEmail(),
                supplier.getContact_No(),
                supplier.getTime(),
                supplier.getDate(),
                supplier.getUser_Name()
        );
    }

    @Override
    public ArrayList<String> getAllSupplierOrderId() throws SQLException {
        return supplierOrderDAO.getAllId();
    }

    @Override
    public String getSupplierIdForOrder(String id) throws SQLException {
        return supplierOrderDAO.getSupplierIdForOrder(id);
    }

    @Override
    public ArrayList<String> getSupplierId(String id) throws SQLException {
        return supplierOrderDAO.getSupplierId(id);
    }

    @Override
    public String getSupplierContactNo(String id) throws SQLException {
        return supplierDAO.getSupplierContactNo(id);
    }

    @Override
    public ArrayList<String> getSelectedAllSupplierOrderId(String id) throws SQLException {
        return supplierOrderDAO.getSelectedAllSupplierOrderId(id);
    }

    @Override
    public ArrayList<String[]> getSupplierOrderDataAsAnArray(String id) throws SQLException {
        return supplierOrderDetailDAO.getDataAsAnArray(id);
    }

    @Override
    public double getTotal(String unitPrice, String qty) {
        return Double.parseDouble(unitPrice) * Integer.parseInt(qty);
    }
}
