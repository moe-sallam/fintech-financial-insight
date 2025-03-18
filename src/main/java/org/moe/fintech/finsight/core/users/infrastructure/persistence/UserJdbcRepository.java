package org.moe.fintech.finsight.core.users.infrastructure.persistence;

import org.moe.fintech.finsight.core.users.domain.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserJdbcRepository extends ListCrudRepository<UserEntity, UUID> {
	Optional<UserEntity> findByEmail(String email);
}
