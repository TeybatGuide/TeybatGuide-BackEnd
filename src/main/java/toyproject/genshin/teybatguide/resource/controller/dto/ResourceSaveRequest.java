package toyproject.genshin.teybatguide.resource.controller.dto;

import toyproject.genshin.teybatguide.base.value.Country;
import toyproject.genshin.teybatguide.resource.entity.value.DayOfWeek;
import toyproject.genshin.teybatguide.resource.entity.value.Materials;
import toyproject.genshin.teybatguide.base.value.Stars;

public record ResourceSaveRequest(
        String name,
        Country country,
        DayOfWeek day,
        Stars stars,
        Materials materials,
        Materials materialsDetails,
        String domainId
) { }
