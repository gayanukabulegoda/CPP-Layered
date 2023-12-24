package lk.grb.ceylonPottersPaletteLayered.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmployeeAttendanceDto {
    private String attendance_Id;
    private String employee_Id;
    private String date;
    private String time;
}
