package toyproject.genshin.teybatguide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toyproject.genshin.teybatguide.controller.dto.characters.CharacterListResponse;
import toyproject.genshin.teybatguide.controller.dto.weapons.WeaponDetailsResponse;
import toyproject.genshin.teybatguide.controller.dto.weapons.WeaponListRequest;
import toyproject.genshin.teybatguide.controller.dto.weapons.WeaponListResponse;
import toyproject.genshin.teybatguide.controller.dto.base.PageDto;
import toyproject.genshin.teybatguide.controller.dto.base.PageResponseData;
import toyproject.genshin.teybatguide.service.WeaponService;

import java.util.List;

@RestController
@RequestMapping("/api/weapons")
@RequiredArgsConstructor
public class WeaponController {

    private final WeaponService weaponService;

    @PostMapping
    public PageResponseData<List<WeaponListResponse>> getWeaponList(
            @PageableDefault(size = 20) Pageable pageable,
            @RequestBody WeaponListRequest request
    ) {
        Page<WeaponListResponse> response = weaponService.getWeaponListResponse(pageable, request);
        return PageResponseData.of(response.toList(), PageDto.of(response));
    }

    @GetMapping("/{weapon_id}")
    public ResponseEntity<WeaponDetailsResponse> getWeaponDetails(@PathVariable(name = "weapon_id") String weaponId) {
        return ResponseEntity.ok(weaponService.searchForBasicWeaponsInformation(weaponId));
    }

    @GetMapping("/{weapon_id}/characters")
    public ResponseEntity<List<CharacterListResponse>> getWeaponRecommendedCharacters(@PathVariable(name = "weapon_id") String weaponId) {
        return ResponseEntity.ok(weaponService.searchForRecommendedCharacters(weaponId));
    }

}
