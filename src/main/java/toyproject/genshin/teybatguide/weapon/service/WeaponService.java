package toyproject.genshin.teybatguide.weapon.service;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.genshin.teybatguide.character.controller.dto.response.CharacterListResponse;
import toyproject.genshin.teybatguide.weapon.ascend.entity.WeaponAscend;
import toyproject.genshin.teybatguide.weapon.ascend.repository.WeaponAscendRepository;
import toyproject.genshin.teybatguide.weapon.controller.dto.WeaponAscendDto;
import toyproject.genshin.teybatguide.weapon.controller.dto.request.WeaponAscendSaveRequest;
import toyproject.genshin.teybatguide.weapon.controller.dto.request.WeaponListRequest;
import toyproject.genshin.teybatguide.weapon.controller.dto.response.WeaponAscendListResponse;
import toyproject.genshin.teybatguide.weapon.controller.dto.response.WeaponDetailsResponse;
import toyproject.genshin.teybatguide.weapon.controller.dto.response.WeaponListResponse;
import toyproject.genshin.teybatguide.resource.entity.Resources;
import toyproject.genshin.teybatguide.base.value.Stars;
import toyproject.genshin.teybatguide.exception.TeybatBadRequestException;
import toyproject.genshin.teybatguide.exception.TeybatDataAccessException;
import toyproject.genshin.teybatguide.exception.TeybatException;
import toyproject.genshin.teybatguide.resource.repository.ResourcesRepository;
import toyproject.genshin.teybatguide.weapon.entity.Weapon;
import toyproject.genshin.teybatguide.weapon.repository.WeaponRepository;

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
                .orElseThrow(() -> new TeybatBadRequestException("id가 존재하지 않습니다."));

        return WeaponDetailsResponse.of(weapon);
    }

    public List<CharacterListResponse> searchForRecommendedCharacters(String id) {
        Weapon weapon = weaponRepository.findById(id)
                .orElseThrow(() -> new TeybatException("무기가 존재하지 않습니다."));

        return weapon.getCharacterWeapons().stream()
                .map(characterWeapon -> CharacterListResponse.of(characterWeapon.getCharacters()))
                .collect(Collectors.toList());
    }

    public List<WeaponAscendListResponse> searchForAscendResources(String id) {
        Weapon weapon = weaponRepository.findById(id)
                .orElseThrow(() -> new TeybatBadRequestException("무기가 존재하지 않습니다."));

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
    public WeaponAscendDto saveWeaponAscend(@NotNull WeaponAscendSaveRequest request) {
        Weapon weapon = weaponRepository.findById(request.weaponId())
                .orElseThrow(() -> new TeybatBadRequestException("무기가 존재하지 않습니다."));
        Resources resources = resourcesRepository.findById(request.resourceId())
                .orElseThrow(() -> new TeybatBadRequestException("resource가 존재하지 않습니다."));

        WeaponAscend entity = WeaponAscend.of(request, weapon, resources);
        weaponAscendRepository.save(entity);

        WeaponAscend weaponAscend = weaponAscendRepository.findById(entity.getId())
                .orElseThrow(() -> new TeybatDataAccessException("저장에 실패하였습니다."));
        return WeaponAscendDto.of(weaponAscend);
    }

}
