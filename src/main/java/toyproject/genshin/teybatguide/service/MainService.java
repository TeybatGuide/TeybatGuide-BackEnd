package toyproject.genshin.teybatguide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.genshin.teybatguide.controller.dto.main.*;
import toyproject.genshin.teybatguide.domain.CharacterBanner;
import toyproject.genshin.teybatguide.domain.Characters;
import toyproject.genshin.teybatguide.domain.Weapon;
import toyproject.genshin.teybatguide.domain.WeaponBanner;
import toyproject.genshin.teybatguide.exception.TeybatException;
import toyproject.genshin.teybatguide.repository.CharacterBannerRepository;
import toyproject.genshin.teybatguide.repository.CharactersRepository;
import toyproject.genshin.teybatguide.repository.WeaponBannerRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MainService {

    private final CharacterBannerRepository characterBannerRepository;
    private final CharactersRepository charactersRepository;
    private final WeaponBannerRepository weaponBannerRepository;

    public CharacterBannerResponse searchCharacterBanner() {
        List<CharacterBanner> characterBanners = characterBannerRepository.findByDateTimeBetween(LocalDateTime.now());

        if (characterBanners.isEmpty()) {
            return CharacterBannerResponse.empty();
        }
        List<CharacterBannerDto> characterBannerDtos = characterBanners.stream()
                .map(CharacterBannerDto::of)
                .toList();

        return CharacterBannerResponse.of(characterBanners.get(0), characterBannerDtos);
    }

    public WeaponBannerResponse searchWeaponBanner() {
        List<WeaponBanner> weaponBanners = weaponBannerRepository.findByDateTimeBetween(LocalDateTime.now());

        if (weaponBanners.isEmpty()) {
            return WeaponBannerResponse.empty();
        }

        List<WeaponBannerDto> weaponBannerDtos = weaponBanners.stream()
                .map(WeaponBannerDto::of)
                .toList();

        return WeaponBannerResponse.of(weaponBanners.get(0), weaponBannerDtos);
    }

    @Transactional
    public String saveCharacterBanner(CharacterBannerSaveRequest request) {
        Characters characters = charactersRepository.findById(request.characterId())
                .orElseThrow(() -> new TeybatException("아이디가 없습니다."));

        characterBannerRepository.save(CharacterBanner.of(characters, request));
        return "good";
    }

    @Transactional
    public String saveWeaponBanner(WeaponBannerSaveRequest request) {
        Weapon weapon = weaponBannerRepository.findWeaponById(request.weaponId())
                .orElseThrow(() -> new TeybatException("id가 존재하지 않습니다."));

        weaponBannerRepository.save(WeaponBanner.of(weapon, request));
        return "good";
    }

}
