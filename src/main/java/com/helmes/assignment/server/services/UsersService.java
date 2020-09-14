package com.helmes.assignment.server.services;

import com.helmes.assignment.api.model.User;
import com.helmes.assignment.server.exception.AssignmentException;

public interface UsersService {

	public User getUser(String name) throws AssignmentException;

	public User createUser(User user) throws AssignmentException;

	User updateUser(User user) throws AssignmentException;

}
