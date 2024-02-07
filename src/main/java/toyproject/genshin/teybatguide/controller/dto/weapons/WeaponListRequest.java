package toyproject.genshin.teybatguide.controller.dto.weapons;

import toyproject.genshin.teybatguide.domain.value.Stars;
import toyproject.genshin.teybatguide.domain.value.WeaponOptions;
import toyproject.genshin.teybatguide.domain.value.WeaponType;

import java.util.List;

public record WeaponListRequest(List<Stars> stars, List<WeaponOptions> weaponOptions, List<WeaponType> weaponTypes) {
}
