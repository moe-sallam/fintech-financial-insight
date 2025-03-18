package org.moe.fintech.finsight.core.users.application.usecase;

import org.jmolecules.architecture.hexagonal.Application;
import org.moe.fintech.finsight.core.users.application.event.VerificationEmailVerifiedEvent;
import org.moe.fintech.finsight.core.users.application.port.in.Register;
import org.moe.fintech.finsight.core.users.application.port.out.EmailNotificationPort;
import org.moe.fintech.finsight.core.users.domain.UserEmailAddress;
import org.moe.fintech.finsight.core.users.domain.UserIdentifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Application
@Component
public class RegisterUseCase implements Register {

	private final ApplicationEventPublisher eventPublisher;

	public RegisterUseCase(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	@Override
	@Transactional
	public void execute(UserIdentifier id, UserEmailAddress emailAddress) {
		eventPublisher.publishEvent(new VerificationEmailVerifiedEvent(id));
	}
}
