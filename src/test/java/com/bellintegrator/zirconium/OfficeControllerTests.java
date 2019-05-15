package com.bellintegrator.zirconium;

import com.bellintegrator.zirconium.controller.ErrorResponseBody;
import com.bellintegrator.zirconium.controller.JSONResponseWrapper;
import com.bellintegrator.zirconium.model.Office;
import com.bellintegrator.zirconium.view.OfficeView;
import com.google.gson.Gson;
import org.json.JSONException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class OfficeControllerTests {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private HttpHeaders headers;

    @Autowired
    private Gson gson;

    static OfficeView office = new OfficeView();

    static {
        office.setId(7L);
        office.setOrgId(1L);
        office.setName("Центр тестирования");
        office.setAddress("г. Москва, ул. Озёрная, д. 1");
        office.setPhone(new HashSet<>(Arrays.asList("74957870538", "79457870540")));
        office.setIsActive(true);
    }

    // Сохранение офиса id 5
    @Test
    public void testAddOffice() throws JSONException {
        HttpEntity<OfficeView> entity = new HttpEntity<>(office, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/save"), HttpMethod.POST, entity, String.class);

        String location = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

        assertTrue(location.contains("/7"));
        JSONAssert.assertEquals(gson.toJson(SUCCESS_RESPONSE_BODY), response.getBody(), false);
    }

    // Запрос данных об офисе id 5
    @Test
    public void testGetOffice1() throws JSONException {
        HttpEntity<OfficeView> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/7"), HttpMethod.GET, entity, String.class);

        JSONAssert.assertEquals(gson.toJson(wrap(office)), response.getBody(), false);
    }

    // Попытка запроса данных о несуществующем офисе id 6
    @Test
    public void testGetOffice2() throws JSONException {
        HttpEntity<OfficeView> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/8"), HttpMethod.GET, entity, String.class);

        ErrorResponseBody errorResponseBody = new ErrorResponseBody("office id 8 not found");

        JSONAssert.assertEquals(gson.toJson(errorResponseBody), response.getBody(), false);
    }

    // Запрос списка офисов по заданным критериям
    @Test
    public void testListOffice() throws JSONException {
        OfficeView office2 = new OfficeView();
        office2.setId(1L);
        office2.setOrgId(1L);
        office2.setName("Научный центр");
        office2.setAddress("Россия, г. Новосибирск, ул. Почтовая, 6");
        office2.setPhone(new HashSet<>(Arrays.asList("77903332211")));
        office2.setIsActive(true);

        List<OfficeView> list = new ArrayList<>();

        OfficeView officeForList1 = new OfficeView();

        officeForList1.setId(office.getId());
        officeForList1.setName(office.getName());
        officeForList1.setIsActive(office.getIsActive());

        OfficeView officeForList2 = new OfficeView();

        officeForList2.setId(office2.getId());
        officeForList2.setName(office2.getName());
        officeForList2.setIsActive(office2.getIsActive());

        list.add(officeForList1);
        list.add(officeForList2);

        OfficeView officePattern = new OfficeView();

        officePattern.setOrgId(1L);
        officePattern.setName("ентр");
        officePattern.setPhone(new HashSet<>(Arrays.asList("77903332211", "74957870538")));

        HttpEntity<OfficeView> entity = new HttpEntity<>(officePattern, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/list"), HttpMethod.POST, entity, String.class);

        JSONAssert.assertEquals(gson.toJson(wrap(list)), response.getBody(), false);
    }

    // Обновление сведений об офисе id 5
    @Test
    public void testUpdateOffice1() throws JSONException {
        office.setId(7L);
        office.setName("Отдел разработки");
        office.setAddress("г. Москва, Рублёвское ш., д. 29");
        office.setPhone(new HashSet<>(Arrays.asList("74994445840", "74994445841")));

        HttpEntity<OfficeView> entity = new HttpEntity<>(office, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/update"), HttpMethod.POST, entity, String.class);

        JSONAssert.assertEquals(gson.toJson(SUCCESS_RESPONSE_BODY), response.getBody(), false);
    }

    // Попытка обновления сведений о несуществующем офисе id 6
    @Test
    public void testUpdateOffice2() throws JSONException {
        office.setId(8L);
        office.setName("Отдел разработки");
        office.setAddress("г. Москва, Рублёвское ш., д. 29");
        office.setPhone(new HashSet<>(Arrays.asList("74994445840", "74994445841")));

        HttpEntity<OfficeView> entity = new HttpEntity<>(office, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/update"), HttpMethod.POST, entity, String.class);

        ErrorResponseBody errorResponseBody = new ErrorResponseBody("can't update: office id 8 not found");

        JSONAssert.assertEquals(gson.toJson(errorResponseBody), response.getBody(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + "/api/office" + uri;
    }

    static <T> JSONResponseWrapper.Wrapper wrap(T o) {
        return new JSONResponseWrapper.Wrapper<>(o);
    }
}