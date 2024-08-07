package toyproject.genshin.teybatguide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import toyproject.genshin.teybatguide.base.ResponseData;
import toyproject.genshin.teybatguide.controller.dto.characters.CharacterListResponse;
import toyproject.genshin.teybatguide.controller.dto.weapons.*;
import toyproject.genshin.teybatguide.base.dto.PageDto;
import toyproject.genshin.teybatguide.base.PageResponseData;
import toyproject.genshin.teybatguide.service.WeaponService;

import java.util.List;

@RestController
@RequestMapping("/api/weapons")
@RequiredArgsConstructor
public class WeaponController {

    private final WeaponService weaponService;

    @GetMapping
    public PageResponseData<List<WeaponListResponse>> getWeaponList(
            @PageableDefault(size = 20) Pageable pageable,
            @ModelAttribute WeaponListRequest request
    ) {
        Page<WeaponListResponse> response = weaponService.getWeaponListResponse(pageable, request);
        return PageResponseData.of(response.toList(), PageDto.of(response));
    }

    @GetMapping("/{weapon_id}")
    public ResponseData<WeaponDetailsResponse> getWeaponDetails(@PathVariable(name = "weapon_id") String weaponId) {
        return ResponseData.of(weaponService.searchForBasicWeaponsInformation(weaponId));
    }

    @GetMapping("/{weapon_id}/characters")
    public ResponseData<List<CharacterListResponse>> getWeaponRecommendedCharacters(@PathVariable(name = "weapon_id") String weaponId) {
        return ResponseData.of(weaponService.searchForRecommendedCharacters(weaponId));
    }

    @GetMapping("/{weapon_id}/resources")
    public ResponseData<List<WeaponAscendListResponse>> getWeaponAscendResources(@PathVariable(name = "weapon_id") String weaponId) {
        return ResponseData.of(weaponService.searchForAscendResources(weaponId));
    }

    @PostMapping("/save/ascend")
    public ResponseData<WeaponAscendDto> saveWeaponAscend(@RequestBody WeaponAscendSaveRequest request) {
        return ResponseData.of(weaponService.saveWeaponAscend(request));
    }

}
