package com.helmes.assignment.server.services;

import com.helmes.assignment.api.model.User;
import com.helmes.assignment.server.exception.AssignmentException;
import com.helmes.assignment.server.model.UserEntity;
import com.helmes.assignment.server.repository.UsersRepository;
import com.helmes.assignment.server.services.helper.UserDtoHelper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

	UsersRepository usersRepository;

	public UsersServiceImpl(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	@Override
	public User getUser(String name) throws AssignmentException {
		Optional<UserEntity> userEntity = usersRepository.findByName(name);
		if (!userEntity.isPresent()) {
			throw new AssignmentException("Couldn' find user with given name");
		}
		return UserDtoHelper.toDTO(userEntity.get());
	}

	@Override
	public User createUser(User user) throws AssignmentException {
		Optional<UserEntity> loadedUserEntity = usersRepository.findByName(user.getName());
		if (loadedUserEntity.isPresent()) {
			throw new AssignmentException("Username is taken");
		}
		UserEntity userEntity = usersRepository.save(UserDtoHelper.fromDTO(user));
		return UserDtoHelper.toDTO(userEntity);
	}

	@Override
	public User updateUser(User user) throws AssignmentException {
		UserEntity loadedUser = usersRepository.findById(user.getId())
				.orElseThrow(() -> new AssignmentException("Couldn't find USER"));
		return UserDtoHelper.toDTO(loadedUser);
	}

}
