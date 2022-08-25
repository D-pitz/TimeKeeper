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
import org.springframework.web.servlet.View;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.paychex.timekeeper.constant.Messages.*;

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

    @Transactional
    public Object startShift(long userId) {
        try {
            User user = userRepo.getById(userId);
            if (validator.validStartShift(user.getShifts())) {
                Shift shift = shiftRepo.save(new Shift());
                userRepo.save(validator.assignShift(user, shift));
                return ShiftBreakDto.of(shift);
            }
            throw new ErrorMessage(ACTIVE_SHIFT);
        } catch (EntityNotFoundException e) {
            throw new ApiException(INVALID_ID, e, CLASS);
        }
    }

    public ShiftBreakDto endShift(long shiftId) {
        try {
            Shift shift = shiftRepo.getById(shiftId);
            return ShiftBreakDto.of(shiftRepo.save(validator.validEndShift(shift)));
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

    public ShiftBreakDto getActiveShift(long userId) {
        try {
            return ShiftBreakDto.of(shiftRepo.findShiftByUserAndComplete(userId, false));
        } catch (NullPointerException e) {
            throw new ApiException(NO_ACTIVE, e, CLASS);
        }
    }

    public List<ShiftBreakDto> getShiftsByUser(long userId) {
        List<Shift> shifts = shiftRepo.findAllShiftsByUserAndCompleteOrderByShiftIdDesc(userRepo.getById(userId), true);
        return shifts.stream().map(ShiftBreakDto::of).collect(Collectors.toList());
    }
}
