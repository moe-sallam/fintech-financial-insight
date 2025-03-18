package org.moe.fintech.finsight.core.users.domain;

import java.io.Serial;
import java.io.Serializable;
import java.text.MessageFormat;

public class UserNotFoundException extends RuntimeException implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(UserIdentifier id) {
		super(MessageFormat.format("No user with ID {0} was found", id.value().toString()));
	}
}
