package toyproject.genshin.teybatguide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.genshin.teybatguide.controller.dto.characters.CharacterListResponse;
import toyproject.genshin.teybatguide.controller.dto.characters.CharacterWeaponDto;
import toyproject.genshin.teybatguide.controller.dto.characters.CharacterWeaponListDto;
import toyproject.genshin.teybatguide.controller.dto.weapons.*;
import toyproject.genshin.teybatguide.domain.CharacterWeapon;
import toyproject.genshin.teybatguide.domain.Resources;
import toyproject.genshin.teybatguide.domain.Weapon;
import toyproject.genshin.teybatguide.domain.WeaponAscend;
import toyproject.genshin.teybatguide.domain.value.Stars;
import toyproject.genshin.teybatguide.domain.value.WeaponCriteria;
import toyproject.genshin.teybatguide.exception.TeybatException;
import toyproject.genshin.teybatguide.repository.ResourcesRepository;
import toyproject.genshin.teybatguide.repository.WeaponAscendRepository;
import toyproject.genshin.teybatguide.repository.WeaponRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WeaponService {

    private final WeaponRepository weaponRepository;
    private final WeaponAscendRepository weaponAscendRepository;
    private final ResourcesRepository resourcesRepository;

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

    public List<WeaponAscendListResponse> searchForAscendResources(String id) {
        Weapon weapon = weaponRepository.findById(id)
                .orElseThrow(() -> new TeybatException("id가 존재하지 않습니다."));

        Map<Stars, List<WeaponAscendDto>> map = weapon.getWeaponAscends().stream()
                .collect(Collectors.groupingBy(
                        weaponAscend -> weaponAscend.getResources().getStars(),
                        Collectors.mapping(WeaponAscendDto::of, Collectors.toList())
                ));

        return map.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> entry.getKey().getPriority()))
                .map(criteria -> WeaponAscendListResponse.of(
                        criteria.getKey(),
                        criteria.getValue()
                ))
                .toList();
    }

    @Transactional
    public String saveWeaponAscend(WeaponAscendSaveRequest request) {
        Weapon weapon = weaponRepository.findById(request.weaponId())
                .orElseThrow(() -> new TeybatException("id가 없습니다."));
        Resources resources = resourcesRepository.findById(request.resourceId())
                .orElseThrow(() -> new TeybatException("id가 없습니다."));
        weaponAscendRepository.save(WeaponAscend.of(request, weapon, resources));
        return "good";
    }

}
