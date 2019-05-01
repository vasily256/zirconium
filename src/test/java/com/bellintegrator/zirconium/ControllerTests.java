package com.bellintegrator.zirconium;

import com.bellintegrator.zirconium.controller.ErrorResponseBody;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static com.bellintegrator.zirconium.controller.SuccessResponseBody.SUCCESS_RESPONSE_BODY;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ControllerTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private HttpHeaders headers;

	@Autowired
	private Gson gson;

	static OfficeView office = new OfficeView(
			2L,
			1L,
			"Отдел тестирования",
			"г. Москва, ул. Озёрная, д. 1",
			Arrays.asList("74957870538"),
			true
	);

	// Сохранение офиса id 2 (в фейковом сервисисе также хранится офис id 1)
	@Test
	public void testAddOffice() throws JSONException {
		HttpEntity<OfficeView> entity = new HttpEntity<>(office, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/save"), HttpMethod.POST, entity, String.class);

		String location = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

		assertTrue(location.contains("/2"));
		JSONAssert.assertEquals(gson.toJson(SUCCESS_RESPONSE_BODY), response.getBody(), false);
	}

	// Запрос данных об офисе id 2
	@Test
	public void testGetOffice1() throws JSONException {
		HttpEntity<OfficeView> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/2"), HttpMethod.GET, entity, String.class);

		JSONAssert.assertEquals(gson.toJson(wrap(office)), response.getBody(), false);
	}

	// Попытка запроса данных о несуществующем офисе id 3
	@Test
	public void testGetOffice2() throws JSONException {
		HttpEntity<OfficeView> entity = new HttpEntity<>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/3"), HttpMethod.GET, entity, String.class);

		ErrorResponseBody errorResponseBody = new ErrorResponseBody("office id 3 not found");

		JSONAssert.assertEquals(gson.toJson(errorResponseBody), response.getBody(), false);
	}

	// Запрос списка офисов
    @Test
	public void testListOffice() throws JSONException {
		OfficeView office2 = new OfficeView(
				1L,
				1L,
				"Исследовательский центр",
				"г. Москва, ул. Вербная, д. 5",
				Arrays.asList("74957870544", "74957870545"),
				true
		);

		List<OfficeView> list = new ArrayList<>();
		list.add(office);
		list.add(office2);

		HttpEntity<OfficeView> entity = new HttpEntity<>(office, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/list"), HttpMethod.POST, entity, String.class);

		JSONAssert.assertEquals(gson.toJson(wrap(list)), response.getBody(), false);
	}

	// Обновление сведений об офисе id 2
	@Test
	public void testUpdateOffice1() throws JSONException {
        office.setAddress("г. Москва, Рублёвское ш., д. 29");
		office.setPhone(Arrays.asList("74994445840", "74994445841"));

		HttpEntity<OfficeView> entity = new HttpEntity<>(office, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/update"), HttpMethod.POST, entity, String.class);

		JSONAssert.assertEquals(gson.toJson(SUCCESS_RESPONSE_BODY), response.getBody(), false);
	}

	// Попытка обновления сведений о несуществующем офисе id 3
	@Test
	public void testUpdateOffice2() throws JSONException {
		office.setId(3L);
		office.setAddress("г. Москва, Рублёвское ш., д. 29");
		office.setPhone(Arrays.asList("74994445840", "74994445841"));

		HttpEntity<OfficeView> entity = new HttpEntity<>(office, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/update"), HttpMethod.POST, entity, String.class);

		ErrorResponseBody errorResponseBody = new ErrorResponseBody("can't update: office id 3 not found");

		JSONAssert.assertEquals(gson.toJson(errorResponseBody), response.getBody(), false);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + "/api/mock-office" + uri;
	}

	static <T> JSONResponseWrapper.Wrapper wrap(T o) {
	    return new JSONResponseWrapper.Wrapper<>(o);
	}
}