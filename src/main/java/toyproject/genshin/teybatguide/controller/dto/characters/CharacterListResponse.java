package toyproject.genshin.teybatguide.controller.dto.characters;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.Characters;
import toyproject.genshin.teybatguide.domain.value.Stars;

@Builder
public record CharacterListResponse(String characterId, String characterName, String characterImage, Stars stars) {

    public static CharacterListResponse of(Characters characters) {
        return CharacterListResponse.builder()
                .characterId(characters.getId())
                .characterName(characters.getCharacterName())
                .characterImage(characters.getCharacterImage())
                .stars(characters.getStars())
                .build();
    }

}
