package toyproject.genshin.teybatguide.controller.dto.main;

import lombok.Builder;
import toyproject.genshin.teybatguide.domain.WeaponBanner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
public record WeaponBannerResponse(LocalDateTime startDate, LocalDateTime endDate, String bannerType, List<WeaponBannerDto> weapons) {

    public static WeaponBannerResponse of(WeaponBanner banner, List<WeaponBannerDto> weapons) {
        return WeaponBannerResponse.builder()
                .startDate(banner.getBannerStartDate())
                .endDate(banner.getBannerEndDate())
                .bannerType(banner.getBannerType().getBannerTypeName())
                .weapons(weapons)
                .build();
    }

    public static WeaponBannerResponse empty() {
        return WeaponBannerResponse.builder()
                .weapons(new ArrayList<>())
                .build();
    }

}
