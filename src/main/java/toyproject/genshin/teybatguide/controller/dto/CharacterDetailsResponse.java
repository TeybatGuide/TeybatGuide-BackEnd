package toyproject.genshin.teybatguide.controller.dto;

import lombok.Builder;
import org.springframework.core.io.FileSystemResource;
import toyproject.genshin.teybatguide.domain.Characters;

@Builder
public record CharacterDetailsResponse(String id, String characterName, FileSystemResource characterImage) {

    public static CharacterDetailsResponse of(Characters characters, FileSystemResource characterImage) {
        return CharacterDetailsResponse.builder()
                .id(characters.getId())
                .characterName(characters.getCharacterName())
                .characterImage(characterImage)
                .build();
    }

}
