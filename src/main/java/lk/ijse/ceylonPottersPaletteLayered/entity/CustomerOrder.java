package lk.ijse.ceylonPottersPaletteLayered.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CustomerOrder {
    private String customer_Order_Id;
    private String customer_Id;
    private double total_Price;
    private String date;
    private String time;
    private ArrayList<String[]> orderList;
}
