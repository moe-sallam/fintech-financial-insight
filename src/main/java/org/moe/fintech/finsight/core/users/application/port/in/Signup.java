package org.moe.fintech.finsight.core.users.application.port.in;

import org.jmolecules.architecture.hexagonal.PrimaryPort;
import org.moe.fintech.finsight.core.users.domain.UserEmailAddress;

@PrimaryPort
public interface Signup {

	void execute(UserEmailAddress email);
}
