package toyproject.genshin.teybatguide.resource.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;
import toyproject.genshin.teybatguide.resource.entity.Resources;
import toyproject.genshin.teybatguide.resource.entity.value.DayOfWeek;
import toyproject.genshin.teybatguide.resource.repository.querydsl.CustomResourcesRepository;

@GraphQlRepository
public interface ResourcesRepository extends JpaRepository<Resources, String>, CustomResourcesRepository {

    Page<Resources> findByDayOfWeek(DayOfWeek dayOfWeek, Pageable pageable);

}
