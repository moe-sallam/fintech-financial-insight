package org.moe.fintech.finsight.core.users.infrastructure.notification;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.moe.fintech.finsight.core.users.application.port.out.EmailNotificationPort;
import org.moe.fintech.finsight.core.users.domain.EmailVerificationToken;
import org.moe.fintech.finsight.core.users.domain.UserEmailAddress;
import org.moe.fintech.finsight.core.users.domain.UserIdentifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

@Component
public class EmailService implements EmailNotificationPort {

	private final JavaMailSender javaMailSender;

	@Value("${fintech.mail.app.registration.verification-url}")
	private String verificationUrl;

	@Value("${fintech.mail.app.registration.from}")
	private String from;

	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Override
	@Transactional
	@Async
	public void sendVerificationToken(UserIdentifier id, UserEmailAddress emailAddress, EmailVerificationToken token) {
		String subject = "FinSight - Verify Your Email";
		String verificationLink;
		try {
			verificationLink = UriComponentsBuilder.fromUriString(verificationUrl).build(id.value().toString(), token.hashedToken()).toURL().toString();

		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}

		String htmlContent = """
            <html>
            <body>
                <h2>Welcome to FinSight!</h2>
                <p>Thank you for registering. Please verify your email address by clicking the link below:</p>
                <p><a href="%s">Verify my email</a></p>
                <p>This link will expire in 24 hours.</p>
                <p>If you did not create an account, please ignore this email.</p>
                <p>Thanks,<br>The FinSight Team</p>
            </body>
            </html>
            """.formatted(verificationLink);
		sendHtmlEmail(emailAddress.value(), subject, htmlContent);
	}

	@Override
	@Transactional
	@Async
	public void sendWelcomeEmail(UserIdentifier id, UserEmailAddress emailAddress) {
		String subject = "Welcome to FinSight!";

		String htmlContent = """
            <html>
            <body>
                <h2>Welcome to FinSight!</h2>
                <p>Your account has been successfully verified.</p>
                <p>You can now start managing your finances with our powerful tools.</p>
                <p>Here are some tips to get started:</p>
                <ul>
                    <li>Connect your bank accounts to import transactions</li>
                    <li>Set up your budget categories</li>
                    <li>Define your financial goals</li>
                </ul>
                <p>Thanks for choosing FinSight!</p>
                <p>Best regards,<br>The FinSight Team</p>
            </body>
            </html>
            """;

		sendHtmlEmail(emailAddress.value(), subject, htmlContent);
	}

	@Override
	@Transactional
	@Async
	public void sendProducts(UserIdentifier id, UserEmailAddress emailAddress) {
		String subject = "Welcome to FinSight!";

		String htmlContent = """
            <html>
            <body>
                <h2>Welcome to FinSight!</h2>
                <p>Your account has been successfully verified.</p>
                <p>You can now start managing your finances with our powerful tools.</p>
                <p>Here are some tips to get started:</p>
                <ul>
                    <li>Connect your bank accounts to import transactions</li>
                    <li>Set up your budget categories</li>
                    <li>Define your financial goals</li>
                </ul>
                <p>Thanks for choosing FinSight!</p>
                <p>Best regards,<br>The FinSight Team</p>
            </body>
            </html>
            """;

		sendHtmlEmail(emailAddress.value(), subject, htmlContent);
	}

	private void sendHtmlEmail(String to, String subject, String htmlBody) {
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(
					message,
					MimeMessageHelper.MULTIPART_MODE_MIXED,
					StandardCharsets.UTF_8.name()
			);

			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(htmlBody, true);

			javaMailSender.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException("Failed to send email", e);
		}
	}
}
