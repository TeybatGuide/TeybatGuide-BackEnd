package toyproject.genshin.teybatguide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toyproject.genshin.teybatguide.domain.Weapon;
import toyproject.genshin.teybatguide.repository.querydsl.CustomWeaponRepository;

@Repository
public interface WeaponRepository extends JpaRepository<Weapon, String>, CustomWeaponRepository {

}
