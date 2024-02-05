package toyproject.genshin.teybatguide.domain.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WeaponCriteria {

    PRIORITY("높은 우선도"), OTHER("기타 무기"), FOR_NEWBIE("뉴비 추천 무기");

    private final String weaponCriteriaName;

}
