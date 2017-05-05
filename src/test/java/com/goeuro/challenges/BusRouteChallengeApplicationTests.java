package com.goeuro.challenges;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = {"data.location = data/example"})
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BusRouteChallengeApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void shouldReturnFalseIfNoDirectRoute() {
		String body = this.restTemplate.getForObject("/api/direct?dep_sid=150&arr_sid=152", String.class);
		assertThat(body, is(equalTo("{\"departureSid\":150,\"arrivalSid\":152,\"directBusRoute\":false}")));
	}

	@Test
	public void shouldReturnTrueIfDirectRoute() {
		String body = this.restTemplate.getForObject("/api/direct?dep_sid=150&arr_sid=153", String.class);
		assertThat(body, is(equalTo("{\"departureSid\":150,\"arrivalSid\":153,\"directBusRoute\":true}")));
	}
}
