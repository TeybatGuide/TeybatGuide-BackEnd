package toyproject.genshin.teybatguide.controller.dto.weapons;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.domain.Weapon;

public record WeaponEffectDto(String weaponEffectName, String weaponEffectExplain) {

    @Contract("_ -> new")
    public static @NotNull WeaponEffectDto of(@NotNull Weapon weapon) {
        return new WeaponEffectDto(weapon.getWeaponEffect(), weapon.getWeaponEffectExplanation());
    }

}
