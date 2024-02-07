package toyproject.genshin.teybatguide.controller.dto.characters;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.CharacterWeapon;
import toyproject.genshin.teybatguide.domain.Weapon;

@Builder
public record CharacterWeaponDto(String id, String weaponImage, String weaponName, String recommend, String comments) {

    public static CharacterWeaponDto of(CharacterWeapon characterWeapon) {
        Weapon weapon = characterWeapon.getWeapon();

        return CharacterWeaponDto.builder()
                .id(weapon.getId())
                .weaponImage(weapon.getWeaponImage())
                .weaponName(weapon.getWeaponName())
                .recommend(characterWeapon.getRecommend().getRecommendName())
                .comments(characterWeapon.getComments())
                .build();
    }

}
