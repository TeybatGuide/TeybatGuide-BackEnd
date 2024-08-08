package toyproject.genshin.teybatguide.controller.dto.banner.response;

import lombok.Builder;
import org.jetbrains.annotations.NotNull;
import toyproject.genshin.teybatguide.controller.dto.banner.CharacterBannerDto;
import toyproject.genshin.teybatguide.domain.CharacterBanner;
import toyproject.genshin.teybatguide.domain.value.BannerType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
public record CharacterBannerResponse(
        LocalDateTime startDate, LocalDateTime endDate, String bannerType, List<CharacterBannerDto> characters
) {

    public static CharacterBannerResponse of(@NotNull CharacterBanner banner, List<CharacterBannerDto> characters) {
        return CharacterBannerResponse.builder()
                .startDate(banner.getBannerStartDate())
                .endDate(banner.getBannerEndDate())
                .bannerType(banner.getBannerType().getBannerTypeName())
                .characters(characters)
                .build();
    }

    public static CharacterBannerResponse of(
            @NotNull BannerType bannerType,
            @NotNull List<CharacterBanner> characters
    ) {
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
