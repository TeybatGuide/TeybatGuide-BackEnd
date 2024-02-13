package toyproject.genshin.teybatguide.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toyproject.genshin.teybatguide.domain.Resources;
import toyproject.genshin.teybatguide.domain.value.DayOfWeek;
import toyproject.genshin.teybatguide.domain.value.Stars;
import toyproject.genshin.teybatguide.repository.querydsl.CustomResourcesRepository;

@Repository
public interface ResourcesRepository extends JpaRepository<Resources, String>, CustomResourcesRepository {

}
