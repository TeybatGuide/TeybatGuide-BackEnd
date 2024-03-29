package toyproject.genshin.teybatguide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyproject.genshin.teybatguide.controller.dto.MainCharacterResourcesResponse;
import toyproject.genshin.teybatguide.controller.dto.base.PageResponseData;
import toyproject.genshin.teybatguide.controller.dto.main.*;
import toyproject.genshin.teybatguide.domain.value.BannerType;
import toyproject.genshin.teybatguide.service.MainService;

import java.util.List;

@RestController
@RequestMapping("/api/main")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/banner/characters")
    public ResponseEntity<CharacterBannerResponse> getCharacterBanner(@RequestParam(name = "type", defaultValue = "CHARACTER") BannerType bannerType) {
        return ResponseEntity.ok(mainService.searchCharacterBanner(bannerType));
    }

    @GetMapping("/banner/weapons")
    public ResponseEntity<WeaponBannerResponse> getWeaponBanner(@RequestParam(name = "type", defaultValue = "WEAPON") BannerType bannerType) {
        return ResponseEntity.ok(mainService.searchWeaponBanner(bannerType));
    }

    @GetMapping("/banner/events")
    public ResponseEntity<List<BannerEventsDto>> getEvents() {
        return ResponseEntity.ok(mainService.searchEvents());
    }

    @GetMapping("/resources")
    public PageResponseData<List<MainResourcesResponse>> getResourcesForMain(@PageableDefault(size = 20) Pageable pageable) {
        return mainService.searchResources(pageable);
    }

    @GetMapping("/banner/characters/resources")
    public ResponseEntity<List<MainCharacterResourcesResponse>> getResourcesForBannerCharacter() {
        return ResponseEntity.ok(mainService.searchBannerCharacterResources());
    }

    @PostMapping("/banner/character/save")
    public String saveCharacterBanners(@RequestBody CharacterBannerSaveRequest request) {
        return mainService.saveCharacterBanner(request);
    }

    @PostMapping("/banner/weapon/save")
    public String saveWeaponBanners(@RequestBody WeaponBannerSaveRequest request) {
        return mainService.saveWeaponBanner(request);
    }

    @PostMapping("/banner/events/save")
    public String saveEventBanner(@RequestBody BannerEventsDto request) {
        return mainService.saveEvents(request);
    }

}
