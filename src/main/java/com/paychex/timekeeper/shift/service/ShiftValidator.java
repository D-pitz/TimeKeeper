package com.paychex.timekeeper.shift.service;

import com.paychex.timekeeper.messages.ErrorMessage;
import com.paychex.timekeeper.shift.model.Shift;
import com.paychex.timekeeper.user.model.User;
import com.paychex.timekeeper.user.model.util.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

import static com.paychex.timekeeper.constant.Messages.INCOMPLETE;

public class ShiftValidator {

    private final String CLASS = this.getClass().getSimpleName();
    private final Logger LOG = LoggerFactory.getLogger(ShiftValidator.class);

    public boolean validStartShift(List<Shift> shifts) {
        shifts = shifts.stream()
                .filter(shift -> !shift.isComplete())
                .collect(Collectors.toList());
        return shifts.isEmpty();
    }

    public User assignShift(User user, Shift shift) {
        shift.createBreaks();
        shift.setUser(user);
        user.getShifts().add(shift);
        return user;
    }

    public Shift validEndShift(Shift shift) {
        if (shift.getLunch().isComplete() && shift.getABreak().isComplete()) {
            shift.endShift();
            return shift;
        }
        else {
            throw new ErrorMessage(INCOMPLETE);
        }
    }
}
