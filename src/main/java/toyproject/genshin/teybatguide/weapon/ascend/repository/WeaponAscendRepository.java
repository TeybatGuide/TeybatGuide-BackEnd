package toyproject.genshin.teybatguide.weapon.ascend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toyproject.genshin.teybatguide.weapon.ascend.entity.WeaponAscend;

@Repository
public interface WeaponAscendRepository extends JpaRepository<WeaponAscend, String> {
}
