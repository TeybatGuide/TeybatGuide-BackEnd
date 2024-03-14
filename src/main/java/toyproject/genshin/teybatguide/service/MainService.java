package toyproject.genshin.teybatguide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.genshin.teybatguide.controller.dto.MainCharacterResourcesResponse;
import toyproject.genshin.teybatguide.controller.dto.base.PageDto;
import toyproject.genshin.teybatguide.controller.dto.base.PageResponseData;
import toyproject.genshin.teybatguide.controller.dto.main.*;
import toyproject.genshin.teybatguide.domain.*;
import toyproject.genshin.teybatguide.domain.value.BannerType;
import toyproject.genshin.teybatguide.domain.value.DayOfWeek;
import toyproject.genshin.teybatguide.domain.value.Materials;
import toyproject.genshin.teybatguide.exception.TeybatException;
import toyproject.genshin.teybatguide.repository.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MainService {

    private final CharacterBannerRepository characterBannerRepository;
    private final CharacterAscendRepository characterAscendRepository;
    private final WeaponBannerRepository weaponBannerRepository;
    private final ResourcesRepository resourcesRepository;
    private final EventRepository eventRepository;

    public List<CharacterBannerResponse> searchCharacterBanner() {
        Map<BannerType, List<CharacterBanner>> characterBanners = characterBannerRepository.findByDateTimeBetween(LocalDateTime.now());

        if (characterBanners.isEmpty()) {
            return List.of(CharacterBannerResponse.empty());
        }

        return characterBanners.entrySet().stream()
                .map(entry -> CharacterBannerResponse.of(entry.getKey(), entry.getValue()))
                .toList();
    }

    public List<WeaponBannerResponse> searchWeaponBanner() {
        Map<BannerType, List<WeaponBanner>> weaponBanners = weaponBannerRepository.findByDateTimeBetween(LocalDateTime.now());

        if (weaponBanners.isEmpty()) {
            return List.of(WeaponBannerResponse.empty());
        }

        return weaponBanners.entrySet().stream()
                .map(entry -> WeaponBannerResponse.of(entry.getKey(), entry.getValue()))
                .toList();
    }

    public PageResponseData<List<MainResourcesResponse>> searchResources(Pageable pageable) {
        Page<Resources> resourcesPage = resourcesRepository.findByDayOfWeekForMain(DayOfWeek.of(getDayOfWeek()), pageable);

        Map<Materials, List<MainResourcesListDto>> materialsListMap = resourcesPage.stream()
                .collect(Collectors.groupingBy(
                        Resources::getMaterials,
                        Collectors.mapping(MainResourcesListDto::of, Collectors.toList())
                ));

        List<MainResourcesResponse> mainResourcesResponses = materialsListMap.entrySet().stream()
                .map(entry -> MainResourcesResponse.of(entry.getKey(), entry.getValue()))
                .toList();

        return PageResponseData.of(mainResourcesResponses, PageDto.of(resourcesPage));
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

    private java.time.DayOfWeek getDayOfWeek() {
        LocalDateTime now = LocalDateTime.now();

        if (now.getHour() < 5) {
            now = now.minusDays(1);
        }

        return now.getDayOfWeek();
    }

    @Transactional
    public String saveCharacterBanner(CharacterBannerSaveRequest request) {
        Characters characters = characterBannerRepository.findCharactersById(request.characterId())
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

    @Transactional
    public String saveEvents(BannerEventsDto request) {
        eventRepository.save(Event.of(request));
        return "저장 완료";
    }

}
