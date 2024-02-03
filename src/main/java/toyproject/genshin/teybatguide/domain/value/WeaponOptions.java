package toyproject.genshin.teybatguide.domain.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WeaponOptions {

    ATK_PERCENTAGE("공격력 백분율"),
    PHYSICAL_DMG_BONUS("물리 피해 보너스"),
    DEF_PERCENTAGE("방어력 백분율"),
    CRIT_RATE("치명타 확률"),
    CRIT_DMG("치명타 피해"),
    ELEMENTAL_MASTERY("원소 마스터리"),
    ENERGY_RECHARGE("원소 충전 효율"),
    HP_PERCENTAGE("HP 백분율");

    private final String WeaponOptionsName;

}
