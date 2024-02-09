package toyproject.genshin.teybatguide.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import toyproject.genshin.teybatguide.controller.dto.weapons.WeaponListRequest;
import toyproject.genshin.teybatguide.domain.Weapon;

public interface CustomWeaponRepository {

    Page<Weapon> findByStarsInAndWeaponTypeInAndWeaponOptionIn(WeaponListRequest request, Pageable pageable);

}
