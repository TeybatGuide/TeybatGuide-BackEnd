package toyproject.genshin.teybatguide.controller.dto.characters;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.value.Recommend;
import toyproject.genshin.teybatguide.domain.value.WeaponCriteria;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Builder
public record CharacterWeaponListDto(String weaponCriterion, CharacterWeaponDto bestWeapon, List<CharacterWeaponDto> weapons) {

    public static CharacterWeaponListDto of(WeaponCriteria weaponCriteria, List<CharacterWeaponDto> weapons) {

        Map<Boolean, List<CharacterWeaponDto>> weaponMap = weapons.stream()
                .collect(Collectors.partitioningBy(
                        weaponDto -> weaponDto.recommend().equals(Recommend.BEST.getRecommendName())
                ));

        return CharacterWeaponListDto.builder()
                .weaponCriterion(weaponCriteria.getWeaponCriteriaName())
                .bestWeapon(weaponMap.get(true).stream().findFirst().orElse(null))
                .weapons(weaponMap.get(false))
                .build();
    }

}
