package toyproject.genshin.teybatguide.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import toyproject.genshin.teybatguide.domain.Event;

import java.time.LocalDateTime;
import java.util.List;

import static toyproject.genshin.teybatguide.domain.QEvent.event;

@RequiredArgsConstructor
public class CustomEventRepositoryImpl implements CustomEventRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Event> findByDate(LocalDateTime dateTime) {
        return jpaQueryFactory
                .selectFrom(event)
                .where(betweenDate(dateTime))
                .fetch();
    }

    private BooleanExpression betweenDate(LocalDateTime localDateTime) {
        BooleanExpression isLoeStartDate = event.eventStartDate.loe(localDateTime);
        BooleanExpression isGoeEndDate = event.eventEndDate.goe(localDateTime);

        return Expressions.allOf(isLoeStartDate, isGoeEndDate);
    }
}
