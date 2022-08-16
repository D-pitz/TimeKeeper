package com.paychex.timekeeper.shift.breaks.brk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreakRepo extends JpaRepository<Break, Long> {
}
