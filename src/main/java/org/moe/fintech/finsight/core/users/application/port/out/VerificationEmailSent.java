package org.moe.fintech.finsight.core.users.application.port.out;

import org.jmolecules.architecture.hexagonal.SecondaryPort;
import org.moe.fintech.finsight.core.users.application.event.VerificationEmailSentEvent;

@SecondaryPort
public interface VerificationEmailSent {

	void on(VerificationEmailSentEvent event);
}
