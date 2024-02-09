package toyproject.genshin.teybatguide.controller.dto.weapons;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.Weapon;
import toyproject.genshin.teybatguide.domain.value.Stars;

@Builder
public record WeaponDetailsResponse(
        String id,
        String weaponName,
        String weaponImageUrls,
        Stars star,
        WeaponHashtagDto hashtag,
        WeaponEffectDto effects
) {

    public static WeaponDetailsResponse of(Weapon weapon) {
        return WeaponDetailsResponse.builder()
                .id(weapon.getId())
                .weaponName(weapon.getWeaponName())
                .weaponImageUrls(weapon.getWeaponImage())
                .star(weapon.getStars())
                .hashtag(WeaponHashtagDto.of(weapon))
                .effects(WeaponEffectDto.of(weapon))
                .build();
    }

}
