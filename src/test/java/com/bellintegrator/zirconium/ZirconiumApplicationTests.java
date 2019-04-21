package com.bellintegrator.zirconium;

import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
@EnableAutoConfiguration
public class ZirconiumApplicationTests {

    @Bean
    public TestRestTemplate testRestTemplate() {
        return new TestRestTemplate();
    }

    @Bean
    public HttpHeaders httpHeaders() {
        return new HttpHeaders();
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

}