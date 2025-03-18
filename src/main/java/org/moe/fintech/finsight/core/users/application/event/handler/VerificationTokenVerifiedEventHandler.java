package org.moe.fintech.finsight.core.users.application.event.handler;

import org.moe.fintech.finsight.core.users.application.event.VerificationTokenVerifiedEvent;
import org.moe.fintech.finsight.core.users.application.port.in.Register;
import org.moe.fintech.finsight.core.users.application.port.out.VerificationTokenVerified;
import org.moe.fintech.finsight.core.users.domain.UserRepository;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class VerificationTokenVerifiedEventHandler implements VerificationTokenVerified {

	private final UserRepository repository;
	private final Register register;
	private final PasswordEncoder passwordEncoder;

	public VerificationTokenVerifiedEventHandler(UserRepository repository, Register register) {
		this.repository = repository;
		this.register = register;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@Override
	@ApplicationModuleListener
	public void on(VerificationTokenVerifiedEvent event) {
		repository.findById(event.id())
				.ifPresent(user -> {
					boolean isValid = passwordEncoder.matches(user.getId().value().toString(), event.token());
					if (isValid) {
						user.verifyEmail();
						repository.save(user);
						register.execute(event.id(), user.emailAddress());
					} else {
						user.invalidate();
						repository.save(user);
					}
				});
	}
}
