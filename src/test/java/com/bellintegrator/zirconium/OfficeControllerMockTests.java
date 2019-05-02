package com.bellintegrator.zirconium;

import com.bellintegrator.zirconium.controller.OfficeController;
import com.bellintegrator.zirconium.service.content.ContentService;
import com.bellintegrator.zirconium.view.OfficeView;
import com.google.gson.Gson;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static com.bellintegrator.zirconium.OfficeControllerTests.*;
import static com.bellintegrator.zirconium.controller.SuccessResponseBody.*;
import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OfficeControllerMockTests {

    private Gson gson = new Gson();

    @Mock
    private ContentService<OfficeView> officeService;

    @InjectMocks
    private OfficeController ctrl;

    // Сохранение офиса
    public void mockTestAddOffice() {
        when(officeService.save(office)).thenReturn(1L);

        // Ошибка: вызов ctrl.save(office) приводит к
        // IllegalStateException: No current ServletRequestAttributes
        assertEquals(gson.toJson(ctrl.save(office).getBody()), gson.toJson(SUCCESS_RESPONSE_BODY));
    }

    // Запрос данных об офисе id
    @Test
    public void mockTestGetOffice() {
        when(officeService.get(1L)).thenReturn(office);

        assertEquals(gson.toJson(ctrl.get(1L)), gson.toJson(office));
    }
}