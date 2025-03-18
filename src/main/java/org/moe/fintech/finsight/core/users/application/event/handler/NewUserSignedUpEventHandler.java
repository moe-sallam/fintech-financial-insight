package org.moe.fintech.finsight.core.users.application.event.handler;

import org.moe.fintech.finsight.core.users.application.event.NewUserSignedUpEvent;
import org.moe.fintech.finsight.core.users.application.port.in.GenerateVerificationToken;
import org.moe.fintech.finsight.core.users.application.port.out.NewUserSignedUp;
import org.moe.fintech.finsight.core.users.domain.User;
import org.moe.fintech.finsight.core.users.domain.UserAlreadySignedUpException;
import org.moe.fintech.finsight.core.users.domain.UserRepository;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class NewUserSignedUpEventHandler implements NewUserSignedUp {

	private final UserRepository repository;
	private final GenerateVerificationToken generateVerificationToken;

	public NewUserSignedUpEventHandler(UserRepository repository, GenerateVerificationToken generateVerificationToken) {
		this.repository = repository;
		this.generateVerificationToken = generateVerificationToken;
	}

	@Override
	@ApplicationModuleListener
	public void on(NewUserSignedUpEvent event) {
		if (repository.findByEmailAddress(event.emailAddress()).isPresent()) {
			throw new UserAlreadySignedUpException(event.id(), event.emailAddress());
		}
		User user = User.instance(event.id());
		user.signup(event.emailAddress());
		repository.save(user);
		generateVerificationToken.execute(user.getId());
	}
}
