package lk.grb.ceylonPottersPaletteLayered.bo.custom.Impl;

import lk.grb.ceylonPottersPaletteLayered.bo.custom.DashboardBO;
import lk.grb.ceylonPottersPaletteLayered.dto.CustomerOrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class DashboardBOImpl implements DashboardBO {
    @Override
    public ArrayList<String> getAllCustomerOrderIdS() throws SQLException {
        return null;
    }

    @Override
    public double getCustomerOrderTotal() throws SQLException {
        return 0;
    }

    @Override
    public double getSupplierOrderTotal() throws SQLException {
        return 0;
    }

    @Override
    public ArrayList<String> getAllProductId() throws SQLException {
        return null;
    }

    @Override
    public String getProductQtyTotal(String id) throws SQLException {
        return null;
    }

    @Override
    public String getTodayAttendance() throws SQLException {
        return null;
    }

    @Override
    public String getAvailableClayStock() throws SQLException {
        return null;
    }

    @Override
    public String getTodaySales() throws SQLException {
        return null;
    }

    @Override
    public String getCustomerCount() throws SQLException {
        return null;
    }

    @Override
    public String getSupplierCount() throws SQLException {
        return null;
    }

    @Override
    public CustomerOrderDto getCustomerOrderData(String id) throws SQLException {
        return null;
    }
}
