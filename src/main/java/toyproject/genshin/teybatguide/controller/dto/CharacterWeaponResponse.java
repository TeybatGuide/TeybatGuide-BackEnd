package toyproject.genshin.teybatguide.controller.dto;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.value.SignatureWeapon;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Builder
public record CharacterWeaponResponse(List<CharacterWeaponListDto> wrapper, String version) {

    public static CharacterWeaponResponse of(List<CharacterWeaponListDto> weapons, String version) {
        return CharacterWeaponResponse.builder()
                .wrapper(weapons)
                .version(version)
                .build();
    }
}
