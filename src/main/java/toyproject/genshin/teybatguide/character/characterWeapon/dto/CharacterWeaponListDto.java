package toyproject.genshin.teybatguide.character.characterWeapon.dto;

import lombok.Builder;
import toyproject.genshin.teybatguide.character.entity.value.Recommend;
import toyproject.genshin.teybatguide.character.characterWeapon.entity.dto.WeaponCriteria;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Builder
public record CharacterWeaponListDto(String criterion, CharacterWeaponDto bestWeapon, List<CharacterWeaponDto> weapons) {

    public static CharacterWeaponListDto of(WeaponCriteria weaponCriteria, List<CharacterWeaponDto> weapons) {

        Map<Boolean, List<CharacterWeaponDto>> weaponMap = weapons.stream()
                .collect(Collectors.partitioningBy(
                        weaponDto -> weaponDto.recommend().equals(Recommend.BEST.getRecommendName())
                ));

        return CharacterWeaponListDto.builder()
                .criterion(weaponCriteria.getWeaponCriteriaName())
                .bestWeapon(weaponMap.get(true).stream().findFirst().orElse(null))
                .weapons(weaponMap.get(false))
                .build();
    }

}
