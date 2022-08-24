package com.paychex.timekeeper.user.model.util;

import com.paychex.timekeeper.shift.model.Shift;
import com.paychex.timekeeper.shift.model.util.ShiftBreakDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserShiftDto {
    private long id;
    private long role;
    private List<ShiftBreakDto> shiftBreakDto;


}
