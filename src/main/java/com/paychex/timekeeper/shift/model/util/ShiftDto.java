package com.paychex.timekeeper.shift.model.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paychex.timekeeper.shift.model.Shift;
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
public class ShiftDto {

    private long userId;

    private long shiftId;

    @JsonFormat(pattern = DATE)
    private LocalDateTime start;

    @JsonFormat(pattern = DATE)
    private LocalDateTime end;

    private boolean closed;

    public static ShiftDto of (Shift shift) {
        return ShiftDto.builder()
                .shiftId(shift.getShiftId())
                .start(shift.getStart())
                .end(shift.getEnd())
                .closed(shift.isComplete())
                .userId(shift.getUser().getId())
                .build();
    }
}
