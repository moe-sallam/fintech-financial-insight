package org.moe.fintech.finsight.core.users.application.usecase;

import org.jmolecules.architecture.hexagonal.Application;
import org.moe.fintech.finsight.core.users.application.event.NewUserSignedUpEvent;
import org.moe.fintech.finsight.core.users.application.port.in.Signup;
import org.moe.fintech.finsight.core.users.domain.User;
import org.moe.fintech.finsight.core.users.domain.UserEmailAddress;
import org.moe.fintech.finsight.core.users.domain.UserIdentifier;
import org.moe.fintech.finsight.core.users.domain.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Application
@Component
public class SignupUseCase implements Signup {

	private final ApplicationEventPublisher eventPublisher;

	public SignupUseCase(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	@Override
	@Transactional
	public void execute(UserEmailAddress email) {
		UserIdentifier id = UserIdentifier.generate();
		eventPublisher.publishEvent(new NewUserSignedUpEvent(id, email));
	}
}
