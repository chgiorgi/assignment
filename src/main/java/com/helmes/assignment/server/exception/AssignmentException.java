package com.helmes.assignment.server.exception;

import lombok.Getter;

@Getter
public class AssignmentException extends Exception {

	private final String code;

	public AssignmentException(String code) {
		this.code = code;
	}

}
