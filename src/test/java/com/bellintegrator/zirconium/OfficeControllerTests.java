package com.bellintegrator.zirconium;

import com.bellintegrator.zirconium.controller.ErrorResponseBody;
import com.bellintegrator.zirconium.view.OfficeView;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static com.bellintegrator.zirconium.controller.SuccessResponseBody.SUCCESS_RESPONSE_BODY;
import static org.springframework.http.HttpMethod.POST;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OfficeControllerTests {

    @LocalServerPort
    private int port;

    private ControllerTester tester;

    private OfficeView office1;
    private OfficeView office2;
    private OfficeView office3;

    private List<OfficeView> expectedList;

    private OfficeView filterPattern1;
    private OfficeView filterPattern2;

    @Before
    public void init() {
        tester = new ControllerTester("office", port);

        office1 = new OfficeView();
        office1.setId(7L);
        office1.setOrgId(3L);
        office1.setName("центр тестирования");
        office1.setAddress("г. Москва, ул. Озёрная, д. 1");
        office1.setPhone(new HashSet<>(Arrays.asList("74957870538", "79457870540")));
        office1.setIsActive(true);

        office2 = new OfficeView();
        office2.setId(8L);
        office2.setOrgId(3L);
        office2.setName("научный центр");
        office2.setAddress("Россия, г. Новосибирск, ул. Почтовая, 6");
        office2.setPhone(new HashSet<>(Arrays.asList("77903332211")));
        office2.setIsActive(true);

        office3 = new OfficeView();
        office3.setId(9L);
        office3.setOrgId(3L);
        office3.setName("филиал");
        office3.setPhone(new HashSet<>(Arrays.asList("77903332211")));
        office3.setIsActive(true);

        OfficeView officeForList1 = new OfficeView();
        officeForList1.setId(office1.getId());
        officeForList1.setName(office1.getName());
        officeForList1.setIsActive(office1.getIsActive());

        OfficeView officeForList2 = new OfficeView();
        officeForList2.setId(office2.getId());
        officeForList2.setName(office2.getName());
        officeForList2.setIsActive(office2.getIsActive());

        expectedList = new ArrayList<>();
        expectedList.add(officeForList1);
        expectedList.add(officeForList2);

        filterPattern1 = new OfficeView();
        filterPattern1.setOrgId(3L);
        filterPattern1.setName("центр");
        filterPattern1.setPhone(new HashSet<>(Arrays.asList("77777777777")));

        filterPattern2 = new OfficeView();
        filterPattern2.setOrgId(3L);
        filterPattern2.setName("центр");
        filterPattern2.setPhone(new HashSet<>(Arrays.asList("77903332211", "74957870538")));
    }

    @Test
    public void testAddFail() throws JSONException {
        Assert.assertFalse(tester.testPost(new OfficeView(), SUCCESS_RESPONSE_BODY, POST, "save"));
    }

    @Test
    public void testAddSuccess() throws JSONException {
        Assert.assertTrue(tester.testPost(office1, SUCCESS_RESPONSE_BODY, POST, "save"));
        Assert.assertTrue(tester.testPost(office2, SUCCESS_RESPONSE_BODY, POST, "save"));
        Assert.assertTrue(tester.testPost(office3, SUCCESS_RESPONSE_BODY, POST, "save"));
    }

    @Test
    public void testGetFail() throws JSONException {
        Assert.assertTrue(tester.testGet(0, new ErrorResponseBody("office id 0 not found")));
    }

    @Test
    public void testGetSuccess() throws JSONException {
        Assert.assertTrue(tester.testGet(office1.getId(), office1));
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
        office1.setId(0L);
        office1.setName("Отдел разработки");
        office1.setAddress("г. Москва, Рублёвское ш., д. 29");
        office1.setPhone(new HashSet<>(Arrays.asList("74994445840", "74994445841")));

        Assert.assertTrue(tester.testPost(
                office1, new ErrorResponseBody("can't update: office id 0 not found"), POST, "update")
        );
    }

    @Test
    public void testUpdateSuccess() throws JSONException {
        office1.setId(7L);

        Assert.assertTrue(tester.testPost(office1, SUCCESS_RESPONSE_BODY, POST, "update"));
    }
}
