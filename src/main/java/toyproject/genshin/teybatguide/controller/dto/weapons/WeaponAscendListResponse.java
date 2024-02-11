package toyproject.genshin.teybatguide.controller.dto.weapons;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.value.Stars;

import java.util.List;

@Builder
public record WeaponAscendListResponse(String stars, List<WeaponAscendDto> resources) {

    public static WeaponAscendListResponse of(Stars stars, List<WeaponAscendDto> weaponAscendDto) {
        return WeaponAscendListResponse.builder()
                .stars(stars.getStarsName())
                .resources(weaponAscendDto)
                .build();
    }

}
