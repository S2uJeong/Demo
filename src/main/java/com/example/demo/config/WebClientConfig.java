package com.example.demo.config;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
@RequiredArgsConstructor
public class WebClientConfig {

    public static WebClient getBaseUrl(final String uri) {
        return WebClient.builder()
                .baseUrl(uri)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build()
                .mutate()
                .build();
    }
}

