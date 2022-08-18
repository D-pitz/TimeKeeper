package com.paychex.timekeeper.shift.model;

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
            "AND s.complete = :c",
            nativeQuery = true)
    Shift findShiftByUserAndComplete(@Param("userId") Long userId, @Param("c") Boolean complete);

    List<Shift> findAllByUserId(long userId);

    Shift getByUserId(long userId);
}
