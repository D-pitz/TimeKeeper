package com.paychex.timekeeper.shift.breaks.brk;

import com.paychex.timekeeper.messages.ErrorMessage;
import com.paychex.timekeeper.shift.breaks.lunch.Lunch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.paychex.timekeeper.constant.Messages.*;

public class BreakValidator {

    private final Logger LOG = LoggerFactory.getLogger(BreakValidator.class);

    public void validStartBreak(Break br) {
        Lunch lunch = br.getShift().getLunch();
        if (!lunch.isComplete() && lunch.getStart() != null)
            throw new ErrorMessage(INVALID_START);
        else if (br.getStart() != null)
            throw new ErrorMessage(DOUBLE_BREAK);
        br.startBreak();
    }

    public void validEndBreak(Break br) {
        if (br.isComplete())
            throw new ErrorMessage(DOUBLE_BREAK);
        else if (br.getStart() == null)
            throw new ErrorMessage(INVALID_END);
        br.endBreak();
    }
}
