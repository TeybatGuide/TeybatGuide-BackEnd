package toyproject.genshin.teybatguide.controller.dto.characters.response;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.controller.dto.characters.CharacterSpecificationsDto;
import toyproject.genshin.teybatguide.domain.Characters;

public record CharacterDetailsResponse(
        String id, String characterName, String characterImageUrl, CharacterSpecificationsDto specifications
) {

    @Contract("_, _ -> new")
    public static @NotNull CharacterDetailsResponse of(@NotNull Characters ch, CharacterSpecificationsDto spec) {
        return new CharacterDetailsResponse(ch.getId(), ch.getCharacterName(), ch.getCharacterImage(), spec);
    }

}
