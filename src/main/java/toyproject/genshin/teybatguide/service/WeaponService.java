package toyproject.genshin.teybatguide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.genshin.teybatguide.controller.dto.weapons.WeaponListRequest;
import toyproject.genshin.teybatguide.controller.dto.weapons.WeaponListResponse;
import toyproject.genshin.teybatguide.repository.WeaponRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WeaponService {

    private final WeaponRepository weaponRepository;

    public Page<WeaponListResponse> getWeaponListResponse(Pageable pageable, WeaponListRequest request) {
        return weaponRepository.findByStarsInAndWeaponTypeInAndWeaponOptionIn(request, pageable)
                .map(WeaponListResponse::of);
    }

    private boolean isNull(WeaponListRequest request) {
        return request.stars() == null && request.weaponOptions() == null && request.weaponTypes() == null;
    }

}
