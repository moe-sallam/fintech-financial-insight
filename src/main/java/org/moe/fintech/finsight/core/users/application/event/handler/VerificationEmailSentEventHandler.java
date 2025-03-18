package org.moe.fintech.finsight.core.users.application.event.handler;

import org.moe.fintech.finsight.core.users.application.event.VerificationEmailSentEvent;
import org.moe.fintech.finsight.core.users.application.port.out.VerificationEmailSent;
import org.moe.fintech.finsight.core.users.domain.UserRepository;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class VerificationEmailSentEventHandler implements VerificationEmailSent {

	private final UserRepository repository;

	public VerificationEmailSentEventHandler(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	@ApplicationModuleListener
	public void on(VerificationEmailSentEvent event) {
		repository.findById(event.id())
				.ifPresent(user -> {
					user.sendEmail();
					repository.save(user);
				});
	}
}
