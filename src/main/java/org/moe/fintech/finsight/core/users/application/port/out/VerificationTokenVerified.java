package org.moe.fintech.finsight.core.users.application.port.out;

import org.jmolecules.architecture.hexagonal.SecondaryPort;
import org.moe.fintech.finsight.core.users.application.event.VerificationTokenVerifiedEvent;

@SecondaryPort
public interface VerificationTokenVerified {

	void on(VerificationTokenVerifiedEvent event);
}
