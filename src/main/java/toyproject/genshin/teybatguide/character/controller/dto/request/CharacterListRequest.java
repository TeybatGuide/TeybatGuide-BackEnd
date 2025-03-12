package toyproject.genshin.teybatguide.character.controller.dto.request;

import toyproject.genshin.teybatguide.base.value.Country;
import toyproject.genshin.teybatguide.character.entity.value.Element;
import toyproject.genshin.teybatguide.base.value.Stars;
import toyproject.genshin.teybatguide.weapon.entity.value.WeaponType;

import java.util.List;

public record CharacterListRequest(
        List<Stars> stars,
        List<Country> countries,
        List<Element> elements,
        List<WeaponType> weaponTypes) {
}
