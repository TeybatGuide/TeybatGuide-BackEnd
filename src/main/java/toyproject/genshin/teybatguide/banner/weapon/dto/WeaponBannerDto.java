package toyproject.genshin.teybatguide.banner.weapon.dto;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.weapon.entity.Weapon;
import toyproject.genshin.teybatguide.banner.weapon.entity.WeaponBanner;

public record WeaponBannerDto(String id, String name, String image) {

    @Contract("_ -> new")
    public static @NotNull WeaponBannerDto of(@NotNull WeaponBanner weaponBanner) {
        Weapon weapon = weaponBanner.getWeapon();
        return new WeaponBannerDto(weapon.getId(), weapon.getWeaponName(), weapon.getWeaponImage());
    }


}
