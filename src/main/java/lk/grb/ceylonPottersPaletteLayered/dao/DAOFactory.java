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
        EMPLOYEE, EMPLOYEE_ATTENDANCE, EMPLOYEE_SALARY, USER,
        ITEM_STOCK, PRODUCT_STOCK, REPAIR_STOCK,
        SUPPLIER, SUPPLIER_ORDER, SUPPLIER_ORDER_DETAIL
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
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}
