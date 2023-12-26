package lk.grb.ceylonPottersPaletteLayered.dao;

import lk.grb.ceylonPottersPaletteLayered.dao.custom.Impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ?
                daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER, CUSTOMER_ORDER, CUSTOMER_ORDER_DETAIL,
        EMPLOYEE, EMPLOYEE_ATTENDANCE, EMPLOYEE_SALARY,
        ITEM_STOCK, PRODUCT_STOCK, REPAIR_STOCK,
        SUPPLIER, SUPPLIER_ORDER, SUPPLIER_ORDER_DETAIL,
        PLACE_CUSTOMER_ORDER, PLACE_SUPPLIER_ORDER, PRODUCT_STOCK_TRANSACTION,
        REMOVE_REPAIRED_STOCK, UPDATE_REPAIR_STOCK, USER
    }

    public SuperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case CUSTOMER_ORDER:
                return new CustomerOrderDAOImpl();
            case CUSTOMER_ORDER_DETAIL:
                return new CustomerOrderDetailDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case EMPLOYEE_ATTENDANCE:
                return new EmployeeAttendanceDAOImpl();
            case EMPLOYEE_SALARY:
                return new EmployeeSalaryDAOImpl();
            case ITEM_STOCK:
                return new ItemStockDAOImpl();
            case PRODUCT_STOCK:
                return new ProductStockDAOImpl();
            case REPAIR_STOCK:
                return new RepairStockDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case SUPPLIER_ORDER:
                return new SupplierOrderDAOImpl();
            case SUPPLIER_ORDER_DETAIL:
                return new SupplierOrderDetailDAOImpl();
            case PLACE_CUSTOMER_ORDER:
                return new PlaceCustomerOrderDAOImpl();
            case PLACE_SUPPLIER_ORDER:
                return new PlaceSupplierOrderDAOImpl();
            case PRODUCT_STOCK_TRANSACTION:
                return new ProductStockTransactionDAOImpl();
            case REMOVE_REPAIRED_STOCK:
                return new RemoveRepairedStockDAOImpl();
            case UPDATE_REPAIR_STOCK:
                return new UpdateRepairStockDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}
