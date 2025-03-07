package toyproject.genshin.teybatguide.weapon.controller.dto.response;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.weapon.entity.Weapon;
import toyproject.genshin.teybatguide.base.value.Stars;

public record WeaponListResponse(String id, String name, String imageUrls, Stars stars) {
    @Contract("_ -> new")
    public static @NotNull WeaponListResponse of(@NotNull Weapon weapon) {
        return new WeaponListResponse(
                weapon.getId(), weapon.getWeaponName(), weapon.getWeaponImage(), weapon.getStars()
        );
    }

}
