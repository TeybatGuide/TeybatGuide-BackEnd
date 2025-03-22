package toyproject.genshin.teybatguide.banner.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;
import toyproject.genshin.teybatguide.banner.event.entity.Event;
import toyproject.genshin.teybatguide.banner.event.repository.querydsl.CustomEventRepository;

@GraphQlRepository
public interface EventRepository extends JpaRepository<Event, String>, CustomEventRepository {

}
