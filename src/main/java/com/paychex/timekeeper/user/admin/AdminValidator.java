package com.paychex.timekeeper.user.admin;

import com.paychex.timekeeper.exception.ApiException;
import com.paychex.timekeeper.messages.ErrorMessage;
import com.paychex.timekeeper.user.model.User;
import com.paychex.timekeeper.user.model.util.Role;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static com.paychex.timekeeper.constant.Messages.*;

public class AdminValidator {

    private final String CLASS = this.getClass().getSimpleName();

    public void valid(User userData) {
        validAdmin(userData);
        userData.setPassword(validPassword(userData.getPassword()));
    }

    public User verifyLogin(User userData, User user) {
        try {
            validAdmin(user);
            if (!BCrypt.checkpw(userData.getPassword(), user.getPassword()))
                throw new ErrorMessage(WRONG_PW);
            return user;
        } catch (NullPointerException e) {
            throw new ApiException(PW_REQUIRED, e, CLASS);
        }
    }

    public void validAdmin(User userData) {
        Role.isAdmin(userData);
    }

    public String validPassword(final String password) {
        final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&-+=()])(?=\\S+$).{8,20}$";
        final Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        if (matcher.matches()) {
            return BCrypt.hashpw(password, BCrypt.gensalt());
        }
        throw new ApiException(INVALID_PW,
                new PatternSyntaxException("Invalid Password Syntax", PASSWORD_REGEX, 0),
                CLASS + " -> validPassword");
    }
}
