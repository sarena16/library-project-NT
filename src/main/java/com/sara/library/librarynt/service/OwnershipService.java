package com.sara.library.librarynt.service;

import com.sara.library.librarynt.auth.AuthEntity;
import com.sara.library.librarynt.errors.UserNotFound;
import com.sara.library.librarynt.repositories.AuthRepository;


public abstract class OwnershipService {
    protected final AuthRepository authRepository;

    public OwnershipService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public boolean isOwner (String username, Long userId){
        if(userId == null || username == null){
            return false;
        }
        AuthEntity authEntity = authRepository.findByUsername(username).orElseThrow(()-> UserNotFound.createWithUsername(username));

        return (userId == authEntity.getUser().getId());
    }
}
