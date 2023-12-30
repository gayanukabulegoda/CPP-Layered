package lk.ijse.ceylonPottersPaletteLayered.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class RepairStock {
    private String product_Id;
    private String qty_To_Repair;
}
