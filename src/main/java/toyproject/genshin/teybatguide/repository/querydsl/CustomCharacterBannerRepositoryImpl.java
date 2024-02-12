package toyproject.genshin.teybatguide.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import toyproject.genshin.teybatguide.domain.CharacterBanner;

import java.time.LocalDateTime;
import java.util.List;

import static toyproject.genshin.teybatguide.domain.QCharacterBanner.characterBanner;

@RequiredArgsConstructor
public class CustomCharacterBannerRepositoryImpl implements CustomCharacterBannerRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CharacterBanner> findByDateTimeBetween(LocalDateTime localDateTime) {
        return jpaQueryFactory
                .selectFrom(characterBanner)
                .where(
                        isBetweenDate(localDateTime)
                )
                .fetch();
    }

    private BooleanExpression isBetweenDate(LocalDateTime localDateTime) {
        BooleanExpression isLoeStartDate = characterBanner.bannerStartDate.loe(localDateTime);
        BooleanExpression isGoeEndDate = characterBanner.bannerEndDate.goe(localDateTime);

        return Expressions.allOf(isLoeStartDate, isGoeEndDate);
    }

}
