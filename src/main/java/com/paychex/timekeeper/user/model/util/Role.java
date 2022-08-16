package com.paychex.timekeeper.user.model.util;

import com.paychex.timekeeper.messages.ErrorMessage;
import com.paychex.timekeeper.user.model.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static com.paychex.timekeeper.constant.Messages.INVALID_ROLE;
import static com.paychex.timekeeper.constant.Messages.UNAUTHORIZED;

@Getter
public enum Role {
    ADMIN("ADMIN"),
    EMPLOYEE("EMPLOYEE");

    private final String value;
    Role(String v) { value = v; }

    public List<String> roles() {
        List<String> roles = new ArrayList<>();
        roles.add(ADMIN.value);
        roles.add(EMPLOYEE.value);
        return roles;
    }

    public static void isAdmin(User user) {
        if (!user.getRole().equals(ADMIN.value)) {
            throw new ErrorMessage(UNAUTHORIZED);
        }
    }

    public boolean validRole(String role) {
        if (!roles().contains(role)) {
            throw new ErrorMessage(INVALID_ROLE);
        }
        return true;
    }
}
