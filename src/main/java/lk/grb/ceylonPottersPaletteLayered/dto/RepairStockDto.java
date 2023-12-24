package lk.grb.ceylonPottersPaletteLayered.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class RepairStockDto {
    private String product_Id;
    private String qty_To_Repair;
}
