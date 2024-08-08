package toyproject.genshin.teybatguide.controller.dto.characters.response;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.domain.Characters;
import toyproject.genshin.teybatguide.domain.value.Stars;

public record CharacterListResponse(String characterId, String characterName, String characterImage, Stars stars) {

    @Contract("_ -> new")
    public static @NotNull CharacterListResponse of(@NotNull Characters ch) {
        return new CharacterListResponse(ch.getId(), ch.getCharacterName(), ch.getCharacterImage(), ch.getStars());
    }

}
