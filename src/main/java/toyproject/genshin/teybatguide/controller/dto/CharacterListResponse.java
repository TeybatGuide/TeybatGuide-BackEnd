package toyproject.genshin.teybatguide.controller.dto;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.Characters;

@Builder
public record CharacterListResponse(String characterId, String characterName, String characterImage) {

    public static CharacterListResponse of(Characters characters) {
        return CharacterListResponse.builder()
                .characterId(characters.getId())
                .characterName(characters.getCharacterName())
                .characterImage(characters.getCharacterImage())
                .build();
    }

}
