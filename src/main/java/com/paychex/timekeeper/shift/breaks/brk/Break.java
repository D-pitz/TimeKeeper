package com.paychex.timekeeper.shift.breaks.brk;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paychex.timekeeper.shift.breaks.Breaks;
import com.paychex.timekeeper.shift.model.Shift;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static com.paychex.timekeeper.constant.Constant.DATE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Break implements Breaks {

    @Id
    @NotNull
    private long shiftId;

    @JsonFormat(pattern = DATE)
    private LocalDateTime start;

    @JsonFormat(pattern = DATE)
    private LocalDateTime end;

    private boolean complete = false;

    @OneToOne(mappedBy = "aBreak")
    private Shift shift;


    public Break(Shift shift) {
        assignId(shift.getShiftId());
        this.shift = shift;
    }

    @Override
    public void assignId(long id) {
        this.shiftId = id;
    }

    @Override
    public void startBreak() {
        this.start = LocalDateTime.now();
    }

    @Override
    public void endBreak() {
        this.end = LocalDateTime.now();
        this.complete = true;
    }
}
