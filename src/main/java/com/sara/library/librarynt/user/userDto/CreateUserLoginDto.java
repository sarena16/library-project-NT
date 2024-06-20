package com.sara.library.librarynt.user.userDto;

import com.sara.library.librarynt.commonTypes.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class CreateUserLoginDto {

    @NotBlank(message = "Username is required")
    @Schema(name= "username", example = "username")
    private String username;

    @NotBlank(message = "Password is required")
    @Schema(name = "password", example = "password")
    private String password;

    public CreateUserLoginDto(String username, String password) {
        this.username = username;
        this.password = password;
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
//    private String password;
//    private String username;
//    private UserRole role;
//    private String email;
//
//    public CreateUserLoginDto(String password, String username, UserRole role, String email) {
//        this.password = password;
//        this.username = username;
//        this.role = role;
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public UserRole getRole() {
//        return role;
//    }
//
//    public void setRole(UserRole role) {
//        this.role = role;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
}
