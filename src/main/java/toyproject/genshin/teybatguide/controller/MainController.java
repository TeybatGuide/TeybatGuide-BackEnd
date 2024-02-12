package toyproject.genshin.teybatguide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyproject.genshin.teybatguide.controller.dto.main.CharacterBannerResponse;
import toyproject.genshin.teybatguide.service.MainService;

@RestController
@RequestMapping("/api/main")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/banner")
    public ResponseEntity<CharacterBannerResponse> getCharacterBanners() {
        return ResponseEntity.ok(mainService.searchCharacterBanner());
    }

}
