package toyproject.genshin.teybatguide.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import toyproject.genshin.teybatguide.domain.Resources;
import toyproject.genshin.teybatguide.domain.value.DayOfWeek;
import toyproject.genshin.teybatguide.domain.value.Materials;
import toyproject.genshin.teybatguide.domain.value.Stars;

import java.util.List;

import static toyproject.genshin.teybatguide.domain.QResources.resources;

@RequiredArgsConstructor
public class CustomResourcesRepositoryImpl implements CustomResourcesRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Resources> findByDayOfWeekForMain(DayOfWeek dayOfWeek, Pageable pageable) {

        List<Resources> resourcesList = jpaQueryFactory
                .selectFrom(resources)
                .where(
                        eqDayOfWeek(dayOfWeek),
                        eqStarsAndMaterials()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return PageableExecutionUtils.getPage(resourcesList, pageable, getCount(dayOfWeek)::fetchOne);
    }

    private JPAQuery<Long> getCount(DayOfWeek dayOfWeek) {
        return jpaQueryFactory
                .select(resources.count())
                .from(resources)
                .where(
                        eqDayOfWeek(dayOfWeek),
                        eqStarsAndMaterials()
                );
    }

    private BooleanExpression eqStarsAndMaterials() {
        BooleanExpression characterTalent = eqMaterials(Materials.CHARACTERS_TALENT_MATERIAL).and(eqStar(Stars.FOUR));
        BooleanExpression weaponAscension = eqMaterials(Materials.WEAPONS_ASCENSION_MATERIAL).and(eqStar(Stars.FOUR));

        return Expressions.allOf(characterTalent, weaponAscension);
    }

    private BooleanExpression eqDayOfWeek(DayOfWeek dayOfWeek) {
        return resources.dayOfWeek.eq(dayOfWeek);
    }

    private BooleanExpression eqMaterials(Materials materials) {
        return resources.materials.eq(materials);
    }

    private BooleanExpression eqStar(Stars stars) {
        return resources.stars.eq(stars);
    }
}
