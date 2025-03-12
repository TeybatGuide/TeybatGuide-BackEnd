package toyproject.genshin.teybatguide.character.service;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.genshin.teybatguide.character.characterWeapon.entity.CharacterWeapon;
import toyproject.genshin.teybatguide.character.characterWeapon.repository.CharacterWeaponRepository;
import toyproject.genshin.teybatguide.character.entity.Characters;
import toyproject.genshin.teybatguide.character.repository.CharactersRepository;
import toyproject.genshin.teybatguide.character.specifications.dto.CharacterSpecificationsDto;
import toyproject.genshin.teybatguide.character.characterWeapon.dto.CharacterWeaponDto;
import toyproject.genshin.teybatguide.character.characterWeapon.dto.CharacterWeaponListDto;
import toyproject.genshin.teybatguide.character.controller.dto.request.CharacterListRequest;
import toyproject.genshin.teybatguide.character.controller.dto.request.CharacterWeaponSaveRequest;
import toyproject.genshin.teybatguide.character.controller.dto.response.CharacterDetailsResponse;
import toyproject.genshin.teybatguide.character.controller.dto.response.CharacterListResponse;
import toyproject.genshin.teybatguide.character.controller.dto.response.CharacterWeaponResponse;
import toyproject.genshin.teybatguide.character.specifications.repository.CharacterSpecificationsRepository;
import toyproject.genshin.teybatguide.weapon.entity.Weapon;
import toyproject.genshin.teybatguide.character.characterWeapon.entity.dto.WeaponCriteria;
import toyproject.genshin.teybatguide.exception.TeybatBadRequestException;
import toyproject.genshin.teybatguide.exception.TeybatDataAccessException;
import toyproject.genshin.teybatguide.weapon.repository.WeaponRepository;

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

    public Page<CharacterListResponse> findAndCreateCharacterList(CharacterListRequest request, Pageable pageable) {
        return charactersRepository.findByStarsAndCountryAndElementAndWeaponType(request, pageable)
                .map(CharacterListResponse::of);
    }

    public CharacterDetailsResponse findAndBuildCharacterDetails(String id) {
        Characters characters = charactersRepository.findById(id)
                .orElseThrow(() -> new TeybatBadRequestException("캐릭터가 존재하지 않습니다."));

        CharacterSpecificationsDto specifications = specificationsRepository.findByCharacters(characters)
                .map(CharacterSpecificationsDto::of)
                .orElse(null);

        return CharacterDetailsResponse.of(characters, specifications);

    }

    public CharacterWeaponResponse findAndBuildCharacterWeapon(String id) {
        Characters character = charactersRepository.findById(id)
                .orElseThrow(() -> new TeybatBadRequestException("캐릭터가 존재하지 않습니다."));

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

    private boolean isPresentCharacterList(@NotNull List<CharacterWeapon> characterWeapons) {
        return !characterWeapons.isEmpty();
    }

    private @Nullable String getVersion(List<CharacterWeapon> characterWeapons) {
        if (isPresentCharacterList(characterWeapons)) {
            return characterWeapons.get(0).getVersion();
        }
        return null;
    }

    @Transactional
    public CharacterWeaponDto save(@NotNull CharacterWeaponSaveRequest request) {
        Characters character = charactersRepository.findById(request.characterId())
                .orElseThrow(() -> new TeybatBadRequestException("캐릭터가 존재하지 않습니다."));
        Weapon weapon = weaponRepository.findById(request.weaponId())
                .orElseThrow(() -> new TeybatBadRequestException("무기가 존재하지 않습니다."));

        CharacterWeapon entity = CharacterWeapon.of(character, weapon, request);
        characterWeaponRepository.save(entity);

        CharacterWeapon characterWeapon = characterWeaponRepository.findById(entity.getId())
                .orElseThrow(() -> new TeybatDataAccessException("저장에 실패하였습니다."));
        return CharacterWeaponDto.of(characterWeapon);
    }

}
