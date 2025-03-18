package org.moe.fintech.finsight.core.users.domain;

import org.apache.commons.lang3.StringUtils;
import org.jmolecules.ddd.types.Identifier;
import org.springframework.util.Assert;

import java.util.UUID;

public record UserIdentifier(UUID value) implements Identifier {

	private static final String BLANK_USER_ID_ERROR_MESSAGE = "User ID cannot be blank";

	public UserIdentifier {
		Assert.state(StringUtils.isNotBlank(value.toString()), BLANK_USER_ID_ERROR_MESSAGE);
	}

	public static UserIdentifier of(UUID value) {
		return new UserIdentifier(value);
	}

	public static UserIdentifier generate() {
		return new UserIdentifier(UUID.randomUUID());
	}
}
