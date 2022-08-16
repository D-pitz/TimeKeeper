package com.paychex.timekeeper.shift.breaks.lunch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LunchRepo extends JpaRepository<Lunch, Long> {
}
