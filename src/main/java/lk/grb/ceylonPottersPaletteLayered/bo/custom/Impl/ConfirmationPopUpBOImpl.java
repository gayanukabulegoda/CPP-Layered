package lk.grb.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.bo.custom.ConfirmationPopUpBO;

import java.sql.SQLException;

public class ConfirmationPopUpBOImpl implements ConfirmationPopUpBO {
    @Override
    public boolean deleteEmployee(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteAttendance(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteProductStock(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteItemStock(String id) throws SQLException {
        return false;
    }
}
