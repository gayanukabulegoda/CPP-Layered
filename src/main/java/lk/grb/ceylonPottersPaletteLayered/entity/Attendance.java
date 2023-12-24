package lk.grb.ceylonPottersPaletteLayered.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Attendance {
    private String attendance_Id;
    private String employee_Id;
    private String date;
    private String time;
}
