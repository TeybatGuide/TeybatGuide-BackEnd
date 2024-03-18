package toyproject.genshin.teybatguide.repository.querydsl;

import toyproject.genshin.teybatguide.domain.Weapon;
import toyproject.genshin.teybatguide.domain.WeaponBanner;
import toyproject.genshin.teybatguide.domain.value.BannerType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CustomWeaponBannerRepository {

    Map<BannerType, List<WeaponBanner>> findByDateTimeBetweenGroupBy(LocalDateTime localDateTime);

    List<WeaponBanner> findByDateTimeBetween(LocalDateTime localDateTime, BannerType bannerType);

    Optional<Weapon> findWeaponById(String id);

}
