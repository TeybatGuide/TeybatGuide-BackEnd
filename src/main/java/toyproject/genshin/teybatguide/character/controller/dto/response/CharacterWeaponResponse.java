package toyproject.genshin.teybatguide.character.controller.dto.response;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.character.characterWeapon.dto.CharacterWeaponListDto;

import java.util.List;

public record CharacterWeaponResponse(List<CharacterWeaponListDto> wrapper, String version) {

    @Contract("_, _ -> new")
    public static @NotNull CharacterWeaponResponse of(List<CharacterWeaponListDto> weapons, String version) {
        return new CharacterWeaponResponse(weapons, version);
    }
}
