package toyproject.genshin.teybatguide.controller.dto.weapons;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import toyproject.genshin.teybatguide.domain.Resources;
import toyproject.genshin.teybatguide.domain.WeaponAscend;

@Slf4j
@Builder
public record WeaponAscendDto(String id, String name, int count) {

    public static WeaponAscendDto of(WeaponAscend weaponAscend) {
        Resources resources = weaponAscend.getResources();

        log.info(resources.getResourcesName());

        return WeaponAscendDto.builder()
                .id(resources.getId())
                .name(resources.getResourcesName())
                .count(weaponAscend.getWeaponAscendCount())
                .build();
    }

}
