package org.moe.fintech.finsight.core.users.application.port.in;

import org.jmolecules.architecture.hexagonal.PrimaryPort;
import org.moe.fintech.finsight.core.users.domain.EmailVerificationToken;
import org.moe.fintech.finsight.core.users.domain.UserEmailAddress;
import org.moe.fintech.finsight.core.users.domain.UserIdentifier;

@PrimaryPort
public interface SendVerificationEmail {

	void execute(UserIdentifier id, UserEmailAddress emailAddress, EmailVerificationToken token);
}
