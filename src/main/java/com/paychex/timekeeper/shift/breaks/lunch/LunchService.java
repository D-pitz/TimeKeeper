package com.paychex.timekeeper.shift.breaks.lunch;

import com.paychex.timekeeper.exception.ApiException;
import com.paychex.timekeeper.shift.model.util.ShiftBreakDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import static com.paychex.timekeeper.constant.Messages.INVALID_ID;

@Service
public class LunchService {

    private final String CLASS = this.getClass().getSimpleName();
    private final Logger LOG = LoggerFactory.getLogger(LunchService.class);

    @Autowired
    private LunchRepo lunchRepo;

    private final LunchValidator validator = new LunchValidator();

    public ShiftBreakDto startLunch(long shiftId) {
        try {
            Lunch lunch = lunchRepo.getById(shiftId);
            validator.validStartLunch(lunch);
            lunchRepo.save(lunch);
            return ShiftBreakDto.of(lunch.getShift());
        } catch (EntityNotFoundException e) {
            throw new ApiException(INVALID_ID, e, CLASS);
        }
    }

    public ShiftBreakDto endLunch(long shiftId) {
        try {
            Lunch lunch = lunchRepo.getById(shiftId);
            validator.validEndLunch(lunch);
            lunchRepo.save(lunch);
            return ShiftBreakDto.of(lunch.getShift());
        } catch (EntityNotFoundException e) {
            throw new ApiException(INVALID_ID, e, CLASS);
        }
    }
}
