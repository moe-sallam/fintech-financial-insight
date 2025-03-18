package org.moe.fintech.finsight.core.users.application.usecase;

import org.moe.fintech.finsight.core.users.application.event.VerificationTokenVerifiedEvent;
import org.moe.fintech.finsight.core.users.application.port.in.VerifyToken;
import org.moe.fintech.finsight.core.users.domain.EmailVerificationToken;
import org.moe.fintech.finsight.core.users.domain.UserIdentifier;
import org.moe.fintech.finsight.core.users.domain.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class VerifyTokenUseCase implements VerifyToken {

	private final PasswordEncoder encoder;
	private final UserRepository repository;
	private final ApplicationEventPublisher eventPublisher;

	public VerifyTokenUseCase(UserRepository repository, ApplicationEventPublisher eventPublisher) {
		this.repository = repository;
		this.eventPublisher = eventPublisher;
		this.encoder = new BCryptPasswordEncoder();
	}

	@Override
	@Transactional
	public void execute(UserIdentifier id, String token) {
		eventPublisher.publishEvent(new VerificationTokenVerifiedEvent(id, token));
	}
}
