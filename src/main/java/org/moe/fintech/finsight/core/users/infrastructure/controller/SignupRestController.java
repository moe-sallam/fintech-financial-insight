package org.moe.fintech.finsight.core.users.infrastructure.controller;

import org.jmolecules.architecture.hexagonal.PrimaryAdapter;
import org.moe.fintech.finsight.core.users.application.port.in.Signup;
import org.moe.fintech.finsight.core.users.domain.UserEmailAddress;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
@RequestMapping(
		path = SignupRestController.BASE_URI,
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
)
@PrimaryAdapter
class SignupRestController {

	static final String BASE_URI = "/api/registrations/signup";

	private final Signup signup;

	SignupRestController(Signup signup) {
		this.signup = signup;
	}

	@PostMapping
	ResponseEntity<?> signup(@RequestBody SignupRequest request) {
		signup.execute(UserEmailAddress.of(request.email()));
		return ResponseEntity.accepted().build();
	}

	record SignupRequest(String email) {}
}
