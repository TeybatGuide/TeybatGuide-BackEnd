package toyproject.genshin.teybatguide.controller.dto.main;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.CharacterBanner;
import toyproject.genshin.teybatguide.domain.value.BannerType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
public record CharacterBannerResponse(LocalDateTime startDate, LocalDateTime endDate, String bannerType, List<CharacterBannerDto> characters) {

    public static CharacterBannerResponse of(CharacterBanner banner, List<CharacterBannerDto> characters) {
        return CharacterBannerResponse.builder()
                .startDate(banner.getBannerStartDate())
                .endDate(banner.getBannerEndDate())
                .bannerType(banner.getBannerType().getBannerTypeName())
                .characters(characters)
                .build();
    }

    public static CharacterBannerResponse of(BannerType bannerType, List<CharacterBanner> characters) {

        CharacterBanner characterBanner = characters.get(0);

        List<CharacterBannerDto> characterBannerDtos = characters.stream()
                .map(CharacterBannerDto::of)
                .toList();

        return CharacterBannerResponse.builder()
                .startDate(characterBanner.getBannerStartDate())
                .endDate(characterBanner.getBannerEndDate())
                .bannerType(bannerType.getBannerTypeName())
                .characters(characterBannerDtos)
                .build();
    }

    public static CharacterBannerResponse empty() {
        return CharacterBannerResponse.builder()
                .characters(new ArrayList<>())
                .build();
    }

}
