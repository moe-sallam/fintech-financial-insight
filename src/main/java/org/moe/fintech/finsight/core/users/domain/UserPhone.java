package org.moe.fintech.finsight.core.users.domain;

import org.apache.commons.lang3.StringUtils;
import org.jmolecules.ddd.annotation.ValueObject;
import org.springframework.util.Assert;

import java.text.MessageFormat;

@ValueObject
public record UserPhone(String value) {

	private static final String PHONE_REGEX_PATTERN = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$";
	private static final String BLANK_PHONE_ERROR_MESSAGE = "Phone number cannot be blank";
	private static final String INVALID_PHONE_ERROR_MESSAGE = "Invalid User Phone: ({0})";

	public UserPhone {
		Assert.state(StringUtils.isNotBlank(value), BLANK_PHONE_ERROR_MESSAGE);
		Assert.isTrue(value.matches(PHONE_REGEX_PATTERN), MessageFormat.format(INVALID_PHONE_ERROR_MESSAGE, value));
	}

	public static UserPhone of(String value) {
		return new UserPhone(value);
	}
}
