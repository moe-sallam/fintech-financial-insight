package org.moe.fintech.finsight.core.users.application.event;

import org.jmolecules.event.types.DomainEvent;
import org.moe.fintech.finsight.core.users.domain.UserIdentifier;

public record VerificationTokenVerifiedEvent(UserIdentifier id, String token) implements DomainEvent {
}
