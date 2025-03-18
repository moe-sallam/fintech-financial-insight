package org.moe.fintech.finsight.core.users.domain

import net.datafaker.Faker
import spock.lang.Specification

class UserPhoneSpec extends Specification {

	private final Faker faker = new Faker()

	def 'should create a user phone value object from give phone number'() {
		given:
		def value = faker.phoneNumber().cellPhone()

		when:
		def result = UserPhone.of(value)

		then:
		result
		result.value() == value
	}

	def 'should reject invalid phone number pattern'() {
		given:
		def value = faker.number().digits(16)

		when:
		UserPhone.of(value)

		then:
		def exception = thrown(IllegalArgumentException)
		exception.message == "Invalid User Phone: (${value})"
	}
}
