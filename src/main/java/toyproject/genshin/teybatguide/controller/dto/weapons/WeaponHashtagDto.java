package toyproject.genshin.teybatguide.controller.dto.weapons;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.Weapon;
import toyproject.genshin.teybatguide.domain.value.SignatureWeapon;

@Builder
public record WeaponHashtagDto(String weaponOption, String weaponType, String characterName, String weaponSource, String weaponSourceExplanation) {

    public static WeaponHashtagDto of(Weapon weapon) {
        String characterName = weapon.getCharacterWeapons().stream()
                .filter(characterWeapon -> characterWeapon.getSignatureWeapon().equals(SignatureWeapon.TRUE))
                .findFirst()
                .map(characterWeapon -> characterWeapon.getCharacters().getCharacterName())
                .orElse(null);

        return WeaponHashtagDto.builder()
                .weaponOption(weapon.getWeaponOption().getWeaponOptionsName())
                .weaponType(weapon.getWeaponType().getWeaponTypeName())
                .characterName(characterName)
                .weaponSource(weapon.getWeaponSource().getWeaponSourceName())
                .weaponSourceExplanation(weapon.getWeaponSourceExplanation())
                .build();
    }

}
