package com.helmes.assignment.server.exception;

import com.sun.tools.jdeprscan.Messages;
import lombok.Getter;

@Getter
public class AssignmentException extends Exception {

	private final String code;

	public AssignmentException(String code) {
		super(Messages.get(code));
		this.code = code;
	}

}
