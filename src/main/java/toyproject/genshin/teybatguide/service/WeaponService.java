package toyproject.genshin.teybatguide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.genshin.teybatguide.controller.dto.characters.CharacterListResponse;
import toyproject.genshin.teybatguide.controller.dto.weapons.WeaponDetailsResponse;
import toyproject.genshin.teybatguide.controller.dto.weapons.WeaponListRequest;
import toyproject.genshin.teybatguide.controller.dto.weapons.WeaponListResponse;
import toyproject.genshin.teybatguide.domain.Weapon;
import toyproject.genshin.teybatguide.exception.TeybatException;
import toyproject.genshin.teybatguide.repository.WeaponRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WeaponService {

    private final WeaponRepository weaponRepository;

    public Page<WeaponListResponse> getWeaponListResponse(Pageable pageable, WeaponListRequest request) {
        return weaponRepository.findByStarsInAndWeaponTypeInAndWeaponOptionIn(request, pageable)
                .map(WeaponListResponse::of);
    }

    public WeaponDetailsResponse searchForBasicWeaponsInformation(String id) {
        Weapon weapon = weaponRepository.findById(id)
                .orElseThrow(() -> new TeybatException("id가 존재하지 않습니다."));

        return WeaponDetailsResponse.of(weapon);
    }

    public List<CharacterListResponse> searchForRecommendedCharacters(String id) {
        Weapon weapon = weaponRepository.findById(id)
                .orElseThrow(() -> new TeybatException("id가 존재하지 않습니다."));

        return weapon.getCharacterWeapons().stream()
                .map(characterWeapon -> CharacterListResponse.of(characterWeapon.getCharacters()))
                .collect(Collectors.toList());
    }

}
