package toyproject.genshin.teybatguide.controller.dto.banner;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.domain.CharacterBanner;
import toyproject.genshin.teybatguide.domain.Characters;

public record CharacterBannerDto(String id, String name, String image) {

    @Contract("_ -> new")
    public static @NotNull CharacterBannerDto of(@NotNull CharacterBanner banner) {
        Characters characters = banner.getCharacters();
        return new CharacterBannerDto(
                characters.getId(), characters.getCharacterName(), characters.getCharacterImage()
        );
    }

}
