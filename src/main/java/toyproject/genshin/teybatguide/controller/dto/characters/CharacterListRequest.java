package toyproject.genshin.teybatguide.controller.dto.characters;

import toyproject.genshin.teybatguide.domain.value.Country;
import toyproject.genshin.teybatguide.domain.value.Element;
import toyproject.genshin.teybatguide.domain.value.Stars;
import toyproject.genshin.teybatguide.domain.value.WeaponType;

import java.util.List;

public record CharacterListRequest(
        List<Stars> stars,
        List<Country> countries,
        List<Element> elements,
        List<WeaponType> weaponTypes) {
}
