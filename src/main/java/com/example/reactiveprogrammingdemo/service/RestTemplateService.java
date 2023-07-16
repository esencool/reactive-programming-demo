package com.example.reactiveprogrammingdemo.service;

import com.example.reactiveprogrammingdemo.dto.UserBasicInfoDTO;
import com.example.reactiveprogrammingdemo.dto.UserFavoriteDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class RestTemplateService {
    private final String basicUrl = "http://localhost:8080/";

    private final RestTemplate restTemplate = new RestTemplate();

    public UserBasicInfoDTO getUserInfo(Long userId) {
        log.info("restTemplate execute getUserInfo start");
        return restTemplate.getForObject(basicUrl + "query-basic-info", UserBasicInfoDTO.class, userId);
    }

    @SuppressWarnings("unchecked")
    public List<UserFavoriteDTO> getUserFavorites(Long userId) {
        log.info("restTemplate execute getUserFavorites start");
        return restTemplate.getForObject(basicUrl + "query-user-favorites", List.class, userId);
    }
}
