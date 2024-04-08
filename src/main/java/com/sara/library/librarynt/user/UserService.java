package com.sara.library.librarynt.user;

import com.sara.library.librarynt.auth.AuthEntity;
import com.sara.library.librarynt.repositories.AuthRepository;
import com.sara.library.librarynt.errors.UserNotFound;
import com.sara.library.librarynt.repositories.UserRepository;
import com.sara.library.librarynt.service.OwnershipService;
import com.sara.library.librarynt.user.userDto.GetUserDto;
import com.sara.library.librarynt.user.userDto.PatchUserDto;
import com.sara.library.librarynt.user.userDto.PatchUserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;


@Service
public class UserService extends OwnershipService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(AuthRepository authRepository, UserRepository userRepository) {
        super(authRepository);
        this.userRepository = userRepository;
    }


    public GetUserDto getUserByUsername(String username){
        AuthEntity auth = authRepository.findByUsername(username).orElseThrow(()-> UserNotFound.createWithUsername(username));
        UserEntity user = auth.getUser();

        return new GetUserDto(user.getId(), user.getName(), user.getLastName(), user.getEmail());

    }
    @PreAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name, #id) ")
    public PatchUserResponseDto update(long id, PatchUserDto dto){
        UserEntity user = userRepository.findById(id).orElseThrow(()->UserNotFound.createWithId(id));

        dto.getEmail().ifPresent(user::setEmail);
        dto.getName().ifPresent(user::setName);
        dto.getLastname().ifPresent(user::setLastName);

        userRepository.save(user);

        return new PatchUserResponseDto(user.getId(), user.getName(), user.getLastName(), user.getEmail());
    }

//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public List<GetUserDto> getAll() {
//        var users = userRepository.findAll();
//
//        return users.stream().map((user) -> new GetUserDto(
//                user.getId(),
//                user.getName()
//        )).toList();
//    }
//
//    public GetUserDto getOne(Long id) {
//        var user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
//
//        return new GetUserDto(
//                user.getId(),
//                user.getName()
//        );
//    }
//
//    public CreateUserResponseDto create(CreateUserDto user) {
//        var userEntity = new UserEntity();
//
//        userEntity.setEmail(user.getEmail());
//
//
//        var newUser = userRepository.save(userEntity);
//
//        return new CreateUserResponseDto(
//                newUser.getId(),
//                newUser.getEmail(),null
//               // newUser.getName()          narazie to tak zostawiam ale nie wiem czy to dobrze
//        );
//    }
//
//    public void delete(Long id) {
//        if (!userRepository.existsById(id)) {
//            throw new RuntimeException();
//        }
//        userRepository.deleteById(id);
//    }


}
