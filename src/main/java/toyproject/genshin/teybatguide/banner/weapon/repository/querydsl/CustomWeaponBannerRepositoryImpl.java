package toyproject.genshin.teybatguide.banner.weapon.repository.querydsl;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import toyproject.genshin.teybatguide.banner.value.BannerType;
import toyproject.genshin.teybatguide.banner.weapon.entity.WeaponBanner;
import toyproject.genshin.teybatguide.weapon.entity.Weapon;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.querydsl.core.group.GroupBy.list;
import static toyproject.genshin.teybatguide.banner.weapon.entity.QWeaponBanner.weaponBanner;
import static toyproject.genshin.teybatguide.weapon.entity.QWeapon.weapon;

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
    public List<WeaponBanner> findByDateTimeBetween(LocalDateTime localDateTime, BannerType bannerType) {
        return jpaQueryFactory
            .selectFrom(weaponBanner)
            .where(
                betweenDate(localDateTime),
                eqBannerType(bannerType)
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
