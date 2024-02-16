package toyproject.genshin.teybatguide.repository.querydsl;

import toyproject.genshin.teybatguide.domain.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomEventRepository {

    List<Event> findByDate(LocalDateTime dateTime);

}
