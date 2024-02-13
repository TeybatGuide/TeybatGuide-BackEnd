package toyproject.genshin.teybatguide.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import toyproject.genshin.teybatguide.domain.Weapon;
import toyproject.genshin.teybatguide.domain.WeaponBanner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static toyproject.genshin.teybatguide.domain.QWeapon.weapon;
import static toyproject.genshin.teybatguide.domain.QWeaponBanner.weaponBanner;

@RequiredArgsConstructor
public class CustomWeaponBannerRepositoryImpl implements CustomWeaponBannerRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<WeaponBanner> findByDateTimeBetween(LocalDateTime localDateTime) {
        return jpaQueryFactory
                .selectFrom(weaponBanner)
                .where(isBetweenDate(localDateTime))
                .fetch();
    }

    @Override
    public Optional<Weapon> findWeaponById(String id) {
        return Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(weapon)
                        .where(isIdEq(id))
                        .fetchOne()
        );
    }

    private BooleanExpression isBetweenDate(LocalDateTime localDateTime) {
        BooleanExpression isLoeStartDate = weaponBanner.bannerStartDate.loe(localDateTime);
        BooleanExpression isGoeEndDate = weaponBanner.bannerEndDate.goe(localDateTime);

        return Expressions.allOf(isLoeStartDate, isGoeEndDate);
    }

    private BooleanExpression isIdEq(String id) {
        return id != null ? weapon.id.eq(id) : null;
    }
}