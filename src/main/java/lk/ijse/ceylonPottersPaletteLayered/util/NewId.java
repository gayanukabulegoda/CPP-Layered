package lk.ijse.ceylonPottersPaletteLayered.util;

import javafx.scene.control.Alert;

import java.util.ArrayList;

public class NewId {
    public static String newId(ArrayList<String> list, GetType getType) {
        int maxIdNum = 0;

        // Find the maximum ID number in the list
        for (String id : list) {
            try {
                String[] split = id.split("[^0-9]+");
                int idNum = Integer.parseInt(split[1]);
                if (idNum > maxIdNum) {
                    maxIdNum = idNum;
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage());
            }
        }

        // Increment the maximum ID number
        maxIdNum++;

        // Generate the new ID based on the type
        switch (getType) {
            case EMPLOYEE:
                return "E-00" + maxIdNum;
            case SUPPLIER:
                return "S-00" + maxIdNum;
            case CUSTOMER:
                return "C-00" + maxIdNum;
            case PRODUCT_STOCK:
                return "P-00" + maxIdNum;
            case ITEM_STOCK:
                return "I-00" + maxIdNum;
            case ATTENDANCE_ID:
                return "A-00" + maxIdNum;
            case CUSTOMER_ORDER:
                return "CO-0" + maxIdNum;
            case SUPPLIER_ORDER:
                return "SO-0" + maxIdNum;
            case SALARY_ID:
                return "SI-0" + maxIdNum;
            default:
                return null;
        }
    }

    public enum GetType {
        EMPLOYEE, SUPPLIER, CUSTOMER, PRODUCT_STOCK, ITEM_STOCK,
        SUPPLIER_ORDER, CUSTOMER_ORDER, ATTENDANCE_ID, SALARY_ID
    }
}
