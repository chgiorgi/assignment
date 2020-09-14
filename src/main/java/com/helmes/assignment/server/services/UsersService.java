package com.helmes.assignment.server.services;

import com.helmes.assignment.api.model.User;

import java.util.List;

public interface UsersService {

	public List<User> getAllUsers();

	public User updateUser(User user);

	public User createUser(User user);
}
