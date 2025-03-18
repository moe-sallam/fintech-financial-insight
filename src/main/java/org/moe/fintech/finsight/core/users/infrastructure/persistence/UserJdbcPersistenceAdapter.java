package org.moe.fintech.finsight.core.users.infrastructure.persistence;

import org.moe.fintech.finsight.core.users.domain.User;
import org.moe.fintech.finsight.core.users.domain.UserEmailAddress;
import org.moe.fintech.finsight.core.users.domain.UserIdentifier;
import org.moe.fintech.finsight.core.users.domain.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserJdbcPersistenceAdapter implements UserRepository {

	private final UserJdbcRepository repository;
	private final UserEntityMapper mapper;

	public UserJdbcPersistenceAdapter(UserJdbcRepository repository, UserEntityMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public Optional<User> findByEmailAddress(UserEmailAddress email) {
		return repository.findByEmail(email.value()).map(mapper::map);
	}

	@Override
	public void save(User user) {
		repository.findById(user.getId().value())
				.ifPresentOrElse(userEntity -> {
					repository.save(mapper.map(user, userEntity));
				}, () -> {
					repository.save(mapper.map(user));
				});
	}

	@Override
	public Optional<User> findById(UserIdentifier userIdentifier) {
		return repository.findById(userIdentifier.value()).map(mapper::map);
	}
}
