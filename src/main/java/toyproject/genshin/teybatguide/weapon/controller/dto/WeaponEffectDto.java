package toyproject.genshin.teybatguide.weapon.controller.dto;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.weapon.entity.Weapon;

public record WeaponEffectDto(String weaponEffectName, String weaponEffectExplain) {

    @Contract("_ -> new")
    public static @NotNull WeaponEffectDto of(@NotNull Weapon weapon) {
        return new WeaponEffectDto(weapon.getWeaponEffect(), weapon.getWeaponEffectExplanation());
    }

}
