package com.bellintegrator.zirconium;

import com.bellintegrator.zirconium.controller.JSONResponseWrapper;
import com.bellintegrator.zirconium.view.OfficeView;
import com.google.gson.Gson;
import org.json.JSONException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OfficeControllerTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private Gson gson;

	private OfficeView office = new OfficeView(
			2,
			1,
			"Отдел тестирования",
			"г. Москва, ул. Озёрная, д. 1",
			"74957870538",
			true
	);

	@Test
	public void testAddOffice() {
		HttpEntity<OfficeView> entity = new HttpEntity<>(office, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/save"), HttpMethod.POST, entity, String.class);

		String location = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

		assertTrue(location.contains("/2"));
	}

	@Test
	public void testGetOffice() throws JSONException {
		HttpEntity<OfficeView> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/2"), HttpMethod.GET, entity, String.class);

		JSONAssert.assertEquals(gson.toJson(wrap(office)), response.getBody(), false);
	}

	@Test
	public void testUpdateOffice() throws JSONException {
        office.setAddress("г. Москва, Рублёвское ш., д. 29");
		office.setPhone("74994445840");

		HttpEntity<OfficeView> entity = new HttpEntity<>(office, headers);

		restTemplate.exchange(
				createURLWithPort("/update"), HttpMethod.POST, entity, String.class);

        testGetOffice();
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + "/api/office" + uri;
	}

	private <T> JSONResponseWrapper.Wrapper wrap(T o) {
	    return new JSONResponseWrapper.Wrapper<>(o);
	}
}