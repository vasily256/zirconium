package com.bellintegrator.zirconium;

import com.bellintegrator.zirconium.controller.Controller;
import com.bellintegrator.zirconium.service.classifier.ClassifierService;
import com.bellintegrator.zirconium.service.content.ContentService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static com.bellintegrator.zirconium.ControllerTests.*;
import static com.bellintegrator.zirconium.controller.SuccessResponseBody.*;
import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ControllerMockTests {

    private Gson gson = new Gson();

    @Mock
    private ContentService contentSrv;

    @Mock
    private ClassifierService classifierSrv;

    private Controller ctrl;

    @Before
    public void start() {
        Map<String, ContentService> contentServiceMap = new HashMap<>();
        contentServiceMap.put("office", contentSrv);
        Map<String, ClassifierService> classifierServiceMap = new HashMap<>();
        classifierServiceMap.put("docs", classifierSrv);
        ctrl = new Controller(contentServiceMap, classifierServiceMap);
    }

    // Сохранение офиса
    @Test
    public void mockTestAddOffice() {
        when(contentSrv.save(office)).thenReturn(1L);

        // Ошибка: вызов ctrl.save(office) приводит к
        // IllegalStateException: No current ServletRequestAttributes
        assertEquals(gson.toJson(ctrl.save("office", office).getBody()), gson.toJson(SUCCESS_RESPONSE_BODY));
    }

    // Запрос данных об офисе id
    @Test
    public void mockTestGetOffice() {
        when(contentSrv.get(1L)).thenReturn(office);

        assertEquals(gson.toJson(ctrl.get("office", 1L)), gson.toJson(office));
    }
}