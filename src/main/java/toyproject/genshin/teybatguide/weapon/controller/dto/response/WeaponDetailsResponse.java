package toyproject.genshin.teybatguide.weapon.controller.dto.response;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.weapon.controller.dto.WeaponEffectDto;
import toyproject.genshin.teybatguide.weapon.controller.dto.WeaponHashtagDto;
import toyproject.genshin.teybatguide.weapon.entity.Weapon;
import toyproject.genshin.teybatguide.base.value.Stars;

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
