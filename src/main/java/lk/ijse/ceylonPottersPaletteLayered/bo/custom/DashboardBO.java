package lk.ijse.ceylonPottersPaletteLayered.bo.custom;

import lk.ijse.ceylonPottersPaletteLayered.bo.SuperBO;
import lk.ijse.ceylonPottersPaletteLayered.dto.CustomerOrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DashboardBO extends SuperBO {
    ArrayList<String> getAllCustomerOrderIdS() throws SQLException;

    double getCustomerOrderTotal() throws SQLException;

    double getSupplierOrderTotal() throws SQLException;

    double getSalaryTotal() throws SQLException;

    ArrayList<String> getAllProductId() throws SQLException;

    String getProductQtyTotal(String id) throws SQLException;

    String getTodayAttendance() throws SQLException;

    String getAvailableClayStock() throws SQLException;

    String getTodaySales() throws SQLException;

    String getCustomerCount() throws SQLException;

    String getSupplierCount() throws SQLException;

    CustomerOrderDto getCustomerOrderData(String id) throws SQLException;
}
