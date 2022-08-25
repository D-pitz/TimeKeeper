package com.paychex.timekeeper.filter.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShiftInfo {
    private long userId;
    private long shiftId;
    private double avgBreak;
    private double avgLunch;
    private int shiftsWorked;
}
