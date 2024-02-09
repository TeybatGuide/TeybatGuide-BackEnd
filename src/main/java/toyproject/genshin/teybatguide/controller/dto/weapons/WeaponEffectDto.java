package toyproject.genshin.teybatguide.controller.dto.weapons;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.Weapon;

@Builder
public record WeaponEffectDto(String weaponEffectName, String weaponEffectExplain) {

    public static WeaponEffectDto of(Weapon weapon) {
        return WeaponEffectDto.builder()
                .weaponEffectName(weapon.getWeaponEffect())
                .weaponEffectExplain(weapon.getWeaponEffectExplanation())
                .build();
    }

}
