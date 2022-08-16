package com.paychex.timekeeper.shift.service;

import com.paychex.timekeeper.exception.ApiException;
import com.paychex.timekeeper.exception.Validator;
import com.paychex.timekeeper.messages.ErrorMessage;
import com.paychex.timekeeper.shift.model.Shift;
import com.paychex.timekeeper.shift.model.ShiftRepo;
import com.paychex.timekeeper.shift.model.util.ShiftBreakDto;
import com.paychex.timekeeper.shift.model.util.ShiftDto;
import com.paychex.timekeeper.user.model.User;
import com.paychex.timekeeper.user.model.UserRepo;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static com.paychex.timekeeper.constant.Messages.ACTIVE_SHIFT;
import static com.paychex.timekeeper.constant.Messages.INVALID_ID;

@Service
public class ShiftService {

    private final String CLASS = this.getClass().getSimpleName();
    private final Logger LOG = LoggerFactory.getLogger(ShiftService.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ShiftRepo shiftRepo;

    private final ShiftValidator validator = new ShiftValidator();

    public List<Shift> getAllShifts() {
        return shiftRepo.findAll();
    }

    public List<ShiftBreakDto> getAllShiftsDto() {
        List<Shift> shifts = shiftRepo.findAll();
        return shifts.stream()
                .map(ShiftBreakDto::of)
                .collect(Collectors.toList());
    }

    public Object startShift(long userId) {
        try {
            User user = userRepo.getById(userId);
            if (validator.validStartShift(user.getShifts())) {
                Shift shift = shiftRepo.save(new Shift());
                userRepo.save(validator.assignShift(user, shift));
                return ShiftDto.of(shift);
            }
            throw new ErrorMessage(ACTIVE_SHIFT);
        } catch (EntityNotFoundException e) {
            throw new ApiException(INVALID_ID, e, CLASS);
        }
    }

    public ShiftBreakDto endShift(long shiftId) {
        try {
            Shift shift = shiftRepo.getById(shiftId);
            validator.validEndShift(shift);
            return ShiftBreakDto.of(shiftRepo.save(shift));
        } catch (EntityNotFoundException e) {
            throw new ApiException(INVALID_ID, e, CLASS);
        }
    }

    public ShiftBreakDto getShift(long shiftId) {
        try {
            return ShiftBreakDto.of(shiftRepo.getById(shiftId));
        } catch (EntityNotFoundException e) {
            throw new ApiException(INVALID_ID, e, CLASS);
        }
    }
}
