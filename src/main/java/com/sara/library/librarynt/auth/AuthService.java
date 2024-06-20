package com.sara.library.librarynt.auth;

import com.sara.library.librarynt.service.JwtService;
import com.sara.library.librarynt.user.userDto.CreateUserDto;
import com.sara.library.librarynt.user.userDto.CreateUserLoginDto;
import com.sara.library.librarynt.user.userDto.CreateUserResponseDto;
import com.sara.library.librarynt.user.userDto.LoginResponseDto;
import com.sara.library.librarynt.user.UserEntity;
import com.sara.library.librarynt.repositories.AuthRepository;
import com.sara.library.librarynt.repositories.UserRepository;
import com.sara.library.librarynt.errors.UserAlreadyExists;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Service
public class AuthService {

    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthRepository authRepository, UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }


    public CreateUserResponseDto register(CreateUserDto dto){
        Optional<AuthEntity> existingAuth = authRepository.findByUsername(dto.getUsername());
        if (existingAuth.isPresent()){
            throw UserAlreadyExists.create(dto.getUsername());
        }

        UserEntity userEntity = createUserEntity(dto.getEmail());

        AuthEntity authEntity = createAuthEntity(dto, userEntity);

        return new CreateUserResponseDto(userEntity.getId(), authEntity.getUsername(), authEntity.getRole());
    }

    private UserEntity createUserEntity(String email) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userRepository.save(userEntity);
        return userEntity;
    }

    private AuthEntity createAuthEntity(CreateUserDto dto, UserEntity userEntity) {
        AuthEntity authEntity = new AuthEntity();
        authEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        authEntity.setUsername(dto.getUsername());
        authEntity.setRole(dto.getRole());
        authEntity.setUser(userEntity);
        authRepository.save(authEntity);
        return authEntity;
    }

    public LoginResponseDto login(CreateUserLoginDto dto){
        AuthEntity authEntity = authRepository.findByUsername(dto.getUsername()).orElseThrow(RuntimeException::new);
        if (!passwordEncoder.matches(dto.getPassword(), authEntity.getPassword())){
            throw new RuntimeException();
        }
        String token = jwtService.generateToken(authEntity);
        return new LoginResponseDto(token);
    }
}
