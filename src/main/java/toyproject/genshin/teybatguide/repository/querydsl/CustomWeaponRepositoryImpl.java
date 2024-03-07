package toyproject.genshin.teybatguide.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import toyproject.genshin.teybatguide.controller.dto.weapons.WeaponListRequest;
import toyproject.genshin.teybatguide.domain.Weapon;
import toyproject.genshin.teybatguide.domain.value.Stars;
import toyproject.genshin.teybatguide.domain.value.WeaponOptions;
import toyproject.genshin.teybatguide.domain.value.WeaponType;

import java.util.List;

import static toyproject.genshin.teybatguide.domain.QWeapon.weapon;

@Slf4j
@RequiredArgsConstructor
public class CustomWeaponRepositoryImpl implements CustomWeaponRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Weapon> findByStarsInAndWeaponTypeInAndWeaponOptionIn(
            WeaponListRequest request, Pageable pageable) {

        List<Weapon> weapons = queryFactory
                .selectFrom(weapon)
                .where(
                        inStars(request.stars()),
                        inWeaponType(request.weaponTypes()),
                        inWeaponOptions(request.weaponOptions())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return PageableExecutionUtils.getPage(weapons, pageable, getCount(request)::fetchOne);
    }

    private JPAQuery<Long> getCount(WeaponListRequest request) {
        return queryFactory
                .select(weapon.count())
                .from(weapon)
                .where(
                        inStars(request.stars()),
                        inWeaponType(request.weaponTypes()),
                        inWeaponOptions(request.weaponOptions())
                );
    }

    private BooleanExpression inStars(List<Stars> stars) {
        return stars != null ? weapon.stars.in(stars) : null;
    }

    private BooleanExpression inWeaponType(List<WeaponType> weaponTypes) {
        return weaponTypes != null ? weapon.weaponType.in(weaponTypes) : null;
    }

    private BooleanExpression inWeaponOptions(List<WeaponOptions> weaponOptions) {
        return weaponOptions != null ? weapon.weaponOption.in(weaponOptions) : null;
    }
}
