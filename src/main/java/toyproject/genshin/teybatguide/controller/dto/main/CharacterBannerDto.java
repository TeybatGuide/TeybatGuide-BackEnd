package toyproject.genshin.teybatguide.controller.dto.main;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.CharacterBanner;
import toyproject.genshin.teybatguide.domain.Characters;

@Builder
public record CharacterBannerDto(String id, String name) {

    public static CharacterBannerDto of(CharacterBanner banner) {
        Characters characters = banner.getCharacters();
        return CharacterBannerDto.builder()
                .id(characters.getId())
                .name(characters.getCharacterName())
                .build();
    }

}
