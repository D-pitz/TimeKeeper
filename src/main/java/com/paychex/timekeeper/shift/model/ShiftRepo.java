package com.paychex.timekeeper.shift.model;

import com.paychex.timekeeper.shift.breaks.lunch.Lunch;
import com.paychex.timekeeper.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftRepo extends JpaRepository<Shift, Long> {

    @Query(value =
            "SELECT * FROM Shift s " +
                    "WHERE s.user_id = :userId " +
                    "AND s.complete = :c " +
                    "ORDER BY s.shift_id DESC",
            nativeQuery = true)
    Shift findShiftByUserAndComplete(@Param("userId") Long userId, @Param("c") Boolean complete);

    List<Shift> findAllShiftsByUserAndCompleteOrderByShiftIdDesc(User user, boolean c);

    @Query("SELECT s.lunch FROM Shift s " +
            "WHERE s.user = :user")
    List<Lunch> findAllLunchByUserId(User user);
}
