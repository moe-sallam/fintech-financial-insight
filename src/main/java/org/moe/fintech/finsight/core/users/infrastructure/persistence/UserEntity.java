package org.moe.fintech.finsight.core.users.infrastructure.persistence;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table("fintech_finsight_users")
public record UserEntity(
		@Id UUID id,
		@Version Long version,
		String email,
		String status,
		String activationCode,
		Instant activationCodeExpiresAt,
		@CreatedDate Instant createdAt,
		Instant updatedAt
		) {
}
