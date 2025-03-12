package toyproject.genshin.teybatguide.weapon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toyproject.genshin.teybatguide.weapon.entity.Weapon;
import toyproject.genshin.teybatguide.weapon.repository.querydsl.CustomWeaponRepository;

@Repository
public interface WeaponRepository extends JpaRepository<Weapon, String>, CustomWeaponRepository {

}
