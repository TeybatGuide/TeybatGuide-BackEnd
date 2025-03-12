package toyproject.genshin.teybatguide.banner.weapon.repository.querydsl;

import toyproject.genshin.teybatguide.weapon.entity.Weapon;
import toyproject.genshin.teybatguide.banner.weapon.entity.WeaponBanner;
import toyproject.genshin.teybatguide.banner.value.BannerType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CustomWeaponBannerRepository {

    Map<BannerType, List<WeaponBanner>> findByDateTimeBetweenGroupBy(LocalDateTime localDateTime);

    List<WeaponBanner> findByDateTimeBetween(LocalDateTime localDateTime, BannerType bannerType);

    Optional<Weapon> findWeaponById(String id);

}
