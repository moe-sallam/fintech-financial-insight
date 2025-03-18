package org.moe.fintech.finsight.core.users.application.port.out;

import org.jmolecules.architecture.hexagonal.SecondaryPort;
import org.moe.fintech.finsight.core.users.application.event.VerificationEmailVerifiedEvent;

@SecondaryPort
public interface VerificationEmailVerified {

	void on(VerificationEmailVerifiedEvent event);
}
