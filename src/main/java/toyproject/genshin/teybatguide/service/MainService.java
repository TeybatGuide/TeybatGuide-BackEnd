package toyproject.genshin.teybatguide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import toyproject.genshin.teybatguide.controller.dto.main.CharacterBannerDto;
import toyproject.genshin.teybatguide.controller.dto.main.CharacterBannerResponse;
import toyproject.genshin.teybatguide.domain.CharacterBanner;
import toyproject.genshin.teybatguide.repository.CharacterBannerRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {

    private final CharacterBannerRepository bannerRepository;

    public CharacterBannerResponse searchCharacterBanner() {
        List<CharacterBanner> characterBanners = bannerRepository.findByDateTimeBetween(LocalDateTime.now());

        if(characterBanners.isEmpty()) {
            return CharacterBannerResponse.empty();
        }
        List<CharacterBannerDto> characterBannerDtos = characterBanners.stream()
                .map(CharacterBannerDto::of)
                .toList();

        return CharacterBannerResponse.of(characterBanners.get(0), characterBannerDtos);
    }

}
