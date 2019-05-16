package com.bellintegrator.zirconium;

import com.bellintegrator.zirconium.controller.ErrorResponseBody;
import com.bellintegrator.zirconium.view.UserView;
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
public class UserControllerTests {

    @LocalServerPort
    private int port;

    private ControllerTester tester;

    private UserView user1;
    private UserView user2;
    private UserView user3;

    private List<UserView> expectedList;
    private UserView filterPattern1;
    private UserView filterPattern2;

    @Before
    public void init() {
        tester = new ControllerTester("user", port);

        user1 = new UserView();
        user1.setId(16L);
        user1.setOfficeId(6L);
        user1.setFirstName("Владимир");
        user1.setSecondName("Черных");
        user1.setPosition("финансовый директор");
        user1.setDocCode("21");
        user1.setDocName("Паспорт гражданина Российской Федерации");
        user1.setCitizenshipCode("643");
        user1.setCitizenshipName("Российская Федерация");
        user1.setIsIdentified(true);

        user2 = new UserView();
        user2.setId(17L);
        user2.setOfficeId(6L);
        user2.setFirstName("Василий");
        user2.setSecondName("Макаров");
        user2.setPosition("технический директор");
        user2.setDocCode("21");
        user2.setDocName("Паспорт гражданина Российской Федерации");
        user2.setCitizenshipCode("643");
        user2.setCitizenshipName("Российская Федерация");
        user2.setIsIdentified(true);

        user3 = new UserView();
        user3.setId(18L);
        user3.setOfficeId(6L);
        user3.setFirstName("Дмитрий");
        user3.setPosition("финансовый директор");
        user3.setCitizenshipCode("112");
        user3.setIsIdentified(true);

        UserView userForList1 = new UserView();
        userForList1.setId(user1.getId());
        userForList1.setFirstName(user1.getFirstName());
        userForList1.setSecondName(user1.getSecondName());
        userForList1.setMiddleName(user1.getMiddleName());
        userForList1.setPosition(user1.getPosition());

        UserView userForList2 = new UserView();
        userForList2.setId(user2.getId());
        userForList2.setFirstName(user2.getFirstName());
        userForList2.setSecondName(user2.getSecondName());
        userForList2.setMiddleName(user2.getMiddleName());
        userForList2.setPosition(user2.getPosition());

        expectedList = new ArrayList<>();
        expectedList.add(userForList1);
        expectedList.add(userForList2);

        filterPattern1 = new UserView();
        filterPattern1.setOfficeId(6L);
        filterPattern1.setCitizenshipCode("156");

        filterPattern2 = new UserView();
        filterPattern2.setOfficeId(6L);
        filterPattern2.setPosition("директор");
        filterPattern2.setDocCode("21");
        filterPattern2.setCitizenshipCode("643");
    }

    @Test
    public void testAddFail() throws JSONException {
        Assert.assertFalse(tester.testPost(new UserView(), SUCCESS_RESPONSE_BODY, POST, "save"));
    }

    @Test
    public void testAddSuccess() throws JSONException {
        Assert.assertTrue(tester.testPost(user1, SUCCESS_RESPONSE_BODY, POST, "save"));
        Assert.assertTrue(tester.testPost(user2, SUCCESS_RESPONSE_BODY, POST, "save"));
        Assert.assertTrue(tester.testPost(user3, SUCCESS_RESPONSE_BODY, POST, "save"));
    }

    @Test
    public void testGetFail() throws JSONException {
        Assert.assertTrue(tester.testGet(0, new ErrorResponseBody("user id 0 not found")));
    }

    @Test
    public void testGetSuccess() throws JSONException {
        Assert.assertTrue(tester.testGet(user1.getId(), user1));
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
        user1.setId(0L);
        user1.setFirstName("Владимир");
        user1.setPosition("генеральный директор");

        Assert.assertTrue(tester.testPost(
                user1, new ErrorResponseBody("can't update: user id 0 not found"), POST, "update")
        );
    }

    @Test
    public void testUpdateSuccess() throws JSONException {
        user1.setId(16L);

        Assert.assertTrue(tester.testPost(user1, SUCCESS_RESPONSE_BODY, POST, "update"));
    }
}
