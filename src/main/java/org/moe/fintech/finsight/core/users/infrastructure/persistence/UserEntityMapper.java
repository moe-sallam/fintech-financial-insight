package org.moe.fintech.finsight.core.users.infrastructure.persistence;

import org.apache.commons.lang3.ObjectUtils;
import org.moe.fintech.finsight.core.users.domain.EmailVerificationToken;
import org.moe.fintech.finsight.core.users.domain.User;
import org.moe.fintech.finsight.core.users.domain.UserEmailAddress;
import org.moe.fintech.finsight.core.users.domain.UserIdentifier;
import org.moe.fintech.finsight.core.users.domain.UserStatus;
import org.springframework.stereotype.Component;

@Component
public record UserEntityMapper() {

	UserEntity map(User user) {
		return new UserEntity(
				user.getId().value(),
				null,
				user.emailAddress().value(),
				user.state().status().name(),
				user.hasToken() ? user.verificationToken().hashedToken(): null,
				user.hasToken() ? user.verificationToken().expiresAt() : null,
				null,
				user.state().occurredAt()
		);
	}

	UserEntity map(User user, UserEntity existingEntity) {
		return new UserEntity(
				user.getId().value(),
				existingEntity.version(),
				user.emailAddress().value(),
				user.state().status().name(),
				user.hasToken() ? user.verificationToken().hashedToken(): null,
				user.hasToken() ? user.verificationToken().expiresAt() : null,
				existingEntity.createdAt(),
				user.state().occurredAt()
		);
	}

	User map(UserEntity entity) {
		return User.from(
				UserIdentifier.of(entity.id()),
				UserEmailAddress.of(entity.email()),
				entity.activationCode(),
				entity.activationCodeExpiresAt(),
				UserStatus.valueOf(entity.status()),
				entity.updatedAt()
		);
	}
}
