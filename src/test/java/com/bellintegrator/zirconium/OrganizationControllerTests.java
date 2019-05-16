package com.bellintegrator.zirconium;

import com.bellintegrator.zirconium.controller.ErrorResponseBody;
import com.bellintegrator.zirconium.view.OrganizationView;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static com.bellintegrator.zirconium.controller.SuccessResponseBody.SUCCESS_RESPONSE_BODY;
import static org.springframework.http.HttpMethod.POST;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrganizationControllerTests {

    @LocalServerPort
    private int port;

    private ControllerTester tester;

    private OrganizationView org1;
    private OrganizationView org2;
    private OrganizationView org3;

    private List<OrganizationView> expectedList;
    private OrganizationView filterPattern1;
    private OrganizationView filterPattern2;

    @Before
    public void init() {
        tester = new ControllerTester("organization", port);

        org1 = new OrganizationView();
        org1.setId(4L);
        org1.setName("АО Весна");
        org1.setFullName("Акционерное общество Весна");
        org1.setInn("7783106219");
        org1.setKpp("779479801");
        org1.setAddress("г. Москва, ул. Озёрная, д. 1");
        org1.setPhone(new HashSet<>(Arrays.asList("74957870538", "79457870540")));
        org1.setIsActive(false);

        org2 = new OrganizationView();
        org2.setId(5L);
        org2.setName("АО Вперёд");
        org2.setFullName("Акционерное общество Вперёд");
        org2.setInn("7708762314");
        org2.setKpp("770028088");
        org2.setAddress("Россия, г. Новосибирск, ул. Почтовая, 6");
        org2.setPhone(new HashSet<>(Arrays.asList("77903332211")));
        org2.setIsActive(false);

        org3 = new OrganizationView();
        org3.setId(6L);
        org3.setName("ООО РиК");
        org3.setFullName("ООО Рога и Копыта");
        org3.setInn("9905562314");
        org3.setKpp("997467372");
        org3.setAddress("Россия, г. Новомосковск, ул. Железнодорожная, 9");
        org3.setPhone(new HashSet<>(Arrays.asList("73989873227")));
        org3.setIsActive(false);

        OrganizationView orgForList1 = new OrganizationView();
        orgForList1.setId(org1.getId());
        orgForList1.setName(org1.getName());
        orgForList1.setIsActive(org1.getIsActive());

        OrganizationView orgForList2 = new OrganizationView();
        orgForList2.setId(org2.getId());
        orgForList2.setName(org2.getName());
        orgForList2.setIsActive(org2.getIsActive());

        expectedList = new ArrayList<>();
        expectedList.add(orgForList1);
        expectedList.add(orgForList2);

        filterPattern1 = new OrganizationView();
        filterPattern1.setName("АО");
        filterPattern1.setInn("99");
        filterPattern1.setIsActive(false);

        filterPattern2 = new OrganizationView();
        filterPattern2.setName("АО");
        filterPattern2.setInn("77");
        filterPattern2.setIsActive(false);
    }

    @Test
    public void testAddFail() throws JSONException {
        Assert.assertFalse(tester.testPost(new OrganizationView(), SUCCESS_RESPONSE_BODY, POST, "save"));
    }

    @Test
    public void testAddSuccess() throws JSONException {
        Assert.assertTrue(tester.testPost(org1, SUCCESS_RESPONSE_BODY, POST, "save"));
        Assert.assertTrue(tester.testPost(org2, SUCCESS_RESPONSE_BODY, POST, "save"));
        Assert.assertTrue(tester.testPost(org3, SUCCESS_RESPONSE_BODY, POST, "save"));
    }

    @Test
    public void testGetFail() throws JSONException {
        Assert.assertTrue(tester.testGet(0, new ErrorResponseBody("organization id 0 not found")));
    }

    @Test
    public void testGetSuccess() throws JSONException {
        Assert.assertTrue(tester.testGet(org1.getId(), org1));
    }

    @Test
    public void testListNotFound() throws JSONException {
        Assert.assertTrue(tester.testPost(filterPattern1, Collections.EMPTY_LIST, POST, "list"));
    }

    @Test
    public void testListFound() throws JSONException {
        Assert.assertTrue(tester.testPost(filterPattern2, expectedList, POST, "list"));
    }

    @Test
    public void testUpdateFail() throws JSONException {
        org1.setId(0L);
        org1.setName("ОАО РиК");

        Assert.assertTrue(tester.testPost(
                org1, new ErrorResponseBody("can't update: organization id 0 not found"), POST, "update")
        );
    }

    @Test
    public void testUpdateSuccess() throws JSONException {
        org1.setId(6L);

        Assert.assertTrue(tester.testPost(org1, SUCCESS_RESPONSE_BODY, POST, "update"));
    }
}
