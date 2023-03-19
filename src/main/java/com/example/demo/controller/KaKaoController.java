package com.example.demo.controller;
import com.example.demo.service.WebClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class KaKaoController {

    private final WebClientService webClientService;

    @GetMapping("/{query}/{sort}/{size}/{page}")
    public String search(@PathVariable(name = "query") String query,
                         @PathVariable(name = "sort") String sort,
                         @PathVariable(name = "size") int size,
                         @PathVariable(name = "page") int page) {
        return webClientService.getKakao(query,sort,size,page);
    }

}
