package toyproject.genshin.teybatguide.banner.event.repository.querydsl;

import toyproject.genshin.teybatguide.banner.event.entity.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomEventRepository {

    List<Event> findByDate(LocalDateTime dateTime);

}
