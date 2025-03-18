package org.moe.fintech.finsight.core.users.application.port.in;

import org.jmolecules.architecture.hexagonal.PrimaryPort;
import org.moe.fintech.finsight.core.users.domain.UserIdentifier;

@PrimaryPort
public interface GenerateVerificationToken {

	void execute(UserIdentifier id);
}
