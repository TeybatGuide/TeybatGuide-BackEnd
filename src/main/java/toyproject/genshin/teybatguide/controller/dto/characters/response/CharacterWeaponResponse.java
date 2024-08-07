package toyproject.genshin.teybatguide.controller.dto.characters.response;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.controller.dto.characters.CharacterWeaponListDto;

import java.util.List;

public record CharacterWeaponResponse(List<CharacterWeaponListDto> wrapper, String version) {

    @Contract("_, _ -> new")
    public static @NotNull CharacterWeaponResponse of(List<CharacterWeaponListDto> weapons, String version) {
        return new CharacterWeaponResponse(weapons, version);
    }
}
