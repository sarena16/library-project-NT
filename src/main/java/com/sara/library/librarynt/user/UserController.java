package com.sara.library.librarynt.user;

import com.sara.library.librarynt.user.userDto.GetUserDto;
import com.sara.library.librarynt.user.userDto.PatchUserDto;
import com.sara.library.librarynt.user.userDto.PatchUserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("isAuthenticated()")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<GetUserDto> getMe(Principal principal) {
        String username = principal.getName();
        GetUserDto userDto = userService.getUserByUsername(username);
        return new ResponseEntity<>(userDto, HttpStatus.OK);

    }
    @PatchMapping("/{id}")
    public ResponseEntity<PatchUserResponseDto> update(@PathVariable long id, @RequestBody PatchUserDto dto){
        PatchUserResponseDto responseDto = userService.update(id, dto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

}

//   private final UserService userService;
//
//    @Autowired
//   public UserController(UserService userService) {
//       this.userService = userService;
//    }
//    @GetMapping
//    public List<GetUserDto> getAllUsers(){
//        return userService.getAll();
//    }
//
//    @GetMapping("/{id}")
//    public GetUserDto getOne(@PathVariable long id){
//        return userService.getOne(id);
//    }
//
//    @PostMapping
//    public ResponseEntity<CreateUserResponseDto> create(@RequestBody CreateUserDto user) {
//        var newUser =  userService.create(user);
//        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable long id) {
//        userService.delete(id);
//        return ResponseEntity.noContent().build();
//    }

