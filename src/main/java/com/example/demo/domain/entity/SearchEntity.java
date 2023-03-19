package com.example.demo.domain.entity;

import com.example.demo.domain.dto.SearchDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "search_id")
    private Long id;

    private String keyWord;
    private Long count;

    public SearchDto toDto() {
        return SearchDto.builder()
                .id(id)
                .keyWord(keyWord)
                .count(count)
                .build();
    }


    public static SearchEntity updateCount(Long count, Long searchId) {
        return SearchEntity.builder()
                .id(searchId)
                .count(count)
                .build();
    }

}
