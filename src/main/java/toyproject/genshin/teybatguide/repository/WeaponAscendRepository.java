package toyproject.genshin.teybatguide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toyproject.genshin.teybatguide.domain.WeaponAscend;

@Repository
public interface WeaponAscendRepository extends JpaRepository<WeaponAscend, String> {
}
