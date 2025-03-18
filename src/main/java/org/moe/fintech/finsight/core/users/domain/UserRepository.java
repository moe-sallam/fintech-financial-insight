package org.moe.fintech.finsight.core.users.domain;

import org.jmolecules.ddd.integration.AssociationResolver;
import org.jmolecules.ddd.types.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User, UserIdentifier>, AssociationResolver<User, UserIdentifier> {
	Optional<User> findByEmailAddress(UserEmailAddress email);
	void save(User user);
}
