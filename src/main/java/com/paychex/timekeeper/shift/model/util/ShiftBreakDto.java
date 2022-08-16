package com.paychex.timekeeper.shift.model.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paychex.timekeeper.shift.model.Shift;
import com.paychex.timekeeper.shift.breaks.BreakDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.paychex.timekeeper.constant.Constant.DATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShiftBreakDto {

    private long userId;
    private long shiftId;
    private String role;
    @JsonFormat(pattern = DATE)
    private LocalDateTime start;
    @JsonFormat(pattern = DATE)
    private LocalDateTime end;
    private BreakDto lunch;
    private BreakDto aBreak;

    public static ShiftBreakDto of (Shift shift) {
        return ShiftBreakDto.builder()
                .shiftId(shift.getShiftId())
                .start(shift.getStart())
                .end(shift.getEnd())
                .lunch(BreakDto.of(shift.getLunch()))
                .aBreak(BreakDto.of(shift.getABreak()))
                .userId(shift.getUser().getId())
                .role(shift.getUser().getRole())
                .build();
    }
}
