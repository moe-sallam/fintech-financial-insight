package org.moe.fintech.finsight.core.users.application.port.out;

import org.jmolecules.architecture.hexagonal.SecondaryPort;
import org.moe.fintech.finsight.core.users.application.event.NewUserSignedUpEvent;

@SecondaryPort
public interface NewUserSignedUp {

	void on(NewUserSignedUpEvent event);
}
