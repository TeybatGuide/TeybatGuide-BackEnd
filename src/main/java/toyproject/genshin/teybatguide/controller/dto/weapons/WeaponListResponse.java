package toyproject.genshin.teybatguide.controller.dto.weapons;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.Weapon;
import toyproject.genshin.teybatguide.domain.value.Stars;

@Builder
public record WeaponListResponse(String id, String name, String imageUrls, Stars stars) {

    public static WeaponListResponse of(Weapon weapon) {
        return WeaponListResponse.builder()
                .id(weapon.getId())
                .name(weapon.getWeaponName())
                .imageUrls(weapon.getWeaponImage())
                .stars(weapon.getStars())
                .build();
    }

}
