package toyproject.genshin.teybatguide.character.controller.dto.response;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.character.entity.Characters;
import toyproject.genshin.teybatguide.character.specifications.dto.CharacterSpecificationsDto;

public record CharacterDetailsResponse(
        String id, String characterName, String characterImageUrl, CharacterSpecificationsDto specifications
) {

    @Contract("_, _ -> new")
    public static @NotNull CharacterDetailsResponse of(@NotNull Characters ch, CharacterSpecificationsDto spec) {
        return new CharacterDetailsResponse(ch.getId(), ch.getCharacterName(), ch.getCharacterImage(), spec);
    }

}
