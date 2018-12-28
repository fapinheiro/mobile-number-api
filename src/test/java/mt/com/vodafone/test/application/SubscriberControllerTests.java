/**
 * @author filipe.pinheiro, 29/09/2018
 */
package mt.com.vodafone.test.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import mt.com.vodafone.application.Application;
import mt.com.vodafone.dao.SubscriberRepository;
import mt.com.vodafone.model.Subscriber;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class SubscriberControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private SubscriberRepository subscriberRepository;

	@Before
	public void deleteAllBeforeTests() throws Exception {
		subscriberRepository.deleteAll();
	}

	@Test
	public void shouldTestIfSubscriberDoesNotExist() throws Exception {
		mockMvc.perform(get("/subscribers/-1")).andExpect(status().isNotFound());
	}

	@Test
	public void shouldCheckSubscriberBeforeDeleting() throws Exception {
		mockMvc.perform(delete("/subscribers/-1")).andExpect(status().isNotFound());
	}

	@Test
	public void shouldReturnEmptyArrayWhenNoSubscribersFound() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/subscribers")).andDo(print()).andExpect(status().isOk())
				.andReturn();
		String body = mvcResult.getResponse().getContentAsString();
		assertThat(body).isEqualTo("[]");
	}

	@Test
	public void shouldNotCreateSameSubscriber() throws Exception {

		final String body = "{ \"msisdn\": \"1155972532645\", \"customerIdOwner\": 0, \"customerIdUser\": 0,  \"serviceType\": \"MOBILE_PREPAID\", \"serviceStartDate\": 1528208058559 }";

		// Create subscriber
		mockMvc.perform(post("/subscribers").header("Content-Type", "application/json").content(body))
				.andExpect(status().isOk());

		// Try to create the same subscriber
		mockMvc.perform(post("/subscribers").header("Content-Type", "application/json").content(body))
				.andExpect(status().isConflict());
	}

	@Test
	public void shouldUpdateOnlyServiceType() throws Exception {

		// Create subscriber
		String body = "{ \"msisdn\": \"1155985408857\", \"customerIdOwner\": 0, \"customerIdUser\": 0,  \"serviceType\": \"MOBILE_PREPAID\", \"serviceStartDate\": 1528208058559 }";
		MvcResult mvcResult = mockMvc
				.perform(post("/subscribers").header("Content-Type", "application/json").content(body))
				.andExpect(status().isOk()).andReturn();

		// Mapping jsonResponse to the class
		ObjectMapper mapper = new ObjectMapper();
		Subscriber subscriber = mapper.readValue(mvcResult.getResponse().getContentAsString(), Subscriber.class);

		// Trying to alter all fields
		body = "{ \"msisdn\": \"1155985408858\", \"customerIdOwner\": 1, \"customerIdUser\": 1,  \"serviceType\": \"MOBILE_POSTPAID\", \"serviceStartDate\": 1528208058558 }";
		mvcResult = mockMvc.perform(
				put("/subscribers/" + subscriber.getId()).header("Content-Type", "application/json").content(body))
				.andExpect(status().isOk()).andReturn();

		// Mapping jsonResponse to class
		mapper = new ObjectMapper();
		Subscriber subscriberUpdated = mapper.readValue(mvcResult.getResponse().getContentAsString(), Subscriber.class);

		// Check if only servicetype was updated
		assertThat(subscriber).isNotEqualTo(subscriberUpdated);
		assertThat(subscriber.getMsisdn()).isEqualTo(subscriberUpdated.getMsisdn());
		assertThat(subscriber.getCustomerIdOwner()).isEqualTo(subscriberUpdated.getCustomerIdOwner());
		assertThat(subscriber.getCustomerIdUser()).isEqualTo(subscriberUpdated.getCustomerIdUser());
		assertThat(subscriber.getServiceStartDate()).isEqualTo(subscriberUpdated.getServiceStartDate());
		assertThat(subscriber.getServiceType()).isNotEqualTo(subscriberUpdated.getServiceType());
	}

	@Test
	public void shouldNotUpdateNonExistingSubscriber() throws Exception {

		// Trying to alter all fields
		String body = "{ \"msisdn\": \"1155985408858\", \"customerIdOwner\": 1, \"customerIdUser\": 1,  \"serviceType\": \"MOBILE_POSTPAID\", \"serviceStartDate\": 1528208058558 }";
		mockMvc.perform(
				put("/subscribers/-1").header("Content-Type", "application/json").content(body))
				.andExpect(status().isNotFound());

	}
	
	@Test
	public void shouldDeleteSubscriber() throws Exception {

		// Create subscriber
		String body = "{ \"msisdn\": \"1155985408856\", \"customerIdOwner\": 0, \"customerIdUser\": 0,  \"serviceType\": \"MOBILE_PREPAID\", \"serviceStartDate\": 1528208058559 }";
		MvcResult mvcResult = mockMvc
				.perform(post("/subscribers").header("Content-Type", "application/json").content(body))
				.andExpect(status().isOk()).andReturn();

		// Mapping jsonResponse to the class
		ObjectMapper mapper = new ObjectMapper();
		Subscriber subscriber = mapper.readValue(mvcResult.getResponse().getContentAsString(), Subscriber.class);

		// Deleting
		mockMvc.perform(delete("/subscribers/" + subscriber.getId()).header("Content-Type", "application/json"))
				.andExpect(status().isNoContent());

	}

}