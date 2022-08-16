package com.paychex.timekeeper.shift.breaks.lunch;

import com.paychex.timekeeper.shift.model.util.ShiftBreakDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.paychex.timekeeper.constant.Constant.UI;

@RestController
@RequestMapping("/api/lunches")
@CrossOrigin(UI)
public class LunchController {

    @Autowired
    private LunchService lunchService;

    @PutMapping("/{shiftId}")
    public ResponseEntity<ShiftBreakDto> startLunch(@PathVariable long shiftId) {
        return new ResponseEntity<>(lunchService.startLunch(shiftId), HttpStatus.OK);
    }

    @PutMapping("/{shiftId}/end")
    public ResponseEntity<ShiftBreakDto> endLunch(@PathVariable long shiftId) {
        return new ResponseEntity<>(lunchService.endLunch(shiftId), HttpStatus.OK);
    }
}
