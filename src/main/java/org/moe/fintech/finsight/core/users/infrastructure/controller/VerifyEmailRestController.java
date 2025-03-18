package org.moe.fintech.finsight.core.users.infrastructure.controller;

import org.moe.fintech.finsight.core.users.application.port.in.VerifyToken;
import org.moe.fintech.finsight.core.users.domain.EmailVerificationToken;
import org.moe.fintech.finsight.core.users.domain.UserIdentifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@ResponseBody
@RequestMapping(
		path = VerifyEmailRestController.BASE_URI,
		produces = MediaType.APPLICATION_JSON_VALUE
)
class VerifyEmailRestController {

	static final String BASE_URI = "/api/registrations/id";

	private final VerifyToken verifyToken;

	VerifyEmailRestController(VerifyToken verifyToken) {
		this.verifyToken = verifyToken;
	}

	@GetMapping("/{id}/verify")
	ResponseEntity<?> verifyEmail(@RequestParam("token") String token, @PathVariable("id") String id) {
		verifyToken.execute(UserIdentifier.of(UUID.fromString(id)), token);
		return ResponseEntity.accepted().build();
	}
}
