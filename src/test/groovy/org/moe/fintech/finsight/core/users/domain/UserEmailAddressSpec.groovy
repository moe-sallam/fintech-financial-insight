package org.moe.fintech.finsight.core.users.domain

import net.datafaker.Faker
import spock.lang.Specification

class UserEmailAddressSpec extends Specification {

	private final Faker faker = new Faker()

	def 'should create a user email address from given email value'() {
		given:
		def value = faker.internet().emailAddress()

		when:
		def result = UserEmailAddress.of(value)

		then:
		result
		result.value() == value
	}

	def 'should reject invalid email pattern'() {
		given:
		def value = faker.text().text(20)

		when:
		UserEmailAddress.of(value)

		then:
		def exception = thrown(IllegalArgumentException)
		exception.message == "Invalid User Email: (${value})"
	}
}
