package toyproject.genshin.teybatguide.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import toyproject.genshin.teybatguide.domain.WeaponBanner;

import java.time.LocalDateTime;
import java.util.List;

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

    private BooleanExpression isBetweenDate(LocalDateTime localDateTime) {
        BooleanExpression isLoeStartDate = weaponBanner.bannerStartDate.loe(localDateTime);
        BooleanExpression isGoeEndDate = weaponBanner.bannerEndDate.goe(localDateTime);

        return Expressions.allOf(isLoeStartDate, isGoeEndDate);
    }
}
