package toyproject.genshin.teybatguide.banner.character.dto;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.banner.character.entity.CharacterBanner;
import toyproject.genshin.teybatguide.character.entity.Characters;

public record CharacterBannerDto(String id, String name, String image) {

    @Contract("_ -> new")
    public static @NotNull CharacterBannerDto of(@NotNull CharacterBanner banner) {
        Characters characters = banner.getCharacters();
        return new CharacterBannerDto(
                characters.getId(), characters.getCharacterName(), characters.getCharacterImage()
        );
    }

}
