package com.paychex.timekeeper.filter.dtos;

import com.paychex.timekeeper.shift.breaks.brk.Break;
import com.paychex.timekeeper.shift.breaks.lunch.Lunch;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

public class Analyze {

    public double getAverageLunch(List<Lunch> lunches) {
        double sum;
        double minutes = 0;
        for (Lunch lunch: lunches) {
        Optional<Integer> notNull = Optional.of(lunch.getStart().getMinute());
            if (notNull.isPresent()) {
                minutes += lunch.getEnd().getMinute() - lunch.getStart().getMinute();
            }
        }
        sum = new BigDecimal(minutes/lunches.size()).setScale(2, RoundingMode.DOWN).doubleValue();
        return sum;
    }

    public double getAverageBreak(List<Break> breaks) {
        double sum = 0;
        double minutes = 0;

        return sum;
    }
}
