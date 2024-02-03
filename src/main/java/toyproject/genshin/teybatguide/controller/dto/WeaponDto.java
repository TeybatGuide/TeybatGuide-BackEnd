package toyproject.genshin.teybatguide.controller.dto;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.CharacterWeapon;
import toyproject.genshin.teybatguide.domain.Weapon;

@Builder
public record WeaponDto(String id, String weaponImage, String weaponName) {

    public static WeaponDto of(CharacterWeapon characterWeapon) {
        Weapon weapon = characterWeapon.getWeapon();

        return WeaponDto.builder()
                .id(weapon.getId())
                .weaponImage(weapon.getWeaponImage())
                .weaponName(weapon.getWeaponName())
                .build();
    }

}
