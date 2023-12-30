package lk.ijse.ceylonPottersPaletteLayered.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class SupplierOrderDto {
    private String supplier_Order_Id;
    private String supplier_Id;
    private double total_Price;
    private String date;
    private String time;
    private ArrayList<String[]> orderList;
}
