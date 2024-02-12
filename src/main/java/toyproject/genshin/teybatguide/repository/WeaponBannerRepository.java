package toyproject.genshin.teybatguide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toyproject.genshin.teybatguide.domain.WeaponBanner;
import toyproject.genshin.teybatguide.repository.querydsl.CustomWeaponBannerRepository;

@Repository
public interface WeaponBannerRepository extends JpaRepository<WeaponBanner, String>, CustomWeaponBannerRepository {
}
