package org.moe.fintech.finsight.core.users.application.port.out;

import org.jmolecules.architecture.hexagonal.SecondaryPort;
import org.moe.fintech.finsight.core.users.application.event.VerificationTokenGeneratedEvent;

@SecondaryPort
public interface VerificationTokenGenerated {

	void on(VerificationTokenGeneratedEvent event);
}
