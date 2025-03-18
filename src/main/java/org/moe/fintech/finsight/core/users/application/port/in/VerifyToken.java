package org.moe.fintech.finsight.core.users.application.port.in;

import org.jmolecules.architecture.hexagonal.PrimaryPort;
import org.moe.fintech.finsight.core.users.domain.EmailVerificationToken;
import org.moe.fintech.finsight.core.users.domain.UserIdentifier;

@PrimaryPort
public interface VerifyToken {

	void execute(UserIdentifier id, String token);
}
