package toyproject.genshin.teybatguide.controller.dto.weapons;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.domain.Resources;
import toyproject.genshin.teybatguide.domain.WeaponAscend;

@Slf4j
public record WeaponAscendDto(String id, String name, int count) {

    @Contract("_ -> new")
    public static @NotNull WeaponAscendDto of(@NotNull WeaponAscend ascend) {
        Resources resources = ascend.getResources();
        return new WeaponAscendDto(resources.getId(), resources.getResourcesName(), ascend.getWeaponAscendCount());
    }

}
