package toyproject.genshin.teybatguide.resource.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import toyproject.genshin.teybatguide.resource.controller.dto.ResourceListRequest;
import toyproject.genshin.teybatguide.resource.entity.Resources;
import toyproject.genshin.teybatguide.resource.entity.value.DayOfWeek;
import toyproject.genshin.teybatguide.resource.entity.value.Materials;

public interface CustomResourcesRepository {

    Page<Resources> findByDayOfWeekForMain(DayOfWeek dayOfWeek, Pageable pageable);

    Page<Resources> findByDayOfWeekAndMaterialForMain(DayOfWeek dayOfWeek, Materials materials, Pageable pageable);

    Page<Resources> findByCountryAndDayOfWeekAndMaterial(ResourceListRequest request, Pageable pageable);

}
