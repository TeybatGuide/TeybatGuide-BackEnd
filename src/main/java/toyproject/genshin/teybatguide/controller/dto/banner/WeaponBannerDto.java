package toyproject.genshin.teybatguide.controller.dto.banner;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.domain.Weapon;
import toyproject.genshin.teybatguide.domain.WeaponBanner;

public record WeaponBannerDto(String id, String name, String image) {

    @Contract("_ -> new")
    public static @NotNull WeaponBannerDto of(@NotNull WeaponBanner weaponBanner) {
        Weapon weapon = weaponBanner.getWeapon();
        return new WeaponBannerDto(weapon.getId(), weapon.getWeaponName(), weapon.getWeaponImage());
    }


}
