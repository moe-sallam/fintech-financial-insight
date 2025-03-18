package org.moe.fintech.finsight.core.users.application.event.handler;

import org.moe.fintech.finsight.core.users.application.event.VerificationEmailVerifiedEvent;
import org.moe.fintech.finsight.core.users.application.event.VerificationTokenVerifiedEvent;
import org.moe.fintech.finsight.core.users.application.port.out.EmailNotificationPort;
import org.moe.fintech.finsight.core.users.application.port.out.VerificationEmailVerified;
import org.moe.fintech.finsight.core.users.application.port.out.VerificationTokenVerified;
import org.moe.fintech.finsight.core.users.domain.UserRepository;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class VerificationEmailVerifiedEventHandler implements VerificationEmailVerified {

	private final UserRepository repository;
	private final EmailNotificationPort emailNotificationPort;

	public VerificationEmailVerifiedEventHandler(UserRepository repository, EmailNotificationPort emailNotificationPort) {
		this.repository = repository;
		this.emailNotificationPort = emailNotificationPort;
	}

	@Override
	@ApplicationModuleListener
	public void on(VerificationEmailVerifiedEvent event) {
		repository.findById(event.id())
				.ifPresent(user -> {
					user.readyToActivate();
					emailNotificationPort.sendWelcomeEmail(user.getId(), user.emailAddress());
					repository.save(user);
				});
	}
}
