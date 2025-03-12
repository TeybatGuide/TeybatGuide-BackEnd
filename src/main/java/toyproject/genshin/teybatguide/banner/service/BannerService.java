package toyproject.genshin.teybatguide.banner.service;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.genshin.teybatguide.banner.character.entity.CharacterBanner;
import toyproject.genshin.teybatguide.banner.character.repository.CharacterBannerRepository;
import toyproject.genshin.teybatguide.banner.event.entity.Event;
import toyproject.genshin.teybatguide.banner.event.repository.EventRepository;
import toyproject.genshin.teybatguide.banner.event.dto.BannerEventsDto;
import toyproject.genshin.teybatguide.banner.character.dto.CharacterBannerDto;
import toyproject.genshin.teybatguide.banner.weapon.dto.WeaponBannerDto;
import toyproject.genshin.teybatguide.banner.controller.dto.request.CharacterBannerSaveRequest;
import toyproject.genshin.teybatguide.banner.controller.dto.request.WeaponBannerSaveRequest;
import toyproject.genshin.teybatguide.banner.controller.dto.response.CharacterBannerResponse;
import toyproject.genshin.teybatguide.banner.controller.dto.response.CharacterResourcesResponse;
import toyproject.genshin.teybatguide.banner.controller.dto.response.WeaponBannerResponse;
import toyproject.genshin.teybatguide.banner.value.BannerType;
import toyproject.genshin.teybatguide.banner.weapon.entity.WeaponBanner;
import toyproject.genshin.teybatguide.banner.weapon.repository.WeaponBannerRepository;
import toyproject.genshin.teybatguide.character.resource.repository.CharacterAscendRepository;
import toyproject.genshin.teybatguide.character.entity.Characters;
import toyproject.genshin.teybatguide.exception.TeybatBadRequestException;
import toyproject.genshin.teybatguide.exception.TeybatDataAccessException;
import toyproject.genshin.teybatguide.weapon.entity.Weapon;

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

    public List<CharacterResourcesResponse> searchBannerCharacterResources() {
        List<Characters> characters = characterBannerRepository.findCharactersByDateTimeBetween(LocalDateTime.now());
        return characterAscendRepository.findByCharacters(characters).entrySet().stream()
                .map(entry -> CharacterResourcesResponse.of(entry.getKey(), entry.getValue()))
                .toList();
    }

    @Transactional
    public CharacterBannerDto saveCharacterBanner(@NotNull CharacterBannerSaveRequest request) {
        Characters characters = characterBannerRepository.findCharactersById(request.characterId())
                .orElseThrow(() -> new TeybatBadRequestException("캐릭터가 존재하지 않습니다."));

        CharacterBanner banner = CharacterBanner.of(characters, request);
        characterBannerRepository.save(banner);

        CharacterBanner savedBanner = characterBannerRepository.findById(banner.getId())
                .orElseThrow(() -> new TeybatDataAccessException("캐릭터 배너 저장에 실패하였습니다."));

        return CharacterBannerDto.of(savedBanner);
    }

    @Transactional
    public WeaponBannerDto saveWeaponBanner(@NotNull WeaponBannerSaveRequest request) {
        Weapon weapon = weaponBannerRepository.findWeaponById(request.weaponId())
                .orElseThrow(() -> new TeybatBadRequestException("무기가 존재하지 않습니다."));

        WeaponBanner banner = WeaponBanner.of(weapon, request);
        weaponBannerRepository.save(banner);

        WeaponBanner savedBanner = weaponBannerRepository.findById(banner.getId())
                .orElseThrow(() -> new TeybatDataAccessException("무기 배너 저장에 실패하였습니다."));

        return WeaponBannerDto.of(savedBanner);
    }

    @Transactional
    public BannerEventsDto saveEvents(BannerEventsDto request) {
        Event event = Event.of(request);
        eventRepository.save(event);

        Event savedEvent = eventRepository.findById(event.getId())
                .orElseThrow(() -> new TeybatDataAccessException("이벤트 저장에 실패하였습니다."));

        return BannerEventsDto.of(savedEvent);
    }

}
