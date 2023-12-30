package lk.ijse.ceylonPottersPaletteLayered.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ItemStock {
    private String item_Id;
    private String description;
    private double unit_Price;
    private int qty_On_Hand;
}
