package com.paychex.timekeeper.shift.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paychex.timekeeper.shift.breaks.brk.Break;
import com.paychex.timekeeper.shift.breaks.lunch.Lunch;
import com.paychex.timekeeper.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.paychex.timekeeper.constant.Constant.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "shift_id")
    private long shiftId;

    @JsonFormat(pattern = DATE)
    private LocalDateTime start = LocalDateTime.now();

    @JsonFormat(pattern = DATE)
    private LocalDateTime end;

    private boolean complete = false;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "lunch")
    private Lunch lunch;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "break")
    private Break aBreak;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void createBreaks() {
        this.lunch = new Lunch(this);
        this.aBreak = new Break(this);
    }

    public void endShift() {
        this.end = LocalDateTime.now();
        this.setComplete(true);
    }
}
