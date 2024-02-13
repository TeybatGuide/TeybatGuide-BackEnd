package toyproject.genshin.teybatguide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyproject.genshin.teybatguide.controller.dto.base.PageResponseData;
import toyproject.genshin.teybatguide.controller.dto.main.*;
import toyproject.genshin.teybatguide.service.MainService;

import java.util.List;

@RestController
@RequestMapping("/api/main")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/banner/characters")
    public ResponseEntity<CharacterBannerResponse> getCharacterBanners() {
        return ResponseEntity.ok(mainService.searchCharacterBanner());
    }

    @GetMapping("/banner/weapons")
    public ResponseEntity<WeaponBannerResponse> getWeaponBanner() {
        return ResponseEntity.ok(mainService.searchWeaponBanner());
    }

    @GetMapping("/resources")
    public PageResponseData<List<MainResourcesResponse>> getResourcesForMain(@PageableDefault(size = 20) Pageable pageable) {
        return mainService.searchResources(pageable);
    }

    @PostMapping("/banner/character/save")
    public String saveCharacterBanners(@RequestBody CharacterBannerSaveRequest request) {
        return mainService.saveCharacterBanner(request);
    }

    @PostMapping("/banner/weapon/save")
    public String saveWeaponBanners(@RequestBody WeaponBannerSaveRequest request) {
        return mainService.saveWeaponBanner(request);
    }

}
