package lk.grb.ceylonPottersPaletteLayered.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ProductStockDto {
    private String product_Id;
    private String description;
    private int qty_On_Hand;
    private double unit_Price;
    private String category;
    private int qty;
}
