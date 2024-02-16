package toyproject.genshin.teybatguide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toyproject.genshin.teybatguide.domain.Event;
import toyproject.genshin.teybatguide.repository.querydsl.CustomEventRepository;

@Repository
public interface EventRepository extends JpaRepository<Event, String>, CustomEventRepository {
}
