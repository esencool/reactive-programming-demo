package com.example.reactiveprogrammingdemo.service;

import com.example.reactiveprogrammingdemo.dto.UserBasicInfoDTO;
import com.example.reactiveprogrammingdemo.dto.UserFavoriteDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@Slf4j
public class WebClientService {
    private final WebClient webClient = WebClient.create("http://localhost:8080");

    public Mono<UserBasicInfoDTO> getUserInfo(Long userId) {
        log.info("webClient execute getUserInfo start");
        return webClient
                .get()
                .uri("/query-user-basic-info", uriBuilder -> uriBuilder.queryParam("userId", userId).build())
                .retrieve()
                .bodyToMono(UserBasicInfoDTO.class);
    }

    public Mono<UserFavoriteDTO> getUserFavorites(Long userId) {
        log.info("webClient execute getUserFavorites start");
        return webClient
                .get()
                .uri("/query-user-favorites", uriBuilder -> uriBuilder.queryParam("userId", userId).build())
                .retrieve()
                .bodyToMono(UserFavoriteDTO.class)
                .doOnNext(result -> log.info("webClient execute getUserFavorites end"));
    }
}
