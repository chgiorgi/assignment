package com.helmes.assignment.server.repository;

import com.helmes.assignment.server.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByName(String name);
}
