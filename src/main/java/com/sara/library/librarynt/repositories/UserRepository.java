package com.sara.library.librarynt.repositories;

import com.sara.library.librarynt.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  // Optional<UserEntity> findByUsername(String username);
}
