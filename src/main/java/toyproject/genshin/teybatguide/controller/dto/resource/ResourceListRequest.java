package toyproject.genshin.teybatguide.controller.dto.resource;

import toyproject.genshin.teybatguide.domain.value.Country;
import toyproject.genshin.teybatguide.domain.value.DayOfWeek;
import toyproject.genshin.teybatguide.domain.value.Materials;

import java.util.List;

public record ResourceListRequest(List<Country> countries, List<DayOfWeek> dayOfWeek, List<Materials> materials) {
}
