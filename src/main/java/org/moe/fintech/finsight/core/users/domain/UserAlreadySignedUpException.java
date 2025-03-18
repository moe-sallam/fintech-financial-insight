package org.moe.fintech.finsight.core.users.domain;

import java.io.Serial;
import java.io.Serializable;
import java.text.MessageFormat;

public class UserAlreadySignedUpException extends RuntimeException implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	public UserAlreadySignedUpException(UserIdentifier id, UserEmailAddress email) {
		super(MessageFormat.format("There already exists a user with ID {0} and email {1}", id, email));
	}
}
