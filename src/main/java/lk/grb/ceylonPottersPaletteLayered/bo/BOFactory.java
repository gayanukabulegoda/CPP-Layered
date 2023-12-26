package lk.grb.ceylonPottersPaletteLayered.bo;

import lk.grb.ceylonPottersPaletteLayered.bo.custom.Impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public BOFactory getBoFactory() {
        return (boFactory == null) ?
                boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        ATTENDANCE, EMPLOYEE, SALARY,
        CUSTOMER, CUSTOMER_ORDER,
        ITEM_STOCK, PRODUCT_STOCK, REPAIR_STOCK,
        SUPPLIER, SUPPLIER_ORDER,
        CONFIRMATION_POPUP, DASHBOARD, USER
    }

    public SuperBO getBO(BOTypes boTypes) {
        switch (boTypes) {
            case ATTENDANCE:
                return new AttendanceBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case SALARY:
                return new SalaryBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case CUSTOMER_ORDER:
                return new CustomerOrderBOImpl();
            case ITEM_STOCK:
                return new ItemStockBOImpl();
            case PRODUCT_STOCK:
                return new ProductStockBOImpl();
            case REPAIR_STOCK:
                return new RepairStockBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case SUPPLIER_ORDER:
                return new SupplierOrderBOImpl();
            case CONFIRMATION_POPUP:
                return new ConfirmationPopUpBOImpl();
            case DASHBOARD:
                return new DashboardBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
