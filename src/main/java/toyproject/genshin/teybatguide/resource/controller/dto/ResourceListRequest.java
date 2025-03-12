package toyproject.genshin.teybatguide.resource.controller.dto;

import toyproject.genshin.teybatguide.base.value.Country;
import toyproject.genshin.teybatguide.resource.entity.value.DayOfWeek;
import toyproject.genshin.teybatguide.resource.entity.value.Materials;

import java.util.List;

public record ResourceListRequest(List<Country> countries, List<DayOfWeek> dayOfWeek, List<Materials> materials) {
}
