package com.example.reactiveprogrammingdemo.service;

import com.example.reactiveprogrammingdemo.dto.UserBasicInfoDTO;
import com.example.reactiveprogrammingdemo.dto.UserFavoriteDTO;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class UserRepository {

    public Mono<UserBasicInfoDTO> queryUserBasicInfo(Long userId) {
        return selectBasicInfoById(userId);
    }

    public Mono<UserFavoriteDTO> queryUserFavorites(Long userId) {
        return Mono.justOrEmpty(selectFavoritesById(userId));
    }

    private Optional<UserFavoriteDTO> selectFavoritesById(Long userId) {
        return Stream.of(
                        UserFavoriteDTO.builder().favoriteId(1L).userId(1L).userFavoriteName(Arrays.asList("唱", "跳")).build(),
                        UserFavoriteDTO.builder().favoriteId(2L).userId(2L).userFavoriteName(Arrays.asList("rap", "篮球")).build()
                ).filter(userFavoriteDTO -> Objects.equals(userId, userFavoriteDTO.getUserId()))
                .findFirst();
    }

    private Mono<UserBasicInfoDTO> selectBasicInfoById(Long userId) {
        return Stream.of(
                        UserBasicInfoDTO.builder().userId(1L).userName("小明").build(),
                        UserBasicInfoDTO.builder().userId(2L).userName("小红").build()
                ).filter(userBasicInfoDTO -> Objects.equals(userId, userBasicInfoDTO.getUserId()))
                .findFirst()
                .map(Mono::justOrEmpty)
                .orElseGet(Mono::empty);
    }
}
