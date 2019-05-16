package com.bellintegrator.zirconium;

import com.bellintegrator.zirconium.view.CountryView;
import com.bellintegrator.zirconium.view.DocumentTypeView;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClassifierControllerTests {

    @LocalServerPort
    private int port;

    private ControllerTester countryTester;
    private ControllerTester docTester;

    private List<CountryView> expectedCountries;
    private List<DocumentTypeView> expectedDocs;

    @Before
    public void init() {
        countryTester = new ControllerTester("countries", port);
        docTester = new ControllerTester("docs", port);

        expectedCountries = new ArrayList<>();
        expectedDocs = new ArrayList<>();

        CountryView country1 = new CountryView();
        country1.setCode("112");
        country1.setName("Республика Беларусь");
        CountryView country2 = new CountryView();
        country2.setCode("156");
        country2.setName("Китайская народная республика");
        CountryView country3 = new CountryView();
        country3.setCode("643");
        country3.setName("Российская Федерация");
        CountryView country4 = new CountryView();
        country4.setCode("688");
        country4.setName("Республика Сербия");

        expectedCountries.add(country1);
        expectedCountries.add(country2);
        expectedCountries.add(country3);
        expectedCountries.add(country4);

        DocumentTypeView doc1 = new DocumentTypeView();
        doc1.setCode("10");
        doc1.setName("Паспорт иностранного гражданина");
        DocumentTypeView doc2 = new DocumentTypeView();
        doc2.setCode("21");
        doc2.setName("Паспорт гражданина Российской Федерации");

        expectedDocs.add(doc1);
        expectedDocs.add(doc2);
    }

    @Test
    public void testListCountries() throws JSONException {
        Assert.assertTrue(countryTester.testGet(expectedCountries));
    }

    @Test
    public void testListDocs() throws JSONException {
        Assert.assertTrue(docTester.testGet(expectedDocs));
    }
}
