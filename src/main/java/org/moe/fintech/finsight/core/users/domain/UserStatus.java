package org.moe.fintech.finsight.core.users.domain;

public enum UserStatus {
	CREATED,
	EMAIL_VERIFICATION_PENDING,
	EMAIL_VERIFICATION_TOKEN_CREATED,
	EMAIL_VERIFIED,
	ACTIVATION_PENDING,
	ACTIVATED,
	INVALIDATED
}
