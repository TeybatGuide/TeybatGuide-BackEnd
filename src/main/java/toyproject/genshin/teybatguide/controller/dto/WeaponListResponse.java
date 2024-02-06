package toyproject.genshin.teybatguide.controller.dto;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.Weapon;

@Builder
public record WeaponListResponse(String id, String name, String imageUrls) {

    public static WeaponListResponse of(Weapon weapon) {
        return WeaponListResponse.builder()
                .id(weapon.getId())
                .name(weapon.getWeaponName())
                .imageUrls(weapon.getWeaponImage())
                .build();
    }

}
