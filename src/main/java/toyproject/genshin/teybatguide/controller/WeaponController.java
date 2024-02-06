package toyproject.genshin.teybatguide.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import toyproject.genshin.teybatguide.controller.dto.WeaponListResponse;
import toyproject.genshin.teybatguide.service.WeaponService;

import java.util.List;

@RestController
@RequestMapping("/api/weapons")
@RequiredArgsConstructor
public class WeaponController {

    private final WeaponService weaponService;

    @GetMapping
    public ResponseEntity<List<WeaponListResponse>> getWeaponList(@RequestParam(name = "filter_on", required = false) List<String> filters) {
        return ResponseEntity.ok(weaponService.getWeaponListResponse());
    }

}
