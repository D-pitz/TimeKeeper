package com.paychex.timekeeper.shift.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShiftRepo extends JpaRepository<Shift, Long> {

}
