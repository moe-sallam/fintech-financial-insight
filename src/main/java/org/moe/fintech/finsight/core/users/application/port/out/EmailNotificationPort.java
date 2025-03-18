package org.moe.fintech.finsight.core.users.application.port.out;

import org.jmolecules.architecture.hexagonal.SecondaryPort;
import org.moe.fintech.finsight.core.users.domain.EmailVerificationToken;
import org.moe.fintech.finsight.core.users.domain.UserEmailAddress;
import org.moe.fintech.finsight.core.users.domain.UserIdentifier;

@SecondaryPort
public interface EmailNotificationPort {

	void sendVerificationToken(UserIdentifier id, UserEmailAddress emailAddress, EmailVerificationToken token);
	void sendWelcomeEmail(UserIdentifier id, UserEmailAddress emailAddress);
	void sendProducts(UserIdentifier id, UserEmailAddress emailAddress);
}
