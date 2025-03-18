package org.moe.fintech.finsight.core.users.application.usecase;

import org.jmolecules.architecture.hexagonal.Application;
import org.moe.fintech.finsight.core.users.application.event.VerificationTokenGeneratedEvent;
import org.moe.fintech.finsight.core.users.application.port.in.GenerateVerificationToken;
import org.moe.fintech.finsight.core.users.domain.EmailVerificationToken;
import org.moe.fintech.finsight.core.users.domain.UserIdentifier;
import org.moe.fintech.finsight.core.users.domain.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Application
@Component
public class GenerateVerificationTokenUseCase implements GenerateVerificationToken {

	private final ApplicationEventPublisher eventPublisher;
	private final PasswordEncoder passwordEncoder;

	public GenerateVerificationTokenUseCase(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@Override
	@Transactional
	public void execute(UserIdentifier id) {
		String encodedToken = passwordEncoder.encode(id.value().toString());
		EmailVerificationToken token = EmailVerificationToken.from(encodedToken);
		eventPublisher.publishEvent(new VerificationTokenGeneratedEvent(id, token));
	}
}
