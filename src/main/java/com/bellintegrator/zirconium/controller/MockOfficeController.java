package com.bellintegrator.zirconium.controller;

import com.bellintegrator.zirconium.service.content.ContentService;
import com.bellintegrator.zirconium.view.OfficeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер mock-офисов
 */
@RestController
@RequestMapping("/api/mock-office")
public class MockOfficeController extends OfficeController {

    @Autowired
    public MockOfficeController(@Qualifier("mock-office") ContentService<OfficeView> officeService) {
        super(officeService);
    }
}
