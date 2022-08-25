package com.paychex.timekeeper.filter;

import com.paychex.timekeeper.shift.breaks.BreakDto;
import com.paychex.timekeeper.shift.breaks.lunch.Lunch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<BreakDto>> getLunches(@PathVariable long userId) {
        return new ResponseEntity<>(dataService.shiftData(userId), HttpStatus.OK);
    }
}
