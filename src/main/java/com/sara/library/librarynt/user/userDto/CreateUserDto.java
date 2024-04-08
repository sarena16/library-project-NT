package com.sara.library.librarynt.user.userDto;

import com.sara.library.librarynt.commonTypes.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateUserDto {
    @NotBlank(message = "User name is needed")
    private String username;
    @NotBlank(message = "Password is needed")
    private String password;
    @NotNull
    private UserRole role;
    @NotBlank(message = "Email is needed")
    @Email
    private String email;


    public CreateUserDto(String username, String password, UserRole role, String email) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}