package com.paychex.timekeeper.filter;

import com.paychex.timekeeper.exception.ApiException;
import com.paychex.timekeeper.filter.dtos.Analyze;
import com.paychex.timekeeper.filter.dtos.ShiftInfo;
import com.paychex.timekeeper.shift.breaks.BreakDto;
import com.paychex.timekeeper.shift.breaks.lunch.Lunch;
import com.paychex.timekeeper.shift.model.Shift;
import com.paychex.timekeeper.shift.model.ShiftRepo;
import com.paychex.timekeeper.user.model.User;
import com.paychex.timekeeper.user.model.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static com.paychex.timekeeper.constant.Messages.INVALID_ID;

@Service
public class DataService {

    private final Logger LOG = LoggerFactory.getLogger(DataService.class);
    private final String CLASS = this.getClass().getSimpleName();

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ShiftRepo shiftRepo;

    public List<Shift> getAllShiftsByUser(long userId) {
        try {
            User user = userRepo.getById(userId);
            return user.getShifts();
        } catch (EntityNotFoundException e) {
            throw new ApiException(INVALID_ID, e, CLASS);
        }
    }

    public ShiftInfo getShiftInfoByUser(long userId) {
        List<Shift> shifts = getAllShiftsByUser(userId);
        return null;
    }

    public List<BreakDto> shiftData(long userId) {
        User user = userRepo.getById(userId);
        List<Lunch> lunches = shiftRepo.findAllLunchByUserId(user);
        double avg = new Analyze().getAverageLunch(lunches);
        LOG.info(String.valueOf(avg));
        return lunches.stream().map(BreakDto::of).collect(Collectors.toList());
    }
}
