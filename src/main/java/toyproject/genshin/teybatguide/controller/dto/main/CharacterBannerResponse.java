package toyproject.genshin.teybatguide.controller.dto.main;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.CharacterBanner;

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

    public static CharacterBannerResponse empty() {
        return CharacterBannerResponse.builder()
                .characters(new ArrayList<>())
                .build();
    }

}
