package com.helmes.assignment.server.repository;

import com.helmes.assignment.server.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UserEntity, Long> {

}
