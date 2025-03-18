package org.moe.fintech.finsight.core.users.domain;

import org.apache.commons.lang3.StringUtils;
import org.jmolecules.ddd.annotation.ValueObject;
import org.springframework.util.Assert;

import java.text.MessageFormat;

@ValueObject
public record UserEmailAddress(String value) {

	private static final String EMAIL_ADDRESS_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])";
	private static final String BLANK_EMAIL_ADDRESS_ERROR_MESSAGE = "Email address cannot be blank";
	private static final String INVALID_EMAIL_ADDRESS_ERROR_MESSAGE = "Invalid User Email: ({0})";

	public UserEmailAddress {
		Assert.state(StringUtils.isNotBlank(value), BLANK_EMAIL_ADDRESS_ERROR_MESSAGE);
		Assert.isTrue(value.matches(EMAIL_ADDRESS_REGEX), MessageFormat.format(INVALID_EMAIL_ADDRESS_ERROR_MESSAGE, value));
	}

	public static UserEmailAddress of(String value) {
		return new UserEmailAddress(value);
	}
}
