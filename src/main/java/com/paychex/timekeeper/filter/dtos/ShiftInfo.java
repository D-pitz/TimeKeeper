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
    private double totalLunch;
    private double totalBreak;
    private double averageLunch;
    private double averageBreak;
    private int shiftsWorked;
}
