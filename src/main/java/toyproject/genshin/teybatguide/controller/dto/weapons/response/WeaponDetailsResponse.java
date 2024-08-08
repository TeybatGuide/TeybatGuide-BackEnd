package toyproject.genshin.teybatguide.controller.dto.weapons.response;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.controller.dto.weapons.WeaponEffectDto;
import toyproject.genshin.teybatguide.controller.dto.weapons.WeaponHashtagDto;
import toyproject.genshin.teybatguide.domain.Weapon;
import toyproject.genshin.teybatguide.domain.value.Stars;

public record WeaponDetailsResponse(
        String id,
        String weaponName,
        String weaponImageUrls,
        Stars star,
        WeaponHashtagDto hashtag,
        WeaponEffectDto effects
) {
    @Contract("_ -> new")
    public static @NotNull WeaponDetailsResponse of(@NotNull Weapon weapon) {
        return new WeaponDetailsResponse(
                weapon.getId(),
                weapon.getWeaponName(),
                weapon.getWeaponImage(),
                weapon.getStars(),
                WeaponHashtagDto.of(weapon),
                WeaponEffectDto.of(weapon)
        );
    }
}
