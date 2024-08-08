package toyproject.genshin.teybatguide.controller.dto.weapons.response;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.controller.dto.weapons.WeaponAscendDto;
import toyproject.genshin.teybatguide.domain.value.Stars;

import java.util.List;

public record WeaponAscendListResponse(String stars, List<WeaponAscendDto> resources) {

    @Contract("_, _ -> new")
    public static @NotNull WeaponAscendListResponse of(@NotNull Stars stars, List<WeaponAscendDto> weaponAscendDto) {
        return new WeaponAscendListResponse(stars.getStarsName(), weaponAscendDto);
    }

}
