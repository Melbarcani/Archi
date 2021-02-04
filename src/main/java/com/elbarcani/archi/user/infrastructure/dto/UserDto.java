package com.elbarcani.archi.user.infrastructure.dto;

import com.elbarcani.archi.user.domain.User;

public interface UserDto {

    User getUserDto();
    boolean isUserExist();
}
