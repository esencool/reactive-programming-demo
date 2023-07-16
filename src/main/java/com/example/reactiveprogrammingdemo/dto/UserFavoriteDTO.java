package com.example.reactiveprogrammingdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFavoriteDTO {
    private Long favoriteId;
    private Long userId;
    private List<String> userFavoriteName;
}
