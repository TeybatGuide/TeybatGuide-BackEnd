package toyproject.genshin.teybatguide.domain.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Options {

    ATK("공격력"),
    DMG_BONUS("피해 증가"),
    HP("HP"),
    SHIELD_STRENGTH("보호막 강화"),
    DEF("방어력"),
    CRIT_RATE("치명타 확률"),
    ELEMENTAL_MASTERY("원소 마스터리"),
    ENERGY_RECHARGE("원소 충전 효율"),
    CD_REDUCTION("재사용 대기시간 감소"),
    HEALING_BONUS("치유 보너스"),
    ELEMENTAL_EFFECTS("원소 부착"),
    ELEMENTAL_RES("원소 내성");

    private final String optionsName;
}
