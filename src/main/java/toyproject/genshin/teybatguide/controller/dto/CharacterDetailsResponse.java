package toyproject.genshin.teybatguide.controller.dto;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.CharacterSpecifications;
import toyproject.genshin.teybatguide.domain.Characters;

@Builder
public record CharacterDetailsResponse(String id, String characterName, String characterImageUrl, CharacterSpecificationsDto specifications) {

    public static CharacterDetailsResponse of(Characters characters, CharacterSpecificationsDto specifications) {
        return CharacterDetailsResponse.builder()
                .id(characters.getId())
                .characterName(characters.getCharacterName())
                .characterImageUrl(characters.getCharacterImage2())
                .specifications(specifications)
                .build();
    }

}
