package org.moe.fintech.finsight.core.users.application.event.handler;

import org.moe.fintech.finsight.core.users.application.event.VerificationTokenGeneratedEvent;
import org.moe.fintech.finsight.core.users.application.port.in.SendVerificationEmail;
import org.moe.fintech.finsight.core.users.application.port.out.VerificationTokenGenerated;
import org.moe.fintech.finsight.core.users.domain.UserRepository;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class VerificationTokenGeneratedEventHandler implements VerificationTokenGenerated {

	private final UserRepository repository;
	private final SendVerificationEmail sendVerificationEmail;

	public VerificationTokenGeneratedEventHandler(UserRepository repository, SendVerificationEmail sendVerificationEmail) {
		this.repository = repository;
		this.sendVerificationEmail = sendVerificationEmail;
	}

	@Override
	@ApplicationModuleListener
	public void on(VerificationTokenGeneratedEvent event) {
		repository.findById(event.id())
				.ifPresent(user -> {
					user.sendVerificationToken(event.token());
					repository.save(user);
					sendVerificationEmail.execute(user.getId(), user.emailAddress(), event.token());
				});
	}
}
