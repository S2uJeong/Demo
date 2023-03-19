package com.example.demo.controller;
import com.example.demo.domain.dto.SearchDto;
import com.example.demo.service.SearchService;
import com.example.demo.service.WebClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class KaKaoController {

    private final WebClientService webClientService;
    private final SearchService searchService;

    @GetMapping("/search/{query}/{sort}/{size}/{page}")
    public String search(@PathVariable(name = "query") String query,
                         @PathVariable(name = "sort") String sort,
                         @PathVariable(name = "size") int size,
                         @PathVariable(name = "page") int page) {
        return webClientService.getKakao(query,sort,size,page);
    }

    @GetMapping("/search-popularity")
    public List<SearchDto> getPopularityList() {
        return searchService.getList();
    }

}
