package com.paychex.timekeeper.user.admin;

import com.paychex.timekeeper.exception.ApiException;
import com.paychex.timekeeper.shift.breaks.brk.Break;
import com.paychex.timekeeper.shift.breaks.brk.BreakRepo;
import com.paychex.timekeeper.shift.breaks.lunch.Lunch;
import com.paychex.timekeeper.shift.breaks.lunch.LunchRepo;
import com.paychex.timekeeper.shift.model.Shift;
import com.paychex.timekeeper.shift.model.ShiftRepo;
import com.paychex.timekeeper.shift.model.util.ShiftBreakDto;
import com.paychex.timekeeper.shift.model.util.ShiftDto;
import com.paychex.timekeeper.shift.service.ShiftValidator;
import com.paychex.timekeeper.user.model.User;
import com.paychex.timekeeper.user.model.UserRepo;
import com.paychex.timekeeper.user.model.util.Role;
import com.paychex.timekeeper.user.model.util.UserDto;
import com.paychex.timekeeper.user.model.util.UserShiftDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.paychex.timekeeper.constant.Messages.INVALID_ID;

@Service
public class AdminService {

    private final Logger LOG = LoggerFactory.getLogger(AdminService.class);
    private final String CLASS = this.getClass().getSimpleName();

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ShiftRepo shiftRepo;

    @Autowired
    private LunchRepo lunchRepo;

    @Autowired
    private BreakRepo breakRepo;

    AdminValidator validator = new AdminValidator();

    public UserDto createUser(User userData) {
        validator.valid(userData);
        return UserDto.of(userRepo.save(userData));
    }

    public List<UserDto> getAll() {
        return userRepo.findAll().stream()
                .map(UserDto::of)
                .collect(Collectors.toList());
    }

    public UserDto login(User userData, User user) {
        try {
//            User user = userRepo.getById(userData.getId());
            LOG.info("" + userData);
            return UserDto.of(validator.verifyLogin(userData, user));
        } catch (EntityNotFoundException e) {
            throw new ApiException(INVALID_ID, e, CLASS);
        }
    }

    public ShiftDto startShift(long userId) {
        try {
            User user = userRepo.getById(userId);
            Shift shift = shiftRepo.save(new Shift());
            userRepo.save(new ShiftValidator().assignShift(user, shift));
            return ShiftDto.of(shift);
        } catch (EntityNotFoundException e) {
            throw new ApiException(INVALID_ID, e, CLASS);
        }
    }

    public ShiftBreakDto endShift(long shiftId) {
        try {
            Shift shift = shiftRepo.getById(shiftId);
            shift.endShift();
            return ShiftBreakDto.of(shiftRepo.save(shift));
        } catch (EntityNotFoundException e) {
            throw new ApiException(INVALID_ID, e, CLASS);
        }
    }

    public ShiftBreakDto startLunch(long shiftId) {
        try {
            Lunch lunch = lunchRepo.getById(shiftId);
            lunch.startBreak();
            return ShiftBreakDto.of(lunchRepo.save(lunch).getShift());
        } catch (EntityNotFoundException e) {
            throw new ApiException(INVALID_ID, e, CLASS);
        }
    }

    public ShiftBreakDto endLunch(long shiftId) {
        try {
            Lunch lunch = lunchRepo.getById(shiftId);
            lunch.endBreak();
            return ShiftBreakDto.of(lunchRepo.save(lunch).getShift());
        } catch (EntityNotFoundException e) {
            throw  new ApiException(INVALID_ID, e, CLASS);
        }
    }

    public ShiftBreakDto startBreak(long shiftId) {
        try {
            Break br = breakRepo.getById(shiftId);
            br.startBreak();
            return ShiftBreakDto.of(breakRepo.save(br).getShift());
        } catch (EntityNotFoundException e) {
            throw new ApiException(INVALID_ID, e, CLASS);
        }
    }

    public ShiftBreakDto endBreak(long shiftId) {
        try {
            Break br = breakRepo.getById(shiftId);
            br.endBreak();
            return ShiftBreakDto.of(breakRepo.save(br).getShift());
        } catch (EntityNotFoundException e) {
            throw new ApiException(INVALID_ID, e, CLASS);
        }
    }

        public List<ShiftBreakDto> getShiftsByUserDesc(long userId) {
        User user = userRepo.getById(userId);
        List<Shift> shifts = user.getShifts();
        return shifts.stream().filter(Shift::isComplete)
                .map(ShiftBreakDto::of)
                .collect(Collectors.toList());
    }
}
