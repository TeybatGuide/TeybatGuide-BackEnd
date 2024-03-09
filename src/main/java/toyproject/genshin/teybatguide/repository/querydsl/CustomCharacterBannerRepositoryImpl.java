package toyproject.genshin.teybatguide.repository.querydsl;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import toyproject.genshin.teybatguide.domain.CharacterBanner;
import toyproject.genshin.teybatguide.domain.Characters;
import toyproject.genshin.teybatguide.domain.value.BannerType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.querydsl.core.group.GroupBy.list;
import static toyproject.genshin.teybatguide.domain.QCharacterBanner.characterBanner;
import static toyproject.genshin.teybatguide.domain.QCharacters.characters;

@RequiredArgsConstructor
public class CustomCharacterBannerRepositoryImpl implements CustomCharacterBannerRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Map<BannerType, List<CharacterBanner>> findByDateTimeBetween(LocalDateTime localDateTime) {
        return jpaQueryFactory
                .from(characterBanner)
                .where(betweenDate(localDateTime))
                .transform(GroupBy
                        .groupBy(characterBanner.bannerType)
                        .as(list(characterBanner))
                );
    }

    @Override
    public Optional<Characters> findCharactersById(String id) {
        return Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(characters)
                        .where(eqCharacterId(id))
                        .fetchOne()
        );
    }

    private BooleanExpression betweenDate(LocalDateTime localDateTime) {
        BooleanExpression isLoeStartDate = characterBanner.bannerStartDate.loe(localDateTime);
        BooleanExpression isGoeEndDate = characterBanner.bannerEndDate.goe(localDateTime);

        return Expressions.allOf(isLoeStartDate, isGoeEndDate);
    }

    private BooleanExpression eqCharacterId(String characterId) {
        return characterId != null ? characters.id.eq(characterId) : null;
    }

}
