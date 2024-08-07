package toyproject.genshin.teybatguide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toyproject.genshin.teybatguide.controller.dto.banner.response.CharacterResourcesResponse;
import toyproject.genshin.teybatguide.base.ResponseData;
import toyproject.genshin.teybatguide.controller.dto.banner.*;
import toyproject.genshin.teybatguide.controller.dto.banner.request.CharacterBannerSaveRequest;
import toyproject.genshin.teybatguide.controller.dto.banner.request.WeaponBannerSaveRequest;
import toyproject.genshin.teybatguide.controller.dto.banner.response.CharacterBannerResponse;
import toyproject.genshin.teybatguide.controller.dto.banner.response.WeaponBannerResponse;
import toyproject.genshin.teybatguide.domain.value.BannerType;
import toyproject.genshin.teybatguide.service.BannerService;

import java.util.List;

@RestController
@RequestMapping("/api/banner")
@RequiredArgsConstructor
public class BannerController {

    private final BannerService bannerService;

    @GetMapping("/characters")
    public ResponseData<CharacterBannerResponse> getCharacterBanner(
            @RequestParam(name = "type", defaultValue = "CHARACTER") BannerType bannerType
    ) {
        return ResponseData.of(bannerService.searchCharacterBanner(bannerType));
    }

    @GetMapping("/weapons")
    public ResponseData<WeaponBannerResponse> getWeaponBanner(
            @RequestParam(name = "type", defaultValue = "WEAPON") BannerType bannerType
    ) {
        return ResponseData.of(bannerService.searchWeaponBanner(bannerType));
    }

    @GetMapping("/events")
    public ResponseData<List<BannerEventsDto>> getEvents() {
        return ResponseData.of(bannerService.searchEvents());
    }

    @GetMapping("/characters/resources")
    public ResponseData<List<CharacterResourcesResponse>> getResourcesForBannerCharacter() {
        return ResponseData.of(bannerService.searchBannerCharacterResources());
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
