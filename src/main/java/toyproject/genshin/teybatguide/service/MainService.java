package toyproject.genshin.teybatguide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.genshin.teybatguide.controller.dto.main.CharacterBannerDto;
import toyproject.genshin.teybatguide.controller.dto.main.CharacterBannerResponse;
import toyproject.genshin.teybatguide.controller.dto.main.CharacterBannerSaveRequest;
import toyproject.genshin.teybatguide.domain.CharacterBanner;
import toyproject.genshin.teybatguide.domain.Characters;
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

        if(characterBanners.isEmpty()) {
            return CharacterBannerResponse.empty();
        }
        List<CharacterBannerDto> characterBannerDtos = characterBanners.stream()
                .map(CharacterBannerDto::of)
                .toList();

        return CharacterBannerResponse.of(characterBanners.get(0), characterBannerDtos);
    }

    @Transactional
    public String saveBanner(CharacterBannerSaveRequest request) {
        Characters characters = charactersRepository.findById(request.characterId())
                .orElseThrow(() -> new TeybatException("아이디가 없습니다."));

        characterBannerRepository.save(CharacterBanner.of(characters, request));
        return "good";
    }

}
