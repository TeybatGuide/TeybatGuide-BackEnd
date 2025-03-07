package toyproject.genshin.teybatguide.character.controller.dto.request;

import toyproject.genshin.teybatguide.character.entity.value.Recommend;
import toyproject.genshin.teybatguide.character.resource.entity.value.SignatureWeapon;
import toyproject.genshin.teybatguide.character.characterWeapon.entity.dto.WeaponCriteria;

public record CharacterWeaponSaveRequest(
        String characterId,
        String weaponId,
        SignatureWeapon signatureWeapon,
        WeaponCriteria criteria,
        Recommend recommend,
        String text,
        String version
) { }
