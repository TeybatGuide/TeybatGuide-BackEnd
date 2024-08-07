package toyproject.genshin.teybatguide.controller.dto.characters;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.domain.CharacterWeapon;
import toyproject.genshin.teybatguide.domain.Weapon;

public record CharacterWeaponDto(String id, String weaponImage, String weaponName, String recommend, String comments) {

    @Contract("_ -> new")
    public static @NotNull CharacterWeaponDto of(@NotNull CharacterWeapon characterWeapon) {
        Weapon weapon = characterWeapon.getWeapon();
        return new CharacterWeaponDto(
                weapon.getId(),
                weapon.getWeaponImage(),
                weapon.getWeaponName(),
                characterWeapon.getRecommend().getRecommendName(),
                characterWeapon.getComments()
        );
    }

}
