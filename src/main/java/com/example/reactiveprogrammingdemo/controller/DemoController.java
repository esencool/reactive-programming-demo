package com.example.reactiveprogrammingdemo.controller;

import com.example.reactiveprogrammingdemo.dto.UserBasicInfoDTO;
import com.example.reactiveprogrammingdemo.dto.UserFavoriteDTO;
import com.example.reactiveprogrammingdemo.service.RestTemplateService;
import com.example.reactiveprogrammingdemo.service.UserRepository;
import com.example.reactiveprogrammingdemo.service.WebClientService;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

@RestController
@Slf4j
public class DemoController {
    @Resource
    private UserRepository userRepository;
    @Resource
    private WebClientService webClientService;
    @Resource
    private RestTemplateService restTemplateService;

    @GetMapping("/query-user-basic-info")
    @SneakyThrows(Exception.class)
    public Mono<UserBasicInfoDTO> queryUserBasicInfo(@RequestParam("userId") Long userId) {
        Thread.sleep(1000L);
        return userRepository.queryUserBasicInfo(userId);
    }

    @GetMapping("/query-user-favorites")
    @SneakyThrows
    public Mono<UserFavoriteDTO> queryUserFavorites(@RequestParam("userId") Long userId) {
        Thread.sleep(2000L);
        return userRepository.queryUserFavorites(userId);
    }


    @GetMapping("/test")
    public Mono<Boolean> test(@RequestParam Long userId) {
        return Flux.merge(
                webClientService.getUserInfo(userId)
                        .map(userBasicInfoDTO -> Objects.equals("小明", userBasicInfoDTO.getUserName()))
                        .doOnNext(result -> log.info("user basic info check result ={}", result)),
                webClientService.getUserFavorites(userId)
                        .map(userFavoriteDTOS -> userFavoriteDTOS.getUserFavoriteName().contains("rap"))
                        .doOnNext(result -> log.info("user favorites check result ={}", result))
            ).any(t -> t);

    }
}
