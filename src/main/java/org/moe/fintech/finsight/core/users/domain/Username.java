package org.moe.fintech.finsight.core.users.domain;

import org.apache.commons.lang3.StringUtils;
import org.jmolecules.ddd.annotation.ValueObject;
import org.springframework.util.Assert;

import java.text.MessageFormat;

@ValueObject
public record Username(String value) {

	private static final String BLANK_USERNAME_ERROR_MESSAGE = "Username cannot be blank";
	private static final String INVALID_USERNAME_ERROR_MESSAGE = "Username must have at least 5 alphanumeric characters: ({0})";

	public Username {
		Assert.state(StringUtils.isNotBlank(value), BLANK_USERNAME_ERROR_MESSAGE);
		Assert.state(value.length() >= 5, MessageFormat.format(INVALID_USERNAME_ERROR_MESSAGE, value));
	}

	public static Username of(String value) {
		return new Username(value);
	}
}
