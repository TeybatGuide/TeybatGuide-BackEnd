package toyproject.genshin.teybatguide.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;
import toyproject.genshin.teybatguide.domain.Resources;
import toyproject.genshin.teybatguide.domain.value.DayOfWeek;
import toyproject.genshin.teybatguide.repository.querydsl.CustomResourcesRepository;

@GraphQlRepository
public interface ResourcesRepository extends JpaRepository<Resources, String>, CustomResourcesRepository {

    Page<Resources> findByDayOfWeek(DayOfWeek dayOfWeek, Pageable pageable);

}
