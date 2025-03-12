package toyproject.genshin.teybatguide.banner.weapon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;
import toyproject.genshin.teybatguide.banner.weapon.repository.querydsl.CustomWeaponBannerRepository;
import toyproject.genshin.teybatguide.banner.weapon.entity.WeaponBanner;

@GraphQlRepository
public interface WeaponBannerRepository extends JpaRepository<WeaponBanner, String>, CustomWeaponBannerRepository {
}
