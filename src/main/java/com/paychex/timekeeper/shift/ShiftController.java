package com.paychex.timekeeper.shift;

import com.paychex.timekeeper.shift.model.util.ShiftBreakDto;
import com.paychex.timekeeper.shift.model.util.ShiftDto;
import com.paychex.timekeeper.shift.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shifts")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;


    @PostMapping("/start/{userId}")
    public ResponseEntity<Object> startShift(@PathVariable long userId) {
        return new ResponseEntity<>(shiftService.startShift(userId), HttpStatus.OK);
    }

    @GetMapping("/{shiftId}")
    public ResponseEntity<ShiftBreakDto> getShift(@PathVariable long shiftId) {
        return new ResponseEntity<>(shiftService.getShift(shiftId), HttpStatus.OK);
    }

    @PutMapping("/{shiftId}")
    public ResponseEntity<ShiftBreakDto> endShift(@PathVariable long shiftId) {
        return new ResponseEntity<>(shiftService.endShift(shiftId), HttpStatus.OK);
    }
}
