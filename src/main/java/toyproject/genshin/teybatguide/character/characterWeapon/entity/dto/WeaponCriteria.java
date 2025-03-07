package toyproject.genshin.teybatguide.character.characterWeapon.entity.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WeaponCriteria {

    PRIORITY("높은 우선도", 1),
    OTHER("기타 무기", 2),
    FOR_NEWBIE("뉴비 추천 무기", 3);

    private final String weaponCriteriaName;
    private final int priorities;

}
