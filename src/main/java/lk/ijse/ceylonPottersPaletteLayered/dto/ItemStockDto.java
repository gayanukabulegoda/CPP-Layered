package lk.ijse.ceylonPottersPaletteLayered.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ItemStockDto {
    private String item_Id;
    private String description;
    private double unit_Price;
    private int qty_On_Hand;
}
