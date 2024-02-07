package toyproject.genshin.teybatguide.controller.dto.characters;

import lombok.Builder;

import java.util.List;

@Builder
public record CharacterWeaponResponse(List<CharacterWeaponListDto> wrapper, String version) {

    public static CharacterWeaponResponse of(List<CharacterWeaponListDto> weapons, String version) {
        return CharacterWeaponResponse.builder()
                .wrapper(weapons)
                .version(version)
                .build();
    }
}
