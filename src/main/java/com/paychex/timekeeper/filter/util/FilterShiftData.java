package com.paychex.timekeeper.filter.util;

import com.paychex.timekeeper.filter.dtos.ShiftInfo;
import com.paychex.timekeeper.shift.model.Shift;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class FilterShiftData {

    private ShiftInfo info = new ShiftInfo();

    public ShiftInfo findLunchSum(List<Shift> shifts) {
        return null;
    }
}
