package com.sara.library.librarynt.user.userDto;

import com.sara.library.librarynt.commonTypes.UserRole;

public class CreateUserResponseDto {

    private long userId;
    private String username;
    private UserRole role;

    public CreateUserResponseDto(long userId, String username, UserRole role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
