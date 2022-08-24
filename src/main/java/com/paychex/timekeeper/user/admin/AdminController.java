package com.paychex.timekeeper.user.admin;

import com.paychex.timekeeper.shift.model.util.ShiftBreakDto;
import com.paychex.timekeeper.shift.model.util.ShiftDto;
import com.paychex.timekeeper.user.model.User;
import com.paychex.timekeeper.user.model.util.UserDto;
import com.paychex.timekeeper.user.model.util.UserShiftDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.paychex.timekeeper.constant.Constant.UI;

@RestController
@RequestMapping("/ADMIN/api")
@CrossOrigin(UI)
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody User userData) {
        return new ResponseEntity<>(adminService.createUser(userData), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(adminService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/shifts/{userId}")
    public ResponseEntity<ShiftDto> startShift(@PathVariable long userId) {
        return new ResponseEntity<>(adminService.startShift(userId), HttpStatus.OK);
    }

    @GetMapping("/shifts/{shiftId}/end")
    public ResponseEntity<ShiftBreakDto> endShift(@PathVariable long shiftId) {
        return new ResponseEntity<>(adminService.endShift(shiftId), HttpStatus.OK);
    }

    @GetMapping("/lunches/{shiftId}")
    public ResponseEntity<ShiftBreakDto> startLunch(@PathVariable long shiftId) {
        return new ResponseEntity<>(adminService.startLunch(shiftId), HttpStatus.OK);
    }

    @GetMapping("/lunches/{shiftId}/end")
    public ResponseEntity<ShiftBreakDto> endLunch(@PathVariable long shiftId) {
        return new ResponseEntity<>(adminService.endLunch(shiftId), HttpStatus.OK);
    }

    @GetMapping("/breaks/{shiftId}")
    public ResponseEntity<ShiftBreakDto> startBreak(@PathVariable long shiftId) {
        return new ResponseEntity<>(adminService.startBreak(shiftId), HttpStatus.OK);
    }

    @GetMapping("/breaks/{shiftId}/end")
    public ResponseEntity<ShiftBreakDto> endBreak(@PathVariable long shiftId) {
        return new ResponseEntity<>(adminService.endBreak(shiftId), HttpStatus.OK);
    }

    @GetMapping("/shifts/{userId)/desc")
    public ResponseEntity<List<ShiftBreakDto>> getShiftsByUserDesc(@PathVariable long userId) {
        return new ResponseEntity<>(adminService.getShiftsByUserDesc(userId), HttpStatus.OK);
    }
}
