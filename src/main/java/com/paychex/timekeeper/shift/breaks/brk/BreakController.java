package com.paychex.timekeeper.shift.breaks.brk;

import com.paychex.timekeeper.shift.model.util.ShiftBreakDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.paychex.timekeeper.constant.Constant.UI;

@RestController
@RequestMapping("/api/breaks")
@CrossOrigin(UI)
public class BreakController {

    @Autowired
    private BreakService breakService;

    @PutMapping("/{shiftId}")
    public ResponseEntity<ShiftBreakDto> startBreak(@PathVariable long shiftId) {
        return new ResponseEntity<>(breakService.startBreak(shiftId), HttpStatus.OK);
    }

    @PutMapping("/{shiftId}/end")
    public ResponseEntity<ShiftBreakDto> endBreak(@PathVariable long shiftId) {
        return new ResponseEntity<>(breakService.endBreak(shiftId), HttpStatus.OK);
    }
}
