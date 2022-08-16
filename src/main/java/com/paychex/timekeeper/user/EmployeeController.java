package com.paychex.timekeeper.user;

import com.paychex.timekeeper.messages.Message;
import com.paychex.timekeeper.user.model.User;
import com.paychex.timekeeper.user.model.util.UserDto;
import com.paychex.timekeeper.user.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody User userData) {
        return new ResponseEntity<>(employeeService.createUser(userData), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody User userData) {
        return new ResponseEntity<>(employeeService.login(userData), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id) {
        return new ResponseEntity<>(employeeService.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteUser(@PathVariable long id) {
        return new ResponseEntity<>(employeeService.deleteUserById(id), HttpStatus.OK);
    }
}
