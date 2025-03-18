package org.moe.fintech.finsight.core.users.domain

import net.datafaker.Faker
import org.apache.commons.lang3.StringUtils
import spock.lang.Specification
import spock.lang.Unroll

class UsernameSpec extends Specification {
	private final Faker faker = new Faker()

	def 'should create a username value object from given value'() {
		given:
		def value = faker.internet().username()

		when:
		def result = Username.of(value)

		then:
		result
		result.value()
	}

	@Unroll
	def 'should reject blank username values'() {
		when:
		Username.of(value)

		then:
		def exception = thrown(IllegalStateException)
		exception.message == 'Username cannot be blank'

		where:
		value << [null, StringUtils.EMPTY, StringUtils.SPACE]
	}

	def 'should reject a username value with length less than 5 characters'() {
		given:
		def value = faker.text().text(4)

		when:
		Username.of(value)

		then:
		def exception = thrown(IllegalStateException)
		exception.message == "Username must have at least 5 alphanumeric characters: (${value})"
	}
}
