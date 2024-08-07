package toyproject.genshin.teybatguide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyproject.genshin.teybatguide.controller.dto.MainCharacterResourcesResponse;
import toyproject.genshin.teybatguide.base.ResponseData;
import toyproject.genshin.teybatguide.controller.dto.main.*;
import toyproject.genshin.teybatguide.domain.value.BannerType;
import toyproject.genshin.teybatguide.service.BannerService;

import java.util.List;

@RestController
@RequestMapping("/api/banner")
@RequiredArgsConstructor
public class BannerController {

    private final BannerService bannerService;

    @GetMapping("/characters")
    public ResponseEntity<CharacterBannerResponse> getCharacterBanner(@RequestParam(name = "type", defaultValue = "CHARACTER") BannerType bannerType) {
        return ResponseEntity.ok(bannerService.searchCharacterBanner(bannerType));
    }

    @GetMapping("/weapons")
    public ResponseEntity<WeaponBannerResponse> getWeaponBanner(@RequestParam(name = "type", defaultValue = "WEAPON") BannerType bannerType) {
        return ResponseEntity.ok(bannerService.searchWeaponBanner(bannerType));
    }

    @GetMapping("/events")
    public ResponseEntity<List<BannerEventsDto>> getEvents() {
        return ResponseEntity.ok(bannerService.searchEvents());
    }

    @GetMapping("/characters/resources")
    public ResponseEntity<List<MainCharacterResourcesResponse>> getResourcesForBannerCharacter() {
        return ResponseEntity.ok(bannerService.searchBannerCharacterResources());
    }

    @PostMapping("/character/save")
    public ResponseData<CharacterBannerDto> saveCharacterBanners(@RequestBody CharacterBannerSaveRequest request) {
        return ResponseData.of(bannerService.saveCharacterBanner(request));
    }

    @PostMapping("/weapon/save")
    public ResponseData<WeaponBannerDto> saveWeaponBanners(@RequestBody WeaponBannerSaveRequest request) {
        return ResponseData.of(bannerService.saveWeaponBanner(request));
    }

    @PostMapping("/events/save")
    public ResponseData<BannerEventsDto> saveEventBanner(@RequestBody BannerEventsDto request) {
        return ResponseData.of("event 저장이 완료되었습니다.", bannerService.saveEvents(request));
    }

}
