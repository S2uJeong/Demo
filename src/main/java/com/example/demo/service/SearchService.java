package com.example.demo.service;

import com.example.demo.domain.dto.SearchDto;
import com.example.demo.domain.entity.SearchEntity;
import com.example.demo.repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final SearchRepository searchRepository;

    /**
     *     검색한 키워드별로 검색 횟수를 저장하는 메서드
     */
    public void saveKeyword(String keyWord) {
        // 검색키워드가 DB에 있으면 count를 +1 해서 해당 키워드를 가진 ID를 통해 Entity를 업데이트 한다.
        if (searchRepository.existsByKeyWord(keyWord) == true) {
            Long searchId = searchRepository.findIdBykeyWord(keyWord);
            Long count = (searchRepository.findById(searchId).get().getCount()) + 1;
            SearchEntity.updateCount(count, searchId);
        } else { // 검색키워드가 DB에 없으면 Entity를 추가한다.
            SearchEntity searchEntity = SearchEntity.builder()
                    .count(1L)
                    .keyWord(keyWord)
                    .build();
            searchRepository.save(searchEntity);
        }
    }

    /**
     *      인기 순 1-10번째 출력하는 메서드
     */
    public List<SearchDto> getList() {
        List<SearchEntity> top10ByCount = searchRepository.findTop10ByCount();
        List<SearchDto> top10ByCountDto = top10ByCount.stream().map(searchEntity -> new SearchDto())
                .collect(Collectors.toList());


        // 이걸 이제 어케 DTO화 해서 내보낸담?
        // Dto
        //Page<PostReadResponse> postReadResponses = PostReadResponse.listOf(posts);

        return top10ByCountDto;
    }
}
