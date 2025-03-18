package org.moe.fintech.finsight.core.users.application.usecase;

import org.moe.fintech.finsight.core.users.application.event.VerificationTokenVerifiedEvent;
import org.moe.fintech.finsight.core.users.application.port.in.VerifyToken;
import org.moe.fintech.finsight.core.users.domain.UserIdentifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class VerifyTokenUseCase implements VerifyToken {

	private final ApplicationEventPublisher eventPublisher;

	public VerifyTokenUseCase(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	@Override
	@Transactional
	public void execute(UserIdentifier id, String token) {
		eventPublisher.publishEvent(new VerificationTokenVerifiedEvent(id, token));
	}
}
