package toyproject.genshin.teybatguide.weapon.controller.dto;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.resource.entity.Resources;
import toyproject.genshin.teybatguide.weapon.ascend.entity.WeaponAscend;

@Slf4j
public record WeaponAscendDto(String id, String name, int count) {

    @Contract("_ -> new")
    public static @NotNull WeaponAscendDto of(@NotNull WeaponAscend ascend) {
        Resources resources = ascend.getResources();
        return new WeaponAscendDto(resources.getId(), resources.getResourcesName(), ascend.getWeaponAscendCount());
    }

}
