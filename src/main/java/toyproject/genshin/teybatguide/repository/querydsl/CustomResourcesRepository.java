package toyproject.genshin.teybatguide.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import toyproject.genshin.teybatguide.controller.dto.resource.ResourceListRequest;
import toyproject.genshin.teybatguide.domain.Resources;
import toyproject.genshin.teybatguide.domain.value.DayOfWeek;
import toyproject.genshin.teybatguide.domain.value.Materials;

public interface CustomResourcesRepository {

    Page<Resources> findByDayOfWeekForMain(DayOfWeek dayOfWeek, Pageable pageable);

    Page<Resources> findByDayOfWeekAndMaterialForMain(DayOfWeek dayOfWeek, Materials materials, Pageable pageable);

    Page<Resources> findByCountryAndDayOfWeekAndMaterial(ResourceListRequest request, Pageable pageable);

}
