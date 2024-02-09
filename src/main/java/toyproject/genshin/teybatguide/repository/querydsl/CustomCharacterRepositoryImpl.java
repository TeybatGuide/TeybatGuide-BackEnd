package toyproject.genshin.teybatguide.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import toyproject.genshin.teybatguide.controller.dto.characters.CharacterListRequest;
import toyproject.genshin.teybatguide.domain.Characters;
import toyproject.genshin.teybatguide.domain.value.Country;
import toyproject.genshin.teybatguide.domain.value.Element;
import toyproject.genshin.teybatguide.domain.value.Stars;
import toyproject.genshin.teybatguide.domain.value.WeaponType;

import java.util.List;

import static toyproject.genshin.teybatguide.domain.QCharacters.characters;

@RequiredArgsConstructor
public class CustomCharacterRepositoryImpl implements CustomCharacterRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Characters> findByStarsAndCountryAndElementAndWeaponType(CharacterListRequest request, Pageable pageable) {
        List<Characters> result = queryFactory
                .selectFrom(characters)
                .where(
                        inElement(request.elements()),
                        inCountry(request.countries()),
                        inStars(request.stars()),
                        inWeaponType(request.weaponTypes())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return PageableExecutionUtils.getPage(result, pageable, getCount(request)::fetchOne);
    }

    private JPAQuery<Long> getCount(CharacterListRequest request) {
        return queryFactory
                .select(characters.count())
                .from(characters)
                .where(
                        inElement(request.elements()),
                        inCountry(request.countries()),
                        inStars(request.stars()),
                        inWeaponType(request.weaponTypes())
                );
    }

    private BooleanExpression inElement(List<Element> elements) {
        return elements != null ? characters.element.in(elements) : characters.element.in(Element.values());
    }

    private BooleanExpression inCountry(List<Country> countries) {
        return countries != null ? characters.country.in(countries) : characters.country.in(Country.values());
    }

    private BooleanExpression inStars(List<Stars> stars) {
        return stars != null ? characters.stars.in(stars) : characters.stars.in(Stars.values());
    }

    private BooleanExpression inWeaponType(List<WeaponType> weaponTypes) {
        return weaponTypes != null ? characters.weaponType.in(weaponTypes) : characters.weaponType.in(WeaponType.values());
    }
}
