package toyproject.genshin.teybatguide.controller.dto.weapons.response;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.domain.Weapon;
import toyproject.genshin.teybatguide.domain.value.Stars;

public record WeaponListResponse(String id, String name, String imageUrls, Stars stars) {
    @Contract("_ -> new")
    public static @NotNull WeaponListResponse of(@NotNull Weapon weapon) {
        return new WeaponListResponse(
                weapon.getId(), weapon.getWeaponName(), weapon.getWeaponImage(), weapon.getStars()
        );
    }

}
