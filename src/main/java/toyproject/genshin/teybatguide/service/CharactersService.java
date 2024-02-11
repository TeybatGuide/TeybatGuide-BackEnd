package toyproject.genshin.teybatguide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.genshin.teybatguide.controller.dto.ResourceSaveRequest;
import toyproject.genshin.teybatguide.controller.dto.characters.*;
import toyproject.genshin.teybatguide.domain.CharacterWeapon;
import toyproject.genshin.teybatguide.domain.Characters;
import toyproject.genshin.teybatguide.domain.Resources;
import toyproject.genshin.teybatguide.domain.Weapon;
import toyproject.genshin.teybatguide.domain.value.WeaponCriteria;
import toyproject.genshin.teybatguide.exception.TeybatException;
import toyproject.genshin.teybatguide.repository.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CharactersService {

    private final CharactersRepository charactersRepository;
    private final CharacterSpecificationsRepository specificationsRepository;
//    private final CharacterWeaponRepository characterWeaponRepository;
//    private final WeaponRepository weaponRepository;
    private final ResourcesRepository resourcesRepository;

    public Page<CharacterListResponse> findAndCreateCharacterList(CharacterListRequest request, Pageable pageable) {
        return charactersRepository.findByStarsAndCountryAndElementAndWeaponType(request, pageable)
                .map(CharacterListResponse::of);
    }

    public CharacterDetailsResponse findAndBuildCharacterDetails(String id) {
        Characters characters = charactersRepository.findById(id)
                .orElseThrow(() -> new TeybatException("아이디가 존재하지 않습니다."));

        CharacterSpecificationsDto specifications = specificationsRepository.findByCharacters(characters)
                .map(CharacterSpecificationsDto::of)
                .orElse(null);

        return CharacterDetailsResponse.of(characters, specifications);

    }

    public CharacterWeaponResponse findAndBuildCharacterWeapon(String id) {
        Characters character = charactersRepository.findById(id)
                .orElseThrow(() -> new TeybatException("아이디가 존재하지 않습니다."));

        List<CharacterWeapon> characterList = character.getCharacterWeapons();

        Map<WeaponCriteria, List<CharacterWeaponDto>> collect = characterList.stream()
                .collect(Collectors.groupingBy(
                        CharacterWeapon::getWeaponCriteria,
                        Collectors.mapping(CharacterWeaponDto::of, Collectors.toList())
                ));

        List<CharacterWeaponListDto> characterWeaponListDtos = collect.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> entry.getKey().getPriorities()))
                .map(criteria -> CharacterWeaponListDto.of(
                        criteria.getKey(),
                        criteria.getValue())
                )
                .toList();


        return CharacterWeaponResponse.of(characterWeaponListDtos, getVersion(characterList));
    }

    private boolean isPresentCharacterList(List<CharacterWeapon> characterWeapons) {
        return !characterWeapons.isEmpty();
    }

    private String getVersion(List<CharacterWeapon> characterWeapons) {
        if(isPresentCharacterList(characterWeapons)) {
            return characterWeapons.get(0).getVersion();
        }
        return null;
    }

    @Transactional
    public String saveResources(ResourceSaveRequest request) {
        resourcesRepository.save(Resources.of(request));
        return "good";
    }

//    @Transactional
//    public String save(CharacterWeaponSaveRequest request) {
//        Characters character = charactersRepository.findById(request.characterId())
//                .orElseThrow(() -> new TeybatException("캐릭터가 없음"));
//
//        Weapon weapon = weaponRepository.findById(request.weaponId()).orElseThrow(() -> new TeybatException("무기가 없음"));
//        characterWeaponRepository.save(CharacterWeapon.of(character, weapon, request));
//
//        return "goood";
//    }

}
