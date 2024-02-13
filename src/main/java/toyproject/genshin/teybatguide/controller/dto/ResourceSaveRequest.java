package toyproject.genshin.teybatguide.controller.dto;

import toyproject.genshin.teybatguide.domain.value.Country;
import toyproject.genshin.teybatguide.domain.value.DayOfWeek;
import toyproject.genshin.teybatguide.domain.value.Materials;
import toyproject.genshin.teybatguide.domain.value.Stars;

public record ResourceSaveRequest(String name, Country country, DayOfWeek day, Stars stars, Materials materials) {
}
