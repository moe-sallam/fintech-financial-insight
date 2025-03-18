package org.moe.fintech.finsight.core.users.application.usecase;

import org.jmolecules.architecture.hexagonal.Application;
import org.moe.fintech.finsight.core.users.application.event.VerificationEmailSentEvent;
import org.moe.fintech.finsight.core.users.application.port.in.SendVerificationEmail;
import org.moe.fintech.finsight.core.users.application.port.out.EmailNotificationPort;
import org.moe.fintech.finsight.core.users.domain.EmailVerificationToken;
import org.moe.fintech.finsight.core.users.domain.UserEmailAddress;
import org.moe.fintech.finsight.core.users.domain.UserIdentifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Application
@Component
class SendVerificationEmailUseCase implements SendVerificationEmail {

	private final EmailNotificationPort emailNotificationPort;
	private final ApplicationEventPublisher eventPublisher;

	public SendVerificationEmailUseCase(EmailNotificationPort emailNotificationPort, ApplicationEventPublisher eventPublisher) {
		this.emailNotificationPort = emailNotificationPort;
		this.eventPublisher = eventPublisher;
	}

	@Override
	@Transactional
	public void execute(UserIdentifier id, UserEmailAddress emailAddress, EmailVerificationToken token) {
		emailNotificationPort.sendVerificationToken(id, emailAddress, token);
		eventPublisher.publishEvent(new VerificationEmailSentEvent(id));
	}
}
