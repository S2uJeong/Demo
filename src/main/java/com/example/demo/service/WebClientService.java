package com.example.demo.service;

import com.example.demo.config.WebClientConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WebClientService {

    @Value("${KAKAO_API_KEY}")
    private Object API_KEY;

    private final WebClientConfig webClientConfig;

    public String getKakao(String query, String sort, int size, int page) {
        WebClient webClient = webClientConfig.getBaseUrl("https://dapi.kakao.com");
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/v2/search/blog")
                        .queryParam("query", query)
                        .queryParam("sort", sort)
                        .queryParam("size", size)
                        .queryParam("page", page)
                        .build())
                .header("Authorization", "KakaoAK" + " " + API_KEY)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(String.class);
                    } else {
                        return clientResponse.createException().flatMap(Mono::error);
                    }
                })
                .block();
    }

}
