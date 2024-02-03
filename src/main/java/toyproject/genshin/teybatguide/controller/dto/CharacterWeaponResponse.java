package toyproject.genshin.teybatguide.controller.dto;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.value.SignatureWeapon;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Builder
public record CharacterWeaponResponse(WeaponDto signatureWeapon, List<WeaponDto> weapons) {

    public static CharacterWeaponResponse of(Map<SignatureWeapon, List<WeaponDto>> weaponDtoMap) {
        return CharacterWeaponResponse.builder()
                .signatureWeapon(weaponDtoMap.getOrDefault(SignatureWeapon.TRUE, Collections.emptyList()).stream().findFirst().orElse(null))
                .weapons(weaponDtoMap.get(SignatureWeapon.FALSE))
                .build();
    }
}
