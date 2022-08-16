package com.paychex.timekeeper.shift.breaks.lunch;

import com.paychex.timekeeper.messages.ErrorMessage;
import com.paychex.timekeeper.shift.breaks.brk.Break;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.paychex.timekeeper.constant.Messages.*;

public class LunchValidator {

    private final Logger LOG = LoggerFactory.getLogger(LunchValidator.class);

    public void validStartLunch(Lunch lunch) {
        Break br = lunch.getShift().getABreak();
        if (!br.isComplete() && br.getStart() != null)
            throw new ErrorMessage(INVALID_START);
        else if (lunch.getStart() != null)
            throw new ErrorMessage(DOUBLE_BREAK);
        lunch.startBreak();
    }

    public void validEndLunch(Lunch lunch) {
        Break br = lunch.getShift().getABreak();
        if (lunch.isComplete())
            throw new ErrorMessage(DOUBLE_BREAK);
        else if (lunch.getStart() == null)
            throw new ErrorMessage(INVALID_END);
        lunch.endBreak();
    }
}
