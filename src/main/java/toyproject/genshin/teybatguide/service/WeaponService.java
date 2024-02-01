package toyproject.genshin.teybatguide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.genshin.teybatguide.repository.WeaponRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WeaponService {

    private final WeaponRepository weaponRepository;

}
