package com.paychex.timekeeper.user.service;

import com.paychex.timekeeper.exception.ApiException;
import com.paychex.timekeeper.messages.ErrorMessage;
import com.paychex.timekeeper.user.model.User;
import com.paychex.timekeeper.user.model.util.Role;
import static com.paychex.timekeeper.constant.Messages.INVALID_ROLE;

public class Validator {

    private final String CLASS = this.getClass().getSimpleName();

    public void validRole(User userData) {
        if (!Role.ADMIN.validRole(userData.getRole()))
            throw new ErrorMessage(INVALID_ROLE);
    }

    public boolean isAdmin(User user) {
        return user.getRole().equals("ADMIN");
    }
}
