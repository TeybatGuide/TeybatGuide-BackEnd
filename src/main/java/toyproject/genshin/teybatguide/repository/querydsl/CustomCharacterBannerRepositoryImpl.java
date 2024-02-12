package toyproject.genshin.teybatguide.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import toyproject.genshin.teybatguide.domain.CharacterBanner;
import toyproject.genshin.teybatguide.domain.Characters;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static toyproject.genshin.teybatguide.domain.QCharacterBanner.characterBanner;
import static toyproject.genshin.teybatguide.domain.QCharacters.characters;

@RequiredArgsConstructor
public class CustomCharacterBannerRepositoryImpl implements CustomCharacterBannerRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CharacterBanner> findByDateTimeBetween(LocalDateTime localDateTime) {
        return jpaQueryFactory
                .selectFrom(characterBanner)
                .where(isBetweenDate(localDateTime))
                .fetch();
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

    private BooleanExpression isBetweenDate(LocalDateTime localDateTime) {
        BooleanExpression isLoeStartDate = characterBanner.bannerStartDate.loe(localDateTime);
        BooleanExpression isGoeEndDate = characterBanner.bannerEndDate.goe(localDateTime);

        return Expressions.allOf(isLoeStartDate, isGoeEndDate);
    }

    private BooleanExpression eqCharacterId(String characterId) {
        return characterId != null ? characters.id.eq(characterId) : null;
    }

}
