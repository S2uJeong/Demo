package com.example.demo.domain.dto;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchDto {

    private Long id;

    private String keyWord;
    private Long count;
}
