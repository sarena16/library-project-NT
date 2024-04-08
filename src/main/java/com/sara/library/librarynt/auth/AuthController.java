package com.sara.library.librarynt.auth;

import com.sara.library.librarynt.user.userDto.CreateUserDto;
import com.sara.library.librarynt.user.userDto.CreateUserLoginDto;
import com.sara.library.librarynt.user.userDto.CreateUserResponseDto;
import com.sara.library.librarynt.loan.loanDto.LoginResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@PreAuthorize("hasRole('ADMIN')")

public class AuthController {

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<CreateUserResponseDto> register(@Valid @RequestBody CreateUserDto requestBody) {
        CreateUserResponseDto dto = authService.register(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<LoginResponseDto> login(@RequestBody CreateUserLoginDto requestBody) {
        LoginResponseDto dto = authService.login(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}



