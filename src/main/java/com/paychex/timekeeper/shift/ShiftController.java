package com.paychex.timekeeper.shift;

import com.paychex.timekeeper.shift.model.util.ShiftBreakDto;
import com.paychex.timekeeper.shift.model.util.ShiftDto;
import com.paychex.timekeeper.shift.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.paychex.timekeeper.constant.Constant.UI;

@RestController
@RequestMapping("/api/shifts")
@CrossOrigin(UI)
public class ShiftController {

    @Autowired
    private ShiftService shiftService;


    @GetMapping("/start/{userId}")
    public ResponseEntity<Object> startShift(@PathVariable long userId) {
        return new ResponseEntity<>(shiftService.startShift(userId), HttpStatus.OK);
    }

    @GetMapping("/{shiftId}")
    public ResponseEntity<ShiftBreakDto> getShift(@PathVariable long shiftId) {
        return new ResponseEntity<>(shiftService.getShift(shiftId), HttpStatus.OK);
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<ShiftBreakDto>> getShiftsByUser(@PathVariable long userId) {
        return new ResponseEntity<>(shiftService.getShiftsByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/active/{userId}")
    public ResponseEntity<ShiftBreakDto> getActiveShiftsByUser(@PathVariable long userId) {
        return new ResponseEntity<>(shiftService.getActiveShift(userId), HttpStatus.OK);
    }

    @GetMapping("/{shiftId}/end")
    public ResponseEntity<ShiftBreakDto> endShift(@PathVariable long shiftId) {
        return new ResponseEntity<>(shiftService.endShift(shiftId), HttpStatus.OK);
    }
}
