package org.moe.fintech.finsight.core.users.domain;

import org.jmolecules.ddd.types.AggregateRoot;
import org.springframework.util.Assert;

import java.time.Instant;

/**
 * Registration flow:
 * 1. SignupUseCase(email)
 * 	- Operations:
 * 		- Ensures email does not already exist in DB
 * 		- Generates a user with a random user ID and status of CREATED
 * 		- Saves user in DB
 * 		- Publishes NewUserSignedUpEvent with user ID and email
 * 2. NewUserSignedUpEventHandler(NewUserSignedUpEvent)
 * 	- Operations:
 * 		- Executes GenerateVerificationTokenUseCase(UserId)
 * 3. GenerateVerificationTokenUseCase(UserId)
 * 		- Ensures user with given ID exists in DB
 * 		- Generates a verification token for the new user using UserId
 * 		- Assigns verification token to user
 * 		- Sets status to PENDING_EMAIL_VERIFICATION
 * 		- Publishes VerificationTokenGeneratedEvent(UserId)
 * 4. VerificationTokenGeneratedEventHandler(UserId)
 * 	- Operations:
 * 		- Executes SendVerificationEmailUseCase(UserId)
 * 5. SendVerificationEmailUseCase(UserId)
 * 	- Operations:
 * 		- Ensures user with given ID exists in DB
 * 		- Sends a verification email
 * 		- Sets status to VERIFICATION_EMAIL_SENT
 * 6. VerifyTokenUseCase(UserId)
 * 	- Operations:
 * 		- Ensures user with given ID exists in DB
 * 		- Verifies token against what was already stored
 * 		- If valid, sends a welcome email to user
 * 		- Sets status to EMAIL_VERIFIED
 * 		- Publishes EmailVerifiedEvent(UserId)
 * 7. EmailVerifiedEventHandler(UserId)
 * 	- Operations:
 * 		- Executes RegisterUserUseCase(UserId)
 * 8. RegisterUserUseCase(UserId)
 * 	- Operations:
 * 		- Ensures user with given ID exists in DB
 * 		- Sets user status to pending verification
 * 		- Sends a registration confirmation email and a login link
 */
public class User implements AggregateRoot<User, UserIdentifier> {

	private final UserIdentifier id;
	private UserEmailAddress emailAddress;
	private EmailVerificationToken verificationToken;
	private UserState state;

	User(UserIdentifier id) {
		this.id = UserIdentifier.generate();
		this.state = UserState.CREATED;
	}

	public static User instance(UserIdentifier id) {
		return new User(id);
	}

	private User(UserIdentifier id, UserEmailAddress emailAddress, EmailVerificationToken token, UserState state) {
		this.id = id;
		this.emailAddress = emailAddress;
		this.verificationToken = token;
		this.state = state;
	}

	public static User from(UserIdentifier id, UserEmailAddress emailAddress, String token, Instant tokenExpiresAt, UserStatus status, Instant occurredAt) {
		return new User(
				id,
				emailAddress,
				new EmailVerificationToken(token, tokenExpiresAt),
				new UserState(status, occurredAt)
		);
	}

	public void signup(UserEmailAddress emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void sendEmail() {
		this.state = UserState.EMAIL_VERIFICATION_PENDING;
	}

	public void invalidate() {
		this.state = UserState.INVALIDATED;
	}

	public void sendVerificationToken(EmailVerificationToken token) {
		this.verificationToken = token;
		this.state = UserState.EMAIL_VERIFICATION_TOKEN_CREATED;
	}

	public void verifyEmail() {
		this.state = UserState.EMAIL_VERIFIED;
	}

	public void readyToActivate() {
		this.verificationToken = verificationToken.reset();
		this.state = UserState.ACTIVATION_PENDING;
	}

	public UserEmailAddress emailAddress() {
		return this.emailAddress;
	}

	public EmailVerificationToken verificationToken() {
		return this.verificationToken;
	}

	@Override
	public UserIdentifier getId() {
		return id;
	}

	public UserState state() {
		return this.state;
	}

	public boolean hasToken() {
		return this.verificationToken != null;
	}
}
