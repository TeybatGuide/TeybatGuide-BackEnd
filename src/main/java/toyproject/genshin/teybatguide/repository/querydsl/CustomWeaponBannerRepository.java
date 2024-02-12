package toyproject.genshin.teybatguide.repository.querydsl;

import toyproject.genshin.teybatguide.domain.WeaponBanner;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomWeaponBannerRepository {

    List<WeaponBanner> findByDateTimeBetween(LocalDateTime localDateTime);

}
