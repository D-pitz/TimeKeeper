package com.paychex.timekeeper.shift.breaks.brk;

import com.paychex.timekeeper.exception.ApiException;
import com.paychex.timekeeper.shift.model.ShiftRepo;
import com.paychex.timekeeper.shift.model.util.ShiftBreakDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import static com.paychex.timekeeper.constant.Messages.INVALID_ID;

@Service
public class BreakService {

    private final Logger LOG = LoggerFactory.getLogger(BreakService.class);
    private final String CLASS = this.getClass().getSimpleName();

    @Autowired
    private BreakRepo breakRepo;

    @Autowired
    private ShiftRepo shiftRepo;

    private final BreakValidator validator = new BreakValidator();

    public ShiftBreakDto startBreak(long shiftId) {
        try {
            Break br = breakRepo.getById(shiftId);
            validator.validStartBreak(br);
            breakRepo.save(br);
            return ShiftBreakDto.of(br.getShift());
        } catch (EntityNotFoundException e) {
            throw new ApiException(INVALID_ID, e, CLASS);
        }
    }

    public ShiftBreakDto endBreak(long shiftId) {
        try {
            Break br = breakRepo.getById(shiftId);
            validator.validEndBreak(br);
            breakRepo.save(br);
            return ShiftBreakDto.of(br.getShift());
        } catch (EntityNotFoundException e) {
            throw new ApiException(INVALID_ID, e, CLASS);
        }
    }
}
