package lk.ijse.ceylonPottersPaletteLayered.bo.custom;

import lk.ijse.ceylonPottersPaletteLayered.bo.SuperBO;

import java.sql.SQLException;

public interface ConfirmationPopUpBO extends SuperBO {
    boolean deleteEmployee(String id) throws SQLException;

    boolean deleteSupplier(String id) throws SQLException;

    boolean deleteCustomer(String id) throws SQLException;

    boolean deleteAttendance(String id) throws SQLException;

    boolean deleteProductStock(String id) throws SQLException;

    boolean deleteItemStock(String id) throws SQLException;
}
