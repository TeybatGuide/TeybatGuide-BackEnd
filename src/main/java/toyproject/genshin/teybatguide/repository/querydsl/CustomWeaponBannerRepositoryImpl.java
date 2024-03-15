package toyproject.genshin.teybatguide.repository.querydsl;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import toyproject.genshin.teybatguide.domain.Weapon;
import toyproject.genshin.teybatguide.domain.WeaponBanner;
import toyproject.genshin.teybatguide.domain.value.BannerType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.querydsl.core.group.GroupBy.list;
import static toyproject.genshin.teybatguide.domain.QWeapon.weapon;
import static toyproject.genshin.teybatguide.domain.QWeaponBanner.weaponBanner;

@RequiredArgsConstructor
public class CustomWeaponBannerRepositoryImpl implements CustomWeaponBannerRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Map<BannerType, List<WeaponBanner>> findByDateTimeBetweenGroupBy(LocalDateTime localDateTime) {
        return jpaQueryFactory
                .from(weaponBanner)
                .where(betweenDate(localDateTime))
                .transform(GroupBy
                        .groupBy(weaponBanner.bannerType)
                        .as(list(weaponBanner))
                );
    }

    @Override
    public List<WeaponBanner> findByDateTimeBetween(LocalDateTime localDateTime) {
        return jpaQueryFactory
                .selectFrom(weaponBanner)
                .where(
                        betweenDate(localDateTime),
                        eqBannerType(BannerType.WEAPON)
                )
                .fetch();
    }

    @Override
    public Optional<Weapon> findWeaponById(String id) {
        return Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(weapon)
                        .where(eqId(id))
                        .fetchOne()
        );
    }

    private BooleanExpression betweenDate(LocalDateTime localDateTime) {
        BooleanExpression isLoeStartDate = weaponBanner.bannerStartDate.loe(localDateTime);
        BooleanExpression isGoeEndDate = weaponBanner.bannerEndDate.goe(localDateTime);

        return Expressions.allOf(isLoeStartDate, isGoeEndDate);
    }

    private BooleanExpression eqId(String id) {
        return id != null ? weapon.id.eq(id) : null;
    }

    private BooleanExpression eqBannerType(BannerType bannerType) {
        return weaponBanner.bannerType.eq(bannerType);
    }
}
