package com.paychex.timekeeper.user.service;

import com.paychex.timekeeper.exception.ApiException;
import com.paychex.timekeeper.messages.Message;
import com.paychex.timekeeper.user.admin.AdminService;
import com.paychex.timekeeper.user.model.User;
import com.paychex.timekeeper.user.model.UserRepo;
import com.paychex.timekeeper.user.model.util.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import static com.paychex.timekeeper.constant.Messages.*;

@Service
public class EmployeeService {

    private final String CLASS = this.getClass().getSimpleName();
    private final Logger LOG = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AdminService adminService;

    public UserDto createUser(User userData) {
        new Validator().validRole(userData);
        return UserDto.of(userRepo.save(userData));
    }

    public UserDto getUserById(long id) {
        try {
            return UserDto.of(userRepo.getById(id));
        } catch (EntityNotFoundException e) {
            throw new ApiException(INVALID_ID, e, CLASS);
        }
    }

    public Message deleteUserById(long id) {
        try {
            userRepo.deleteById(id);
            return new Message("User " + id + " " + DELETED);
        } catch (EmptyResultDataAccessException e) {
            throw new ApiException(INVALID_ID, e, CLASS);
        }
    }

    public UserDto login(User userData) {
        try {
            User user = userRepo.getById(userData.getId());
            if (user.getRole().equals("ADMIN"))
                return adminService.login(userData, user);
            return UserDto.of(userRepo.getById(userData.getId()));
        } catch (EntityNotFoundException e) {
            throw new ApiException(INVALID_ID, e, CLASS);
        }
    }
}
