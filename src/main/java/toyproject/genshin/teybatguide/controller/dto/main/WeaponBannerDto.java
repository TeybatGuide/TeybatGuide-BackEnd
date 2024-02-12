package toyproject.genshin.teybatguide.controller.dto.main;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.Weapon;
import toyproject.genshin.teybatguide.domain.WeaponBanner;

@Builder
public record WeaponBannerDto(String id, String name) {

    public static WeaponBannerDto of(WeaponBanner weaponBanner) {
        Weapon weapon = weaponBanner.getWeapon();

        return WeaponBannerDto.builder()
                .id(weapon.getId())
                .name(weapon.getWeaponName())
                .build();
    }


}
