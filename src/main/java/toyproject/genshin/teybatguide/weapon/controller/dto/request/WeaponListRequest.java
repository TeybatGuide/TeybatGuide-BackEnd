package toyproject.genshin.teybatguide.weapon.controller.dto.request;

import toyproject.genshin.teybatguide.base.value.Stars;
import toyproject.genshin.teybatguide.weapon.entity.value.WeaponOptions;
import toyproject.genshin.teybatguide.weapon.entity.value.WeaponType;

import java.util.List;

public record WeaponListRequest(List<Stars> stars, List<WeaponOptions> weaponOptions, List<WeaponType> weaponTypes) {
}
