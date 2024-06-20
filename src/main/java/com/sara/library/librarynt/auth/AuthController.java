package com.sara.library.librarynt.auth;

import com.sara.library.librarynt.user.userDto.CreateUserDto;
import com.sara.library.librarynt.user.userDto.CreateUserLoginDto;
import com.sara.library.librarynt.user.userDto.CreateUserResponseDto;
import com.sara.library.librarynt.user.userDto.LoginResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Authorization")
public class AuthController {
    @Autowired
    public AuthController(AuthService authService){
        this.authService= authService;
    }
    private final AuthService authService;

    @PostMapping("/register")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account successfully created"),
            @ApiResponse(responseCode = "401", description = "Something went wrong", content = @Content())
    })
    public ResponseEntity<CreateUserResponseDto> register(@Validated @RequestBody CreateUserDto requestBody){
        CreateUserResponseDto dto = authService.register(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    @SecurityRequirements
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login succeeded"),
            @ApiResponse(responseCode = "401", description = "Login failed", content = @Content())
    })
    public ResponseEntity<LoginResponseDto> login(@Validated @RequestBody CreateUserLoginDto requestBody){
        LoginResponseDto dto= authService.login(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
