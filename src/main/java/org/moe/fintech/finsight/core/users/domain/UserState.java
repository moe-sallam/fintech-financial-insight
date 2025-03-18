package org.moe.fintech.finsight.core.users.domain;

import org.jmolecules.ddd.annotation.ValueObject;

import java.time.Instant;

@ValueObject
public record UserState(UserStatus status, Instant occurredAt) {

	public static final UserState CREATED = new UserState(UserStatus.CREATED, Instant.now());
	public static final UserState EMAIL_VERIFICATION_PENDING = new UserState(UserStatus.EMAIL_VERIFICATION_PENDING, Instant.now());
	public static final UserState EMAIL_VERIFICATION_TOKEN_CREATED = new UserState(UserStatus.EMAIL_VERIFICATION_TOKEN_CREATED, Instant.now());
	public static final UserState EMAIL_VERIFIED = new UserState(UserStatus.EMAIL_VERIFIED, Instant.now());
	public static final UserState ACTIVATION_PENDING = new UserState(UserStatus.ACTIVATION_PENDING, Instant.now());
	public static final UserState ACTIVATED = new UserState(UserStatus.ACTIVATED, Instant.now());
	public static final UserState INVALIDATED = new UserState(UserStatus.INVALIDATED, Instant.now());
}
