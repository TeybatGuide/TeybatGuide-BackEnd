package toyproject.genshin.teybatguide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.genshin.teybatguide.controller.dto.MainCharacterResourcesResponse;
import toyproject.genshin.teybatguide.controller.dto.main.*;
import toyproject.genshin.teybatguide.domain.*;
import toyproject.genshin.teybatguide.domain.value.BannerType;
import toyproject.genshin.teybatguide.exception.TeybatException;
import toyproject.genshin.teybatguide.repository.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BannerService {

    private final CharacterBannerRepository characterBannerRepository;
    private final CharacterAscendRepository characterAscendRepository;
    private final WeaponBannerRepository weaponBannerRepository;
    private final EventRepository eventRepository;

    public CharacterBannerResponse searchCharacterBanner(BannerType bannerType) {
        List<CharacterBanner> characterBanners = characterBannerRepository.findByDateTimeBetween(LocalDateTime.now(), bannerType);

        if (characterBanners.isEmpty()) {
            return CharacterBannerResponse.empty();
        }

        return CharacterBannerResponse.of(BannerType.CHARACTER, characterBanners);
    }

    public WeaponBannerResponse searchWeaponBanner(BannerType bannerType) {
        List<WeaponBanner> weaponBanners = weaponBannerRepository.findByDateTimeBetween(LocalDateTime.now(), bannerType);

        if (weaponBanners.isEmpty()) {
            return WeaponBannerResponse.empty();
        }

        return WeaponBannerResponse.of(BannerType.WEAPON, weaponBanners);
    }

    public List<BannerEventsDto> searchEvents() {
        return eventRepository.findByDate(LocalDateTime.now()).stream()
                .map(BannerEventsDto::of)
                .toList();
    }

    public List<MainCharacterResourcesResponse> searchBannerCharacterResources() {
        List<Characters> characters = characterBannerRepository.findCharactersByDateTimeBetween(LocalDateTime.now());
        return characterAscendRepository.findByCharacters(characters).entrySet().stream()
                .map(entry -> MainCharacterResourcesResponse.of(entry.getKey(), entry.getValue()))
                .toList();
    }

    @Transactional
    public CharacterBannerDto saveCharacterBanner(CharacterBannerSaveRequest request) {
        Characters characters = characterBannerRepository.findCharactersById(request.characterId())
                .orElseThrow(() -> new TeybatException("아이디가 없습니다."));

        CharacterBanner banner = CharacterBanner.of(characters, request);
        characterBannerRepository.save(banner);
        characterBannerRepository.findById(banner.getId());
        return CharacterBannerDto.of(banner);
    }

    @Transactional
    public WeaponBannerDto saveWeaponBanner(WeaponBannerSaveRequest request) {
        Weapon weapon = weaponBannerRepository.findWeaponById(request.weaponId())
                .orElseThrow(() -> new TeybatException("id가 존재하지 않습니다."));

        WeaponBanner banner = WeaponBanner.of(weapon, request);
        weaponBannerRepository.save(banner);
        return WeaponBannerDto.of(banner);
    }

    @Transactional
    public BannerEventsDto saveEvents(BannerEventsDto request) {
        Event event = Event.of(request);
        eventRepository.save(event);
        return BannerEventsDto.of(event);
    }

}
