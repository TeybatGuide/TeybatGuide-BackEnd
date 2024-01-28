package toyproject.genshin.teybatguide.controller.dto;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.Characters;

@Builder
public record CharacterDetailsResponse(String id, String characterName, String characterImageUrl) {

    public static CharacterDetailsResponse of(Characters characters) {
        return CharacterDetailsResponse.builder()
                .id(characters.getId())
                .characterName(characters.getCharacterName())
                .characterImageUrl(characters.getCharacterImage2())
                .build();
    }

}
