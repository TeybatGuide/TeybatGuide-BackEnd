package toyproject.genshin.teybatguide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.genshin.teybatguide.controller.dto.*;
import toyproject.genshin.teybatguide.domain.CharacterWeapon;
import toyproject.genshin.teybatguide.domain.Characters;
import toyproject.genshin.teybatguide.domain.value.SignatureWeapon;
import toyproject.genshin.teybatguide.exception.TeybatException;
import toyproject.genshin.teybatguide.repository.CharacterSpecificationsRepository;
import toyproject.genshin.teybatguide.repository.CharacterWeaponRepository;
import toyproject.genshin.teybatguide.repository.CharactersRepository;

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
        Characters characters = charactersRepository.findById(id)
                .orElseThrow(() -> new TeybatException("아이디가 존재하지 않습니다."));

        Map<SignatureWeapon, List<WeaponDto>> weaponsMap = characterWeaponRepository.findByCharacters(characters).stream()
                .collect(Collectors.groupingBy(
                        CharacterWeapon::getSignatureWeapon,
                        Collectors.mapping(WeaponDto::of, Collectors.toList())
                ));

        return CharacterWeaponResponse.of(weaponsMap);
    }

}
