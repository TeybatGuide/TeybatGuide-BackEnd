package toyproject.genshin.teybatguide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.genshin.teybatguide.controller.dto.*;
import toyproject.genshin.teybatguide.domain.CharacterWeapon;
import toyproject.genshin.teybatguide.domain.Characters;
import toyproject.genshin.teybatguide.domain.Weapon;
import toyproject.genshin.teybatguide.domain.value.SignatureWeapon;
import toyproject.genshin.teybatguide.domain.value.WeaponCriteria;
import toyproject.genshin.teybatguide.exception.TeybatException;
import toyproject.genshin.teybatguide.repository.CharacterSpecificationsRepository;
import toyproject.genshin.teybatguide.repository.CharacterWeaponRepository;
import toyproject.genshin.teybatguide.repository.CharactersRepository;
import toyproject.genshin.teybatguide.repository.WeaponRepository;

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
    private final CharacterWeaponRepository characterWeaponRepository;
    private final WeaponRepository weaponRepository;

    public List<CharacterListResponse> findAndCreateCharacterList() {
        return charactersRepository.findAll()
                .stream()
                .map(CharacterListResponse::of)
                .toList();
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

        List<CharacterWeapon> characterList = characterWeaponRepository.findByCharacters(character);

        Map<WeaponCriteria, List<WeaponDto>> collect = characterList.stream()
                .collect(Collectors.groupingBy(
                        CharacterWeapon::getWeaponCriteria,
                        Collectors.mapping(WeaponDto::of, Collectors.toList())
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
    public String save(CharacterWeaponSaveRequest request) {
        Characters character = charactersRepository.findById(request.characterId())
                .orElseThrow(() -> new TeybatException("캐릭터가 없음"));

        Weapon weapon = weaponRepository.findById(request.weaponId()).orElseThrow(() -> new TeybatException("무기가 없음"));
        characterWeaponRepository.save(CharacterWeapon.of(character, weapon, request));

        return "goood";
    }

}
