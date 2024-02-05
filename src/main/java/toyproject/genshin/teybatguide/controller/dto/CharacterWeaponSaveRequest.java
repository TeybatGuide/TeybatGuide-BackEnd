package toyproject.genshin.teybatguide.controller.dto;

import toyproject.genshin.teybatguide.domain.value.Recommend;
import toyproject.genshin.teybatguide.domain.value.SignatureWeapon;
import toyproject.genshin.teybatguide.domain.value.WeaponCriteria;

public record CharacterWeaponSaveRequest(String characterId, String weaponId, SignatureWeapon signatureWeapon, WeaponCriteria criteria, Recommend recommend, String text, String version) {
}
