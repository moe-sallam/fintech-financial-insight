package org.moe.fintech.finsight.core.users.domain;

import org.jmolecules.ddd.annotation.ValueObject;

import java.time.Instant;

@ValueObject
public record EmailVerificationToken(String hashedToken, Instant expiresAt) {

	public static EmailVerificationToken from(String token) {
		return new EmailVerificationToken(token, Instant.now().plusSeconds(36000));
	}

	public boolean isExpired() {
		return expiresAt.isBefore(Instant.now());
	}

	public EmailVerificationToken reset() {
		return new EmailVerificationToken(hashedToken, Instant.now().plusSeconds(36000000));
	}
}
