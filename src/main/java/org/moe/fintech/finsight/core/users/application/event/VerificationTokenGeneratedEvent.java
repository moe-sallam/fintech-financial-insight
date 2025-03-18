package org.moe.fintech.finsight.core.users.application.event;

import org.jmolecules.event.types.DomainEvent;
import org.moe.fintech.finsight.core.users.domain.EmailVerificationToken;
import org.moe.fintech.finsight.core.users.domain.UserEmailAddress;
import org.moe.fintech.finsight.core.users.domain.UserIdentifier;

public record VerificationTokenGeneratedEvent(UserIdentifier id, EmailVerificationToken token) implements DomainEvent {
}
