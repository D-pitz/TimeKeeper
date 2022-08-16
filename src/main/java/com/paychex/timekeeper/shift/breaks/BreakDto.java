package com.paychex.timekeeper.shift.breaks;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paychex.timekeeper.shift.breaks.brk.Break;
import com.paychex.timekeeper.shift.breaks.lunch.Lunch;
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
public class BreakDto {

    @JsonFormat(pattern = DATE)
    private LocalDateTime start;

    @JsonFormat(pattern = DATE)
    private LocalDateTime end;

    private boolean complete;

    public static BreakDto of (Break b) {
        return BreakDto.builder()
                .start(b.getStart())
                .end(b.getEnd())
                .complete(b.isComplete())
                .build();
    }

    public static BreakDto of (Lunch lunch) {
        return BreakDto.builder()
                .start(lunch.getStart())
                .end(lunch.getEnd())
                .complete(lunch.isComplete())
                .build();
    }
}
