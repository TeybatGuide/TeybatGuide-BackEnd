package toyproject.genshin.teybatguide.weapon.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import toyproject.genshin.teybatguide.weapon.controller.dto.request.WeaponListRequest;
import toyproject.genshin.teybatguide.weapon.entity.Weapon;

public interface CustomWeaponRepository {

    Page<Weapon> findByStarsInAndWeaponTypeInAndWeaponOptionIn(WeaponListRequest request, Pageable pageable);

}
