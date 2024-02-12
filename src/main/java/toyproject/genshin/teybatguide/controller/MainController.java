package toyproject.genshin.teybatguide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyproject.genshin.teybatguide.controller.dto.main.CharacterBannerResponse;
import toyproject.genshin.teybatguide.controller.dto.main.CharacterBannerSaveRequest;
import toyproject.genshin.teybatguide.controller.dto.main.WeaponBannerResponse;
import toyproject.genshin.teybatguide.controller.dto.main.WeaponBannerSaveRequest;
import toyproject.genshin.teybatguide.service.MainService;

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

    @PostMapping("/banner/character/save")
    public String saveCharacterBanners(@RequestBody CharacterBannerSaveRequest request) {
        return mainService.saveCharacterBanner(request);
    }

    @PostMapping("/banner/weapon/save")
    public String saveWeaponBanners(@RequestBody WeaponBannerSaveRequest request) {
        return mainService.saveWeaponBanner(request);
    }

}
