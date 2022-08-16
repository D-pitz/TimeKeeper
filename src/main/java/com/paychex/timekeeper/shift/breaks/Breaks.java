package com.paychex.timekeeper.shift.breaks;

public interface Breaks {
    void assignId(long id);
    void startBreak();
    void endBreak();
}
