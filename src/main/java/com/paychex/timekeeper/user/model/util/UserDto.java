package com.paychex.timekeeper.user.model.util;

import com.paychex.timekeeper.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private long id;
    private String role;

    public static UserDto of (User user) {
        return UserDto.builder()
                .id(user.getId())
                .role(user.getRole())
                .build();
    }
}
