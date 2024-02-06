package toyproject.genshin.teybatguide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.genshin.teybatguide.controller.dto.WeaponListResponse;
import toyproject.genshin.teybatguide.repository.WeaponRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WeaponService {

    private final WeaponRepository weaponRepository;

    public List<WeaponListResponse> getWeaponListResponse() {
        return weaponRepository.findAll().stream()
                .map(WeaponListResponse::of)
                .toList();
    }

}
